package com.chainsys.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.CartDaoImpl;
import com.chainsys.model.Cart;

@WebServlet("/DecreaseQuantity")
public class DecreaseQuantity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DecreaseQuantity() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		int oid = (int) session.getAttribute("logincustomerorderId");
		int pid = Integer.parseInt(request.getParameter("pId"));
		int qty = 0;
		Cart stt = new Cart();
		stt.setOrderid(oid);
		stt.setProductid(pid);
		CartDaoImpl obj = new CartDaoImpl();
		try {
			qty = obj.check(stt);
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		if (qty <= 10 && !(qty == 1)) {// check quantity

			stt.setQuantity(qty - 1);
			stt.setOrderid(oid);
			stt.setProductid(pid);
			try {
				obj.incease(stt);
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			response.sendRedirect("CartServlet");

		} else {
			response.sendRedirect("CartServlet");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
