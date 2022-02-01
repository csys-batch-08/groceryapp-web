package com.chainsys.controller;

import java.io.IOException;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		Customer customer = new Customer();
		CustomerDaoImpl obj = new CustomerDaoImpl();
		try {
			String phonenumbers = req.getParameter("uname");
			long phonenumber = Long.parseLong(phonenumbers);
			String Password = req.getParameter("pword");

			customer.setPhonenumber(phonenumber);
			customer.setPassword(Password);
		} catch (NumberFormatException e2) {

			e2.printStackTrace();
		}

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

		} catch ( LoginException | IOException e) {
			session.setAttribute("erroruserid", ((LoginException) e).getUserNameLoginMessage());

			try {
				resp.sendRedirect("login.jsp");

			} catch (IOException e1) {

				e1.printStackTrace();
			}

			e.printStackTrace();
		}

	}

}
