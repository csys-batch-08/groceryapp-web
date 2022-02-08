package com.chainsys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.daoimpl.CustomerDaoImpl;
import com.chainsys.model.Customer;

@WebServlet("/AdminAllUserServlet")
public class AdminAllUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAllUserServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		CustomerDaoImpl obj = new CustomerDaoImpl();

		List<Customer> userList = obj.viewallLoginUser();
		request.setAttribute("userList", userList);

		try {
			request.getRequestDispatcher("adminAllUser.jsp").forward(request, response);

		} catch (IOException | ServletException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

}
