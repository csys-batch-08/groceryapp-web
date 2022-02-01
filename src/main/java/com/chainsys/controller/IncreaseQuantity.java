package com.chainsys.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.CartDaoImpl;
import com.chainsys.model.Cart;

@WebServlet("/IncreaseQuantity")
public class IncreaseQuantity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IncreaseQuantity() {
		super();

	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 {
		Cart stt = new Cart();
		CartDaoImpl obj = new CartDaoImpl();
		HttpSession session = request.getSession();
		int qty=0;
		try {
			int oid = (int) session.getAttribute("logincustomerorderId");
			int pid = Integer.parseInt(request.getParameter("pId"));
			
			
			stt.setOrderid(oid);
			stt.setProductid(pid);
		} catch (NumberFormatException e2) {
			
			e2.printStackTrace();
		}
		
		try {
			qty = obj.check(stt);
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		if (qty > 0 && !(qty > 9)) {// check quantity

			stt.setQuantity(qty + 1);
			try {
				obj.incease(stt);
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			try {
				response.sendRedirect("CartServlet");
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		} else {
			try {
				response.sendRedirect("CartServlet");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 {

		doGet(request, response);
	}

}
