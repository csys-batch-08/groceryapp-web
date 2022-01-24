package com.grocery.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.grocery.daoimpl.CartDaoImpl;
import com.grocery.daoimpl.OrderDaoImpl;
import com.grocery.daoimpl.ProductDaoImpl;
import com.grocery.model.Cart;
import com.grocery.model.Order;
import com.grocery.model.Product;

/**
 * Servlet implementation class ConformOrder
 */
@WebServlet("/ConformOrder")
public class ConformOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConformOrder() {
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
		HttpSession session = request.getSession();
		double value = 0;
		double offercost = 0;
		double offercoste = 0;

		int oid = (int) session.getAttribute("logincustomerorderId");
		Cart stt = new Cart();
		stt.setOrderid(oid);
		CartDaoImpl obj = new CartDaoImpl();
		try {
			List<Integer> prdids = obj.gettingproductidincart(stt);
			List<Integer> prdidse = obj.gettingproductpriceincart(stt);

			for (int i = 0; i < prdids.size(); i++) {
				Product product = new Product();
				product.setProductId(prdids.get(i));
				ProductDaoImpl obj1 = new ProductDaoImpl();
				value = obj1.gettingRate(product);
				offercoste = prdidse.get(i) * value;
				offercost += offercoste;

			}

			System.out.println(offercost);
			if (offercost > 499 && (offercost < 999)) {
				System.out.println(offercost);
				List<Integer> prdid = obj.gettingproductidincart(stt);
				for (int i = 0; i < prdid.size(); i++) {
					Product product = new Product();
					product.setProductId(prdid.get(i));
					ProductDaoImpl obj1 = new ProductDaoImpl();
					value = obj1.gettingRate(product);
					value = value - (value * 0.05);
					stt.setOrderid(oid);
					stt.setPrice(value);
					stt.setProductid(prdid.get(i));
					obj.insertcurrentvalue(stt);

				}
			} else if (offercost >= 999) {
				List<Integer> prdid = obj.gettingproductidincart(stt);
				for (int i = 0; i < prdid.size(); i++) {
					Product product = new Product();
					product.setProductId(prdid.get(i));
					ProductDaoImpl obj1 = new ProductDaoImpl();
					value = obj1.gettingRate(product);
					value = value - (value * 0.1);
					stt.setOrderid(oid);
					stt.setPrice(value);
					stt.setProductid(prdid.get(i));
					obj.insertcurrentvalue(stt);

				}
			} else {
				List<Integer> prdid = obj.gettingproductidincart(stt);
				for (int i = 0; i < prdid.size(); i++) {
					Product product = new Product();
					product.setProductId(prdid.get(i));
					ProductDaoImpl obj1 = new ProductDaoImpl();
					value = obj1.gettingRate(product);
					stt.setOrderid(oid);
					stt.setPrice(value);
					stt.setProductid(prdid.get(i));
					obj.insertcurrentvalue(stt);

				}
			}
			Order order = new Order();
			order.setOrderid(oid);
			OrderDaoImpl obj3 = new OrderDaoImpl();
			obj3.makefinal(order);
			response.sendRedirect("AdderPlacedSuccessfully.jsp");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
