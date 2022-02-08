package com.chainsys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.daoimpl.ProductDaoImpl;
import com.chainsys.model.Product;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		ProductDaoImpl obj = new ProductDaoImpl();

		List<Product> productList = obj.AdminViewAllProducts();
		request.setAttribute("productList", productList);

		try {
			request.getRequestDispatcher("product.jsp").forward(request, response);

		} catch (IOException | ServletException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

}
