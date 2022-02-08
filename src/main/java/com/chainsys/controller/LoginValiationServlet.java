package com.chainsys.controller;

import java.io.IOException;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		Customer customer = new Customer();
		CustomerDaoImpl obj = new CustomerDaoImpl();
		HttpSession session = req.getSession();

		try {
			String phonenumberstr = req.getParameter("phonenumber");
			long phonenumber = Long.parseLong(phonenumberstr);
			String Password = req.getParameter("pword");

			customer.setPhonenumber(phonenumber);
			customer.setPassword(Password);

			resp.setContentType("text/html");

			customer = obj.login(customer);

			if (customer != null) {

				session.setAttribute("logincustomer", customer);
				if (customer.getCustomerid() == 1) {

					req.getRequestDispatcher("AdminViewServlet").include(req, resp);

				} else {

					req.getRequestDispatcher("CustomerviewServlet").include(req, resp);
				}
			} else {

				throw new LoginException();

			}

		} catch (LoginException | IOException | ServletException | NumberFormatException | NullPointerException e) {
			session.setAttribute("erroruserid", ((LoginException) e).getUserNameLoginMessage());

			try {
				resp.sendRedirect("login.jsp");

			} catch (IOException e1) {

				System.out.println(e1.getMessage());
			}

			System.out.println(e.getMessage());
		}

	}

}
