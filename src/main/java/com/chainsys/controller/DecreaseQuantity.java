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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 {
		Cart stt = new Cart();
		CartDaoImpl obj = new CartDaoImpl();
		HttpSession session = request.getSession();
		int oid=0;
		int pid=0;
		int qty=0;
		try {
			oid = (int) session.getAttribute("logincustomerorderId");
			String pids = request.getParameter("pId");
			pid = Integer.parseInt(pids);
			
			
			stt.setOrderid(oid);
			stt.setProductid(pid);
		} catch (NumberFormatException e2) {
			System.out.println(e2.getMessage());
		}
		
		
			qty = obj.check(stt);
		
		if (qty <= 10 && !(qty == 1)) {// check quantity

			stt.setQuantity(qty - 1);
			stt.setOrderid(oid);
			stt.setProductid(pid);
			try {
				obj.incease(stt);
				response.sendRedirect("CartServlet");
			
			} catch (IOException e) {
				
				System.out.println(e.getMessage());
			}
			
		}
		else
		{
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
