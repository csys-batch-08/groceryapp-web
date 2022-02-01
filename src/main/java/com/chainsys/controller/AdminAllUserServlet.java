package com.chainsys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.CustomerDaoImpl;
import com.chainsys.model.Customer;

@WebServlet("/AdminAllUserServlet")
public class AdminAllUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAllUserServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 {

		CustomerDaoImpl obj = new CustomerDaoImpl();
		HttpSession session = request.getSession();
		try {
			List<Customer> userList = obj.viewallLoginUser();
			session.setAttribute("userList", userList);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			response.sendRedirect("adminAllUser.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 {

		doGet(request, response);
	}

}
