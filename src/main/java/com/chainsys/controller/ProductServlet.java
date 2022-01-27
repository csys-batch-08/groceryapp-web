package com.chainsys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.ProductDaoImpl;
import com.chainsys.model.Product;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDaoImpl obj = new ProductDaoImpl();
		HttpSession session = request.getSession();
		try {
			List<Product> productList = obj.AdminViewAllProducts();
			session.setAttribute("productList", productList);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		response.sendRedirect("Product.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
