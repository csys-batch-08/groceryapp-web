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

import com.chainsys.exception.RegistorException;
import com.chainsys.model.Customer;

@WebServlet("/signup")
public class RegistorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		boolean flag=false;
		String username = req.getParameter("cname");
		String password = req.getParameter("password");
		String firstName = req.getParameter("fname");
		String lastName = req.getParameter("lname");
		String address = req.getParameter("address");
		String phonenumbers = req.getParameter("pnumber");
		long phonenumber = Long.parseLong(phonenumbers);
		String emailid = req.getParameter("eid");
		Customer customer = new Customer(username, password, firstName, lastName, address, phonenumber, emailid);
		CustomerDaoImpl obj1 = new CustomerDaoImpl();
		try {
			 flag = obj1.signup(customer);
			if (flag) {
				resp.sendRedirect("login.jsp");

			}
			else
			{
				throw new RegistorException();
			}
		} catch (ClassNotFoundException | SQLException | RegistorException | IOException e) {
			session.setAttribute("erroruserids",  "Sorry, User Already exist!");

			try {
				req.getRequestDispatcher("signup.jsp").include(req, resp);
			} catch (ServletException | IOException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			{

		doGet(request, response);
	}

}
