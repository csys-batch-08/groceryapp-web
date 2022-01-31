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
import com.chainsys.model.Order;

@WebServlet("/AdminViewServlet")
public class AdminViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminViewServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		OrderDaoImpl obj = new OrderDaoImpl();
		HttpSession session = request.getSession();

		try {
			List<Feature> sale = obj.todaySalegraph();
			session.setAttribute("sale", sale);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			List<Order> sales = obj.graphsale();
			session.setAttribute("sales", sales);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		response.sendRedirect("adminView.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
