package com.chainsys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.daoimpl.OrderDaoImpl;
import com.chainsys.dto.Feature;
import com.chainsys.model.Order;

@WebServlet("/AdminViewServlet")
public class AdminViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminViewServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			OrderDaoImpl obj = new OrderDaoImpl();

			List<Feature> sale = obj.todaySalegraph();
			request.setAttribute("sale", sale);

			List<Order> sales = obj.graphsale();
			request.setAttribute("sales", sales);
			request.getRequestDispatcher("adminView.jsp").forward(request, response);

		} catch (IOException | ServletException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

}
