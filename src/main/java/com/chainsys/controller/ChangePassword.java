package com.chainsys.controller;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.daoimpl.CustomerDaoImpl;
import com.chainsys.model.Customer;

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangePassword() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		long phoneNumber = Long.parseLong(req.getParameter("uname"));
		String Password = req.getParameter("pword");
		Customer customer = new Customer();
		customer.setPhonenumber(phoneNumber);
		customer.setPassword(Password);
		CustomerDaoImpl obj = new CustomerDaoImpl();

		resp.setContentType("text/html");
		try {

			boolean flag = obj.changepassword(customer);

			if (flag) {

				resp.sendRedirect("Login.jsp");

			} else {

				resp.sendRedirect("Login.jsp");

			}

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
