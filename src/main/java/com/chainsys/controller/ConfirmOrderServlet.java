package com.chainsys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.CartDaoImpl;
import com.chainsys.daoimpl.OrderDaoImpl;
import com.chainsys.daoimpl.ProductDaoImpl;
import com.chainsys.model.Cart;
import com.chainsys.model.Order;
import com.chainsys.model.Product;

@WebServlet("/ConformOrder")
public class ConfirmOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConfirmOrderServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		double value = 0;
		double offercost = 0;
		double offercostextra = 0;

		int orderid = (int) session.getAttribute("logincustomerorderId");
		Cart stt = new Cart();
		stt.setOrderid(orderid);
		CartDaoImpl obj = new CartDaoImpl();
		try {
			List<Integer> productidincart = obj.gettingproductidincart(stt);
			List<Integer> productcurrentprice = obj.gettingproductpriceincart(stt);

			for (int i = 0; i < productidincart.size(); i++) {
				Product product = new Product();
				product.setProductId(productidincart.get(i));
				ProductDaoImpl obj1 = new ProductDaoImpl();
				value = obj1.gettingRate(product);
				offercostextra = productcurrentprice.get(i) * value;
				offercost += offercostextra;

			}

			if (offercost > 499 && (offercost < 999)) {

				List<Integer> productidincartfive = obj.gettingproductidincart(stt);
				for (int i = 0; i < productidincartfive.size(); i++) {
					Product product = new Product();
					product.setProductId(productidincartfive.get(i));
					ProductDaoImpl obj1 = new ProductDaoImpl();
					value = obj1.gettingRate(product);
					value = value - (value * 0.05);
					stt.setOrderid(orderid);
					stt.setPrice(value);
					stt.setProductid(productidincartfive.get(i));
					obj.insertcurrentvalue(stt);

				}
			} else if (offercost >= 999) {
				List<Integer> productidincartten = obj.gettingproductidincart(stt);
				for (int i = 0; i < productidincartten.size(); i++) {
					Product product = new Product();
					product.setProductId(productidincartten.get(i));
					ProductDaoImpl obj1 = new ProductDaoImpl();
					value = obj1.gettingRate(product);
					value = value - (value * 0.1);
					stt.setOrderid(orderid);
					stt.setPrice(value);
					stt.setProductid(productidincartten.get(i));
					obj.insertcurrentvalue(stt);

				}
			} else {
				List<Integer> productidincartnormal = obj.gettingproductidincart(stt);
				for (int i = 0; i < productidincartnormal.size(); i++) {
					Product product = new Product();
					product.setProductId(productidincartnormal.get(i));
					ProductDaoImpl obj1 = new ProductDaoImpl();
					value = obj1.gettingRate(product);
					stt.setOrderid(orderid);
					stt.setPrice(value);
					stt.setProductid(productidincartnormal.get(i));
					obj.insertcurrentvalue(stt);

				}
			}
			Order order = new Order();
			order.setOrderid(orderid);
			OrderDaoImpl obj3 = new OrderDaoImpl();
			obj3.makefinal(order);
			response.sendRedirect("adderPlacedSuccessfully.jsp");

		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

}
