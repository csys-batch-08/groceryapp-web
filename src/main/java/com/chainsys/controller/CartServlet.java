package com.chainsys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.CartDaoImpl;
import com.chainsys.daoimpl.OrderDaoImpl;
import com.chainsys.model.Customer;
import com.chainsys.model.Feature;
import com.chainsys.model.Order;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartServlet() {
		super();

	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			{

		int oid = 0;
		double total = 0;
		double offer = 0;

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("logincustomer");

		int cid = customer.getCustomerid();
		Order order = new Order();
		order.setCustomerid(cid);
		OrderDaoImpl obj1 = new OrderDaoImpl();
		int ojid;
		try {
			ojid = obj1.cartCheck(order);
			if (ojid > 0) {
				oid = ojid;
			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		session.setAttribute("logincustomerorderId", oid);

		Feature feature = new Feature();
		feature.setOrderId(oid);
		CartDaoImpl obj = new CartDaoImpl();
		try {
			List<Feature> cartlist = obj.showCartin(feature);
			session.setAttribute("cartlist", cartlist);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			total = obj.showCartinTotal(feature);
			session.setAttribute("total", total);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		if (total > 499 && total < 999) {
			offer = total * 0.05;
			total = total - offer;

		} else if (total >= 999) {
			offer = total * 0.1;
			total = total - offer;

		}
		session.setAttribute("offer", total);

		try {
			response.sendRedirect("cart.jsp");
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
