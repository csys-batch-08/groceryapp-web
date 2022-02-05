package com.chainsys.dao;

import java.util.List;

import com.chainsys.model.Feature;
import com.chainsys.model.Order;

public interface OrderDaoinferace {
	public void creatingOrderId(Order order);
	public int GettingOrderID(Order order);
	public List<Feature> todaySale() ;
	public List<Feature> weekSale() ;
	public List<Feature> userOrderDetails(Feature feature);
	public double todaySales() ;
	public double weekSales() ;
	public double userOrderDetailse(Feature feature);
	public List<Order> orderdetail(Order order) ;
	public int cartCheck(Order order) ;
	public void makefinal(Order order);
	public String status(Order order);
	public boolean cancel(Order order);
	public List<Order> graphsale() ;
	public List<Order> listoforder() ;
	public boolean accept(Order order);
	public List<Feature> todaySalegraph(); 
	public List<Order> orderdetails();

}
