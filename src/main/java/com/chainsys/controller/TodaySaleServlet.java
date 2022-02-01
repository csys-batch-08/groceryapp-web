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
import com.chainsys.model.Feature;

@WebServlet("/TodaySaleServlet")
public class TodaySaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodaySaleServlet() {
		super();

	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 {
		long millis=System.currentTimeMillis();  
		 java.sql.Date date = new java.sql.Date(millis);   
		 
     
		OrderDaoImpl obj = new OrderDaoImpl();
		HttpSession session = request.getSession();
		try {
			List<Feature> sale = obj.todaySale();
			session.setAttribute("sale", sale);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		try {
			double b = obj.todaySales();
			session.setAttribute("b", b);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		session.setAttribute("date", date);
		try {
			response.sendRedirect("todaySale.jsp");
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
