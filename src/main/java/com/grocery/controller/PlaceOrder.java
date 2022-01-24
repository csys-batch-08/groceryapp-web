package com.grocery.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.grocery.model.Customer;
import com.grocery.model.Order;
import com.grocery.model.Product;

/**
 * Servlet implementation class PlaceOrder
 */
@WebServlet("/PlaceOrder")
public class PlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		ProductDaoImpl obj =new ProductDaoImpl();
		 PrintWriter out=response.getWriter();
		try {
			List<Product> productList =obj.ViewAllProducte();
			List<Integer> productid = new ArrayList<Integer> ();
			List<Integer> productquantiy = new ArrayList<Integer>();
			 response.setContentType("text/html");
						
			for(Product p:productList)
			{
				int qty=Integer.parseInt(request.getParameter(p.getProductName()));
				if(qty>0) {
				productid.add(p.getProductId());
				productquantiy.add(qty);
				}
				
			}
			int productprice;
			Order str = new Order();  
			  HttpSession session = request.getSession();
			  
			 Customer object =  (Customer) session.getAttribute("logincustomer");
			  
			
			str.setCustomerid(object.getCustomerid());
			str.setStatus("place order");
			OrderDaoImpl order = new OrderDaoImpl();
			order.creatingOrderId(str);
			// getting orderid
			int orderid = order.GettingOrderID(str);

			// break;
			for (int j = 0; j < productid.size(); j++) {
				ProductDaoImpl obj1 = new ProductDaoImpl();
				Product str11 = new Product();
				str11.setProductId(productid.get(j));
				productprice = obj1.gettingRate(str11);
				Cart stt = new Cart();
				stt.setOrderid(orderid);
				stt.setProductid(productid.get(j));
				stt.setQuantity(productquantiy.get(j));
				stt.setPrice(productprice);
				CartDaoImpl obj5 = new CartDaoImpl();
				obj5.addToCart(stt);
			}
			out.print("order place Sucessfully"); 
			response.sendRedirect("CustomerView.jsp");
		
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
