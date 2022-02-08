package com.chainsys.dao;

import java.util.List;

import com.chainsys.dto.Feature;
import com.chainsys.model.Order;

public interface OrderDaoinferace {
	public void creatingOrderId(final Order order);

	public int GettingOrderID(final Order order);

	public List<Feature> todaySale();

	public List<Feature> weekSale();

	public List<Feature> userOrderDetails(final Feature feature);

	public double todaySales();

	public double weekSales();

	public double userOrderDetailse(final Feature feature);

	public List<Order> orderdetail(final Order order);

	public int cartCheck(final Order order);

	public void makefinal(final Order order);

	public String status(final Order order);

	public boolean cancel(final Order order);

	public List<Order> graphsale();

	public List<Order> listoforder();

	public boolean accept(final Order order);

	public List<Feature> todaySalegraph();

	public List<Order> orderdetails();

}
