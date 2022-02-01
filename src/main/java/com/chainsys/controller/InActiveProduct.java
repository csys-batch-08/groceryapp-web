package com.chainsys.controller;

import java.io.IOException;

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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
		ProductDaoImpl obj = new ProductDaoImpl();
		Product product = new Product();
		try {
			String productname = req.getParameter("pname");
			String productstatus = req.getParameter("status");
			
			product.setProductName(productname);
			product.setProducStatus(productstatus);
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		

		resp.setContentType("text/html");
		try {

			boolean flag = obj.delete(product);
			if (flag) {

				req.getRequestDispatcher("inActiveProducts.jsp").include(req, resp);

			} else {

				req.getRequestDispatcher("inActiveProducts.jsp").include(req, resp);
			}
		} catch ( ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			{

		doGet(request, response);
	}
}
