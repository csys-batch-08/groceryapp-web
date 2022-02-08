package com.chainsys.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.chainsys.model.Customer;
import com.chainsys.model.Order;
import com.chainsys.model.Product;

@WebServlet("/PlaceOrder")
public class PlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PlaceOrder() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		ProductDaoImpl obj = new ProductDaoImpl();

		try {
			List<Product> productList = obj.ViewAllProducte();
			List<Integer> productid = new ArrayList<Integer>();
			List<Integer> productquantiy = new ArrayList<Integer>();
			response.setContentType("text/html");

			for (Product p : productList) {
				int qunatity;

				String quantitystr = request.getParameter(p.getProductName());
				qunatity = Integer.parseInt(quantitystr);

				if (qunatity > 0) {
					productid.add(p.getProductId());
					productquantiy.add(qunatity);
				}

			}
			int productprice;
			Order order = new Order();
			HttpSession session = request.getSession();

			Customer object = (Customer) session.getAttribute("logincustomer");

			order.setCustomerid(object.getCustomerid());
			order.setStatus("place order");
			OrderDaoImpl obj1 = new OrderDaoImpl();
			obj1.creatingOrderId(order);
			// getting orderid
			int orderid = obj1.GettingOrderID(order);

			for (int j = 0; j < productid.size(); j++) {
				ProductDaoImpl obj2 = new ProductDaoImpl();
				Product product = new Product();
				product.setProductId(productid.get(j));
				productprice = obj2.gettingRate(product);
				Cart stt = new Cart();
				stt.setOrderid(orderid);
				stt.setProductid(productid.get(j));
				stt.setQuantity(productquantiy.get(j));
				stt.setPrice(productprice);
				CartDaoImpl obj5 = new CartDaoImpl();
				obj5.addToCart(stt);
			}

			response.sendRedirect("CustomerviewServlet");

		} catch (IOException e) {

			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {

			System.out.println(e.getMessage());
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

}
