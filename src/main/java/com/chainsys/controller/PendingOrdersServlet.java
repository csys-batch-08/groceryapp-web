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
import com.chainsys.model.Order;

@WebServlet("/PendingOrdersServlet")
public class PendingOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PendingOrdersServlet() {
		super();

	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 {
		OrderDaoImpl obj = new OrderDaoImpl();
		HttpSession session = request.getSession();
		try {
			List<Order> OrderList = obj.orderdetails();
			session.setAttribute("OrderList", OrderList);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			response.sendRedirect("pendingOrders.jsp");
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
