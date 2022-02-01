package com.chainsys.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.CartDaoImpl;
import com.chainsys.daoimpl.OrderDaoImpl;
import com.chainsys.model.Cart;
import com.chainsys.model.Customer;
import com.chainsys.model.Order;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddToCart() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		 {

		HttpSession session = request.getSession();
		response.setContentType("text/html");

		Customer customer = (Customer) session.getAttribute("logincustomer");
	String pids = request.getParameter("orderId");
		int pid = Integer.parseInt(pids);
		int cid = customer.getCustomerid();
		Order order = new Order();
		order.setCustomerid(cid);
		OrderDaoImpl obj = new OrderDaoImpl();
		int oid = 0;
		try {
			oid = obj.cartCheck(order);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// check user if order id already exist in cart
		if (!(oid > 0)) {

			order.setCustomerid(cid);
			order.setStatus("in cart");

			obj.creatingOrderId(order);
			oid = obj.GettingOrderID(order);

		}
		Cart stt = new Cart();
		stt.setOrderid(oid);
		stt.setProductid(pid);

		CartDaoImpl obj1 = new CartDaoImpl();
		int qty = 0;
		try {
			qty = obj1.check(stt);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (qty > 0 && !(qty > 9)) {// check quantity

			stt.setQuantity(qty + 1);
			try {
				obj1.incease(stt);
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} catch (SQLException e) {

				e.printStackTrace();
			}

			try {
				response.sendRedirect("CustomerviewServlet");
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (qty > 9) {

			try {
				response.sendRedirect("CustomerviewServlet");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

			stt.setQuantity(1);
			stt.setPrice(0);
			try {
				obj1.addToCart(stt);
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} catch (SQLException e) {

				e.printStackTrace();
			}

			try {
				response.sendRedirect("CustomerviewServlet");
			} catch (IOException e) {
				e.printStackTrace();
			}
			;

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			{

		doGet(request, response);
	}

}
