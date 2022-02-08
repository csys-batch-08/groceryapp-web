package com.chainsys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.CartDaoImpl;
import com.chainsys.daoimpl.OrderDaoImpl;
import com.chainsys.dto.Feature;
import com.chainsys.model.Customer;
import com.chainsys.model.Order;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		int orderid = 0;
		double total = 0;
		double offer = 0;

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("logincustomer");

		int customerId = customer.getCustomerid();
		Order order = new Order();
		order.setCustomerid(customerId);
		OrderDaoImpl obj1 = new OrderDaoImpl();
		int orderidcheck;

		orderidcheck = obj1.cartCheck(order);
		if (orderidcheck > 0) {
			orderid = orderidcheck;
		}

		session.setAttribute("logincustomerorderId", orderid);

		Feature feature = new Feature();
		feature.setOrderId(orderid);
		CartDaoImpl obj = new CartDaoImpl();

		List<Feature> cartlist = obj.showCartin(feature);
		request.setAttribute("cartlist", cartlist);

		total = obj.showCartinTotal(feature);
		request.setAttribute("total", total);

		if (total > 499 && total < 999) {
			offer = total * 0.05;
			total = total - offer;

		} else if (total >= 999) {
			offer = total * 0.1;
			total = total - offer;

		}
		request.setAttribute("offer", total);

		try {
			request.getRequestDispatcher("cart.jsp").forward(request, response);

		} catch (IOException | ServletException e) {

			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

}
