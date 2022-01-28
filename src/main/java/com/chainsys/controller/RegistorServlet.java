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

@WebServlet("/signup")
public class RegistorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("cname");
		String password = req.getParameter("password");
		String firstName = req.getParameter("fname");
		String lastName = req.getParameter("lname");
		String address = req.getParameter("address");
		long phonenumber = Long.parseLong(req.getParameter("pnumber"));
		String emailid = req.getParameter("eid");
		Customer customer = new Customer(username, password, firstName, lastName, address, phonenumber, emailid);
		CustomerDaoImpl obj1 = new CustomerDaoImpl();
		try {
			boolean flag = obj1.signup(customer);
			if (flag) {
				resp.sendRedirect("Login.jsp");

			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
