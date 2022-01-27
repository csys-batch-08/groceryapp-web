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

@WebServlet("/ModifiyProduct")
public class ModifiyProductServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int Pid = Integer.parseInt(req.getParameter("pID"));
		String products = req.getParameter("pName");
		Product product = new Product();
		product.setProductId(Pid);
		product.setProductName(products);
		ProductDaoImpl obj = new ProductDaoImpl();
		resp.setContentType("text/html");

		try {
			boolean flag = obj.changeName(product);
			if (flag) {

				req.getRequestDispatcher("ChangeProductPrice.jsp").include(req, resp);

			} else {

				req.getRequestDispatcher("ChangeProductPrice.jsp").include(req, resp);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}