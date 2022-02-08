package com.chainsys.controller;

import java.io.IOException;

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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		Cart stt = new Cart();
		CartDaoImpl obj = new CartDaoImpl();
		HttpSession session = request.getSession();
		int orderid = 0;
		int productid = 0;
		int quantity = 0;
		try {
			orderid = (int) session.getAttribute("logincustomerorderId");
			String pids = request.getParameter("pId");
			productid = Integer.parseInt(pids);

			stt.setOrderid(orderid);
			stt.setProductid(productid);
		} catch (NumberFormatException e2) {
			System.out.println(e2.getMessage());
		}

		quantity = obj.check(stt);

		if (quantity <= 10 && !(quantity == 1)) {// check quantity

			stt.setQuantity(quantity - 1);
			stt.setOrderid(orderid);
			stt.setProductid(productid);
			try {
				obj.changeQuantity(stt);
				response.sendRedirect("CartServlet");

			} catch (IOException e) {

				System.out.println(e.getMessage());
			}

		} else {
			try {
				response.sendRedirect("CartServlet");
			} catch (IOException e) {

				System.out.println(e.getMessage());
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
