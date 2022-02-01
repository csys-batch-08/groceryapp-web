package com.chainsys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.daoimpl.OrderDaoImpl;
import com.chainsys.model.Feature;

@WebServlet("/WeekSaleServlet")
public class WeekSaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WeekSaleServlet() {
		super();

	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 {
		long millis=System.currentTimeMillis();  
		 java.sql.Date date = new java.sql.Date(millis);  
		HttpSession session = request.getSession();
		
		String todaydates =null;
		String lastdates=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy ");
		Calendar cal = Calendar.getInstance();
		// get starting date
		cal.add(Calendar.DAY_OF_YEAR, -8);

		// loop adding one day in each iteration
		for(int i = 0; i<8; i++){
					    cal.add(Calendar.DAY_OF_YEAR, 1);

		   if(i==1)
				   {
			    lastdates =sdf.format(cal.getTime());
			   
		   }
		    if(i==7)
		   {
		    	  todaydates =sdf.format(cal.getTime());

		   }
		    
		}
		session.setAttribute("lastdates", lastdates);
		session.setAttribute("todaydates", todaydates);

		 
		OrderDaoImpl obj = new OrderDaoImpl();
		try {
			List<Feature> sale = obj.weekSale();
			session.setAttribute("sale", sale);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		try {
			double b = obj.weekSales();
			session.setAttribute("b", b);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		session.setAttribute("date", date);
		try {
			response.sendRedirect("weekSale.jsp");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 {

		doGet(request, response);
	}

}
