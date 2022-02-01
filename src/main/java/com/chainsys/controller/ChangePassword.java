package com.chainsys.controller;

import java.io.IOException;

import java.sql.SQLException;

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
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
		CustomerDaoImpl obj = new CustomerDaoImpl();
		Customer customer = new Customer();
		try {
			String phonenumbers = req.getParameter("uname");
			long phonenumber = Long.parseLong(phonenumbers);
			String Password = req.getParameter("pword");
			
			customer.setPhonenumber(phonenumber);
			customer.setPassword(Password);
		} catch (NumberFormatException e1) {
			
			e1.printStackTrace();
		}
		

		resp.setContentType("text/html");
		try {

			boolean flag = obj.changepassword(customer);

			if (flag) {

				resp.sendRedirect("login.jsp");

			} else {

				resp.sendRedirect("login.jsp");

			}

		} catch (ClassNotFoundException | SQLException | IOException e) {

			e.printStackTrace();
		}

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 {

		doGet(request, response);
	}
}
