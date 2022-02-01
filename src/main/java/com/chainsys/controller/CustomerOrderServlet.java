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
import com.chainsys.model.Customer;
import com.chainsys.model.Order;

@WebServlet("/CustomerOrderServlet")
public class CustomerOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerOrderServlet() {
		super();

	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 {

		HttpSession session = request.getSession();

		Customer customer = (Customer) session.getAttribute("logincustomer");
		Order order = new Order();
		order.setCustomerid(customer.getCustomerid());
		OrderDaoImpl obj = new OrderDaoImpl();
		try {
			List<Order> orderlist = obj.orderdetail(order);
			session.setAttribute("orderlist", orderlist);
			response.sendRedirect("customerOrder.jsp");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
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
