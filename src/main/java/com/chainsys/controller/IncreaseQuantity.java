package com.chainsys.controller;

import java.io.IOException;

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
			
			System.out.println(e2.getMessage());
		}
		
		
			qty = obj.check(stt);
		
		
		if (qty > 0 && !(qty > 9)) {// check quantity

			stt.setQuantity(qty + 1);
			
				obj.incease(stt);
			
		
			try {
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
			 {

		doGet(request, response);
	}

}
