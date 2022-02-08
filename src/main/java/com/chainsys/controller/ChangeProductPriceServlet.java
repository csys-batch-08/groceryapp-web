package com.chainsys.controller;

import java.io.IOException;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		Product product = new Product();
		ProductDaoImpl obj = new ProductDaoImpl();
		boolean flag = false;
		try {
			String products = req.getParameter("pname");
			String pricestr = req.getParameter("price");
			double price = Double.parseDouble(pricestr);

			product.setProductName(products);
			product.setProductPrice(price);
		} catch (NumberFormatException e1) {

			System.out.println(e1.getMessage());
		}

		resp.setContentType("text/html");
		try {
			flag = obj.changePrice(product);
			if (flag) {
				resp.sendRedirect("ChangeProductPriceAdminServlet");

			}
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
	}

}
