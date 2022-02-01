package com.chainsys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.ProductDaoImpl;
import com.chainsys.model.Product;

@WebServlet("/CustomerviewServlet")
public class CustomerviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerviewServlet() {
		super();

	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 {

		ProductDaoImpl obj = new ProductDaoImpl();
		HttpSession session = request.getSession();

		try {
			List<Product> productLists = obj.ViewAllProducte();
			session.setAttribute("productLists", productLists);
			response.sendRedirect("customerView.jsp");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
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
