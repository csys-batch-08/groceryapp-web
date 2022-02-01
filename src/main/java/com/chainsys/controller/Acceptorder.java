package com.chainsys.controller;

import java.io.IOException;
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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 {
		boolean flag = false;
		String ordids = request.getParameter("orderId");
		int ordid = Integer.parseInt(ordids);
		Order order = new Order();
		order.setOrderid(ordid);
		OrderDaoImpl obj = new OrderDaoImpl();
		flag = obj.accept(order);
		if (flag) {
			try {
				response.sendRedirect("PendingOrdersServlet");
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		 {

		doGet(request, response);
	}

}
