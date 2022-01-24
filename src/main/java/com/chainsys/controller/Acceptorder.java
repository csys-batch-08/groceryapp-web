package com.chainsys.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.daoimpl.OrderDaoImpl;
import com.chainsys.model.Order;

/**
 * Servlet implementation class Acceptorder
 */
@WebServlet("/Accept")
public class Acceptorder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Acceptorder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean flag= false;
		int ordid= Integer.parseInt(request.getParameter("orderId"));
		Order order= new Order();
		order.setOrderid(ordid);
		OrderDaoImpl obj = new OrderDaoImpl();
	flag=	obj.accept(order);
		if(flag)
		{
			response.sendRedirect("TodayOrders.jsp");

		}
		else
		{
			
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
