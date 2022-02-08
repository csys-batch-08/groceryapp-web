package com.chainsys.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.daoimpl.OrderDaoImpl;
import com.chainsys.model.Order;

@WebServlet("/CancelOrder")
public class CancelOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CancelOrder() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		Order order = new Order();
		try {
			String orderIdstr = request.getParameter("orderId");
			int orderId = Integer.parseInt(orderIdstr);

			order.setOrderid(orderId);
		} catch (NumberFormatException e1) {
			System.out.println(e1.getMessage());
		}

		OrderDaoImpl obj = new OrderDaoImpl();
		boolean flag = obj.cancel(order);

		response.setContentType("text/html");
		if (flag) {
			try {
				response.sendRedirect("cancel.jsp");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
