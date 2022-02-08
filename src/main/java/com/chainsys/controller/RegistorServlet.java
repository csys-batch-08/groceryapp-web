package com.chainsys.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.CustomerDaoImpl;
import com.chainsys.exception.RegistorException;
import com.chainsys.model.Customer;

@WebServlet("/signup")
public class RegistorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		Customer customer = null;
		int customerid = 0;
		CustomerDaoImpl obj = new CustomerDaoImpl();

		try {

			String username = req.getParameter("cname");
			String password = req.getParameter("password");
			String firstName = req.getParameter("fname");
			String lastName = req.getParameter("lname");
			String address = req.getParameter("address");
			String phonenumbers = req.getParameter("pnumber");
			long phonenumber = Long.parseLong(phonenumbers);
			String emailid = req.getParameter("eid");
			customer = new Customer(username, password, firstName, lastName, address, phonenumber, emailid);
			customerid = obj.signupcheck(customer);

			if (customerid > 0) {
				throw new RegistorException();
			} else {
				obj.signup(customer);
				resp.sendRedirect("login.jsp");

			}
		} catch (NumberFormatException | IOException | RegistorException e2) {
			session.setAttribute("erroruserids", ((RegistorException) e2).getUserNameLoginMessage());

			try {
				resp.sendRedirect("signup.jsp");

			} catch (IOException e1) {

				System.out.println(e1.getMessage());
			}

			System.out.println(e2.getMessage());
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

}
