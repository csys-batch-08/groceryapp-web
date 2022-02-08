package com.chainsys.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.daoimpl.ProductDaoImpl;
import com.chainsys.model.Product;

@WebServlet("/ModifiyProduct")
public class ModifiyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		Product product = new Product();
		ProductDaoImpl obj = new ProductDaoImpl();
		try {
			String productidstr = req.getParameter("pID");
			int productid = Integer.parseInt(productidstr);
			String products = req.getParameter("pName");

			product.setProductId(productid);
			product.setProductName(products);
		} catch (NumberFormatException e1) {

			System.out.println(e1.getMessage());
		}

		resp.setContentType("text/html");

		try {
			boolean flag = obj.changeName(product);
			if (flag) {
				resp.sendRedirect("ModifiyProductAdminServlet");

			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}