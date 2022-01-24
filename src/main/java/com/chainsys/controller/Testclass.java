package com.chainsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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

/**
 * Servlet implementation class AddToCart
 */
public class Testclass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Testclass() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		response.setContentType("text/html");

	

		Customer customer = (Customer) session.getAttribute("logincustomer");
		int pid = Integer.parseInt(request.getParameter("orderId"));
		int cid = customer.getCustomerid();
		Order order = new Order();
		order.setCustomerid(cid);
		OrderDaoImpl obj = new OrderDaoImpl();
		int oid = 0;
		try {
			oid = obj.cartCheck(order);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// check user if order id already exist in cart
		if (!(oid > 0)) {
			Order str = new Order();

			str.setCustomerid(cid);
			str.setStatus("in cart");

			obj.creatingOrderId(str);
			oid = obj.GettingOrderID(str);

		}
		Cart stt = new Cart();
		stt.setOrderid(oid);
		stt.setProductid(pid);

		CartDaoImpl obj1 = new CartDaoImpl();
		int qty = 0;
		try {
			qty = obj1.check(stt);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (qty > 0&&qty!=10) {//check quantity

			stt.setQuantity(qty + 1);
			try {
				obj1.incease(stt);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("Increase quantity");
			request.getRequestDispatcher("CustomerView.jsp").include(request, response);


		} 
		else if(qty>10)
		{
			out.print("reached max quantity");
			request.getRequestDispatcher("CustomerView.jsp").include(request, response);	
		}
		else {

			stt.setQuantity(1);
			stt.setPrice(0);
			try {
				obj1.addToCart(stt);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("add to cart");
			request.getRequestDispatcher("CustomerView.jsp").include(request, response);
			

		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
