package com.chainsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.CustomerDaoImpl;
import com.chainsys.model.Customer;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  long phoneNumber=Long.parseLong(req.getParameter("uname"));
			 String Password=req.getParameter("pword");
			 Customer customer =new Customer();
			 customer.setPhonenumber(phoneNumber);
			 customer.setPassword(Password);
			 CustomerDaoImpl obj=new  CustomerDaoImpl();
			 PrintWriter out=resp.getWriter();  
			 resp.setContentType("text/html");
			 try {
				 
				boolean flag= obj.changepassword(customer);
			
				if(flag)
				{
					out.print("Password Change sucessfully");  
					resp.sendRedirect("Login.jsp");
					
				
				}
				else {
					 out.print("Sorry, username or password error!");  
					 resp.sendRedirect("Login.jsp");
				
					
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
