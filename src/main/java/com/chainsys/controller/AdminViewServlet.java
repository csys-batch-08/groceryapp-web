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
import com.chainsys.model.Feature;
import com.chainsys.model.Order;

@WebServlet("/AdminViewServlet")
public class AdminViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminViewServlet () {
		super();

	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			{

		try {
			OrderDaoImpl obj = new OrderDaoImpl();
			HttpSession session = request.getSession();

			
				List<Feature> sale = obj.todaySalegraph();
				session.setAttribute("sale", sale);

			
			
				List<Order> sales = obj.graphsale();
				session.setAttribute("sales", sales);
				response.sendRedirect("adminView.jsp");
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
