package com.chainsys.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.CartDaoImpl;
import com.chainsys.model.Cart;

@WebServlet("/Deleteproductincart")
public class Deleteproductincart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Deleteproductincart() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		Cart stt = new Cart();
		CartDaoImpl obj = new CartDaoImpl();
		HttpSession session = request.getSession();
		int productid = 0;
		int orderid = 0;
		response.setContentType("text/html");
		try {
			orderid = (int) session.getAttribute("logincustomerorderId");
			String pids = request.getParameter("pId");
			productid = Integer.parseInt(pids);

			stt.setOrderid(orderid);
			stt.setProductid(productid);
		} catch (NumberFormatException e1) {

			System.out.println(e1.getMessage());
		}

		try {
			boolean flag = obj.delete(stt);
			if (flag) {
				response.sendRedirect("CartServlet");
			}

		} catch (IOException e) {

			System.out.println(e.getMessage());
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

}
