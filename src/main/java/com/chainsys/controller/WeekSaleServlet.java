package com.chainsys.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.daoimpl.OrderDaoImpl;
import com.chainsys.dto.Feature;

@WebServlet("/WeekSaleServlet")
public class WeekSaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WeekSaleServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		String todaydates = null;
		String lastdates = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy ");
		Calendar cal = Calendar.getInstance();
		// get starting date
		cal.add(Calendar.DAY_OF_YEAR, -8);

		// loop adding one day in each iteration
		for (int i = 0; i < 8; i++) {
			cal.add(Calendar.DAY_OF_YEAR, 1);

			if (i == 1) {
				lastdates = sdf.format(cal.getTime());

			}
			if (i == 7) {
				todaydates = sdf.format(cal.getTime());

			}

		}
		request.setAttribute("lastdates", lastdates);
		request.setAttribute("todaydates", todaydates);

		OrderDaoImpl obj = new OrderDaoImpl();

		List<Feature> sale = obj.weekSale();
		request.setAttribute("sale", sale);

		double b = obj.weekSales();
		request.setAttribute("b", b);

		request.setAttribute("date", date);
		try {

			request.getRequestDispatcher("weekSale.jsp").forward(request, response);
		} catch (IOException | ServletException e) {

			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

}
