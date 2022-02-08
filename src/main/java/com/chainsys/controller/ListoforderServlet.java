package com.chainsys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.daoimpl.OrderDaoImpl;
import com.chainsys.model.Order;

@WebServlet("/ListoforderServlet")
public class ListoforderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListoforderServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		OrderDaoImpl obj = new OrderDaoImpl();
		try {
			List<Order> OrderList = obj.listoforder();
			request.setAttribute("OrderLists", OrderList);

			request.getRequestDispatcher("listoforder.jsp").forward(request, response);
		} catch (IOException | ServletException e) {

			System.out.println(e.getMessage());
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

}
