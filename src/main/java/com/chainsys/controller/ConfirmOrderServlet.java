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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 {

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

			if (offercost > 499 && (offercost < 999)) {
				
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
			response.sendRedirect("adderPlacedSuccessfully.jsp");

		}catch (IOException e) {
		
			System.out.println(e.getMessage());
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			{

		doGet(request, response);
	}

}
