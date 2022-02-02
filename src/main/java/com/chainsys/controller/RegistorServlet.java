package com.chainsys.controller;

import java.io.IOException;


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
		Customer customer = null;
		CustomerDaoImpl obj1 = new CustomerDaoImpl();
		boolean flag=false;
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
		} catch (NumberFormatException e2) {
			
			System.out.println(e2.getMessage());
		}
		
		try {
			 flag = obj1.signup(customer);
			if (flag) {
				resp.sendRedirect("login.jsp");

			}
			else
			{
				throw new RegistorException();
			}
		} catch ( RegistorException | IOException e) {
			session.setAttribute("erroruserids",  "Sorry, User Already exist!");

			try {
				req.getRequestDispatcher("signup.jsp").include(req, resp);
			} catch (ServletException | IOException e1) {
				
				System.out.println(e1.getMessage());
			}
			System.out.println(e.getMessage());
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			{

		doGet(request, response);
	}

}
