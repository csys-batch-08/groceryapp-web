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

@WebServlet("/ChangeProductPrice")
public class ChangeProductPriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String products = req.getParameter("pname");
		double price = Double.parseDouble(req.getParameter("price"));
		Product product = new Product();
		product.setProductName(products);
		product.setProductPrice(price);
		ProductDaoImpl obj = new ProductDaoImpl();
		resp.setContentType("text/html");
		try {
			boolean flag = obj.changePrice(product);
			if (flag) {
resp.sendRedirect("ChangeProductPriceAdminServlet");
				

			} else {

				resp.sendRedirect("ChangeProductPriceAdminServlet");
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

}
