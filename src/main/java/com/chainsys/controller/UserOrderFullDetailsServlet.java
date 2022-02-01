package com.chainsys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.OrderDaoImpl;
import com.chainsys.model.Feature;
import com.chainsys.model.Order;

@WebServlet("/UserOrderFullDetailsServlet")
public class UserOrderFullDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserOrderFullDetailsServlet() {
		super();

	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 {
		Order order = new Order();
		HttpSession session=null;
		Feature feature=null;
		try {
			String b1 = request.getParameter("orderId");
			int b = Integer.parseInt(b1);
			session = request.getSession();
			session.setAttribute("b", b);
			feature = new Feature();
			feature.setOrderId(b);
			
			order.setOrderid(b);
		} catch (NumberFormatException e1) {
			
			e1.printStackTrace();
		}
		OrderDaoImpl obj = new OrderDaoImpl();
		try {
			List<Feature> orderlist = obj.userOrderDetails(feature);
			session.setAttribute("orderlists", orderlist);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		String status = obj.status(order);
		session.setAttribute("status", status);
		try {
			double c = obj.userOrderDetailse(feature);
			session.setAttribute("c", c);
			response.sendRedirect("userOrderFullDetails.jsp");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		 {

		doGet(request, response);
	}

}
