package com.chainsys.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ModifiyProductAdminServlet")
public class ModifiyProductAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifiyProductAdminServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			response.sendRedirect("modifiyProduct.jsp");
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

}
