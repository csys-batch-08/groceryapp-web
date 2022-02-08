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

@WebServlet("/TodaySaleServlet")
public class TodaySaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodaySaleServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		OrderDaoImpl obj = new OrderDaoImpl();
		List<Feature> sale = obj.todaySale();
		request.setAttribute("sale", sale);

		double b = obj.todaySales();
		request.setAttribute("b", b);

		request.setAttribute("date", date);
		try {
			request.getRequestDispatcher("todaySale.jsp").forward(request, response);

		} catch (IOException | ServletException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

}
