package com.chainsys.controller;

import java.io.IOException;
import java.sql.SQLException;

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {

		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
		String Pids = req.getParameter("pID");
		int Pid = Integer.parseInt(Pids);
		String products = req.getParameter("pName");
		Product product = new Product();
		product.setProductId(Pid);
		product.setProductName(products);
		ProductDaoImpl obj = new ProductDaoImpl();
		resp.setContentType("text/html");

		try {
			boolean flag = obj.changeName(product);
			if (flag) {
				resp.sendRedirect("ModifiyProductAdminServlet");
				

			} else {
				resp.sendRedirect("ModifiyProductAdminServlet");
				
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}