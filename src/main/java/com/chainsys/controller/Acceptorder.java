package com.chainsys.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.daoimpl.OrderDaoImpl;
import com.chainsys.model.Order;

@WebServlet("/Accept")
public class Acceptorder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Acceptorder() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		int ordid = Integer.parseInt(request.getParameter("orderId"));
		Order order = new Order();
		order.setOrderid(ordid);
		OrderDaoImpl obj = new OrderDaoImpl();
		flag = obj.accept(order);
		if (flag) {
			response.sendRedirect("PendingOrdersServlet");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
