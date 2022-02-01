package com.chainsys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.OrderDaoImpl;
import com.chainsys.model.Order;

@WebServlet("/ListoforderServlet")
public class ListoforderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListoforderServlet() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 {

		HttpSession session = request.getSession();
		OrderDaoImpl obj = new OrderDaoImpl();
		try {
			List<Order> OrderList = obj.listoforder();
			session.setAttribute("OrderLists", OrderList);
			response.sendRedirect("listoforder.jsp");
		
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
