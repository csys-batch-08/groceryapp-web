package com.chainsys.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.daoimpl.ProductDaoImpl;
import com.chainsys.model.Product;

@WebServlet("/InActiveproduct")

public class InActiveProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String productname = req.getParameter("pname");
		String productstatus = req.getParameter("status");
		Product product = new Product();
		product.setProductName(productname);
		product.setProducStatus(productstatus);
		ProductDaoImpl obj = new ProductDaoImpl();

		resp.setContentType("text/html");
		try {

			boolean flag = obj.delete(product);
			if (flag) {

				req.getRequestDispatcher("inActiveProducts.jsp").include(req, resp);

			} else {

				req.getRequestDispatcher("inActiveProducts.jsp").include(req, resp);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
