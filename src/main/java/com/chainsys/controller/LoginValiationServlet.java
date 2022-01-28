package com.chainsys.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.CustomerDaoImpl;
import com.chainsys.exception.LoginException;
import com.chainsys.model.Customer;

@WebServlet("/login")
public class LoginValiationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		long phoneNumber = Long.parseLong(req.getParameter("uname"));
		String Password = req.getParameter("pword");
		Customer customer = new Customer();
		customer.setPhonenumber(phoneNumber);
		customer.setPassword(Password);
		CustomerDaoImpl obj = new CustomerDaoImpl();
		HttpSession session = req.getSession();
		resp.setContentType("text/html");
		try {

			customer = obj.login(customer);

			if (customer.getCustomerid() != 0) {

				session.setAttribute("logincustomer", customer);
				if (customer.getCustomerid() == 1) {
					resp.sendRedirect("AdminViewServlet");

				} else {
					resp.sendRedirect("CustomerviewServlet");
				}
			} else {

				throw new LoginException();

			}

		} catch (ClassNotFoundException | SQLException | LoginException e) {
			session.setAttribute("erroruserid", ((LoginException) e).getUserNameLoginMessage());

			req.getRequestDispatcher("Login.jsp").include(req, resp);
			e.printStackTrace();
		}

	}
}
