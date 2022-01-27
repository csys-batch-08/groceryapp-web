package com.chainsys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.OrderDaoImpl;
import com.chainsys.model.Feature;

@WebServlet("/WeekSaleServlet")
public class WeekSaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WeekSaleServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		OrderDaoImpl obj = new OrderDaoImpl();
		try {
			List<Feature> sale = obj.weekSale();
			session.setAttribute("sale", sale);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		try {
			double b = obj.weekSales();
			session.setAttribute("b", b);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		response.sendRedirect("WeekSale.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
