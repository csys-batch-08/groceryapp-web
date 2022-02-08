package com.chainsys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.OrderDaoImpl;
import com.chainsys.dto.Feature;
import com.chainsys.model.Order;

@WebServlet("/UserOrderFullDetailsServlet")
public class UserOrderFullDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserOrderFullDetailsServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		Order order = new Order();
		HttpSession session = request.getSession();
		Feature feature = null;
		try {
			String orderIdstr = request.getParameter("orderId");
			int orderId = Integer.parseInt(orderIdstr);
			session.setAttribute("b", orderId);
			feature = new Feature();
			feature.setOrderId(orderId);

			order.setOrderid(orderId);
		} catch (NumberFormatException e1) {

			System.out.println(e1.getMessage());
		}
		OrderDaoImpl obj = new OrderDaoImpl();

		List<Feature> orderlist = obj.userOrderDetails(feature);
		request.setAttribute("orderlists", orderlist);

		String status = obj.status(order);
		request.setAttribute("status", status);
		try {
			double c = obj.userOrderDetailse(feature);
			request.setAttribute("c", c);

			request.getRequestDispatcher("userOrderFullDetails.jsp").forward(request, response);
		} catch (IOException | ServletException e) {

			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

}
