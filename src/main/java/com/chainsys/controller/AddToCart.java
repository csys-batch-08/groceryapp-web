package com.chainsys.controller;

import java.io.IOException;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		response.setContentType("text/html");
		Order order = new Order();
		OrderDaoImpl obj = new OrderDaoImpl();
		Customer customer = (Customer) session.getAttribute("logincustomer");
		int productId = 0;
		int customerId = 0;
		try {
			String productIdStr = request.getParameter("productId");
			productId = Integer.parseInt(productIdStr);
			customerId = customer.getCustomerid();

			order.setCustomerid(customerId);
		} catch (NumberFormatException e1) {

			System.out.println(e1.getMessage());
		}

		int orderid = 0;

		orderid = obj.cartCheck(order);

		// check user if order id already exist in cart
		if (!(orderid > 0)) {

			order.setCustomerid(customerId);
			order.setStatus("in cart");

			obj.creatingOrderId(order);
			orderid = obj.GettingOrderID(order);

		}
		Cart stt = new Cart();
		stt.setOrderid(orderid);
		stt.setProductid(productId);

		CartDaoImpl obj1 = new CartDaoImpl();
		int quantity = 0;

		quantity = obj1.check(stt);

		if (quantity > 0 && !(quantity > 9)) {// check quantity

			stt.setQuantity(quantity + 1);

			obj1.changeQuantity(stt);

			try {
				response.sendRedirect("CustomerviewServlet");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		} else if (quantity > 9) {

			try {
				response.sendRedirect("CustomerviewServlet");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} else {

			stt.setQuantity(1);
			stt.setPrice(0);

			obj1.addToCart(stt);

			try {
				response.sendRedirect("CustomerviewServlet");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			;

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

}
