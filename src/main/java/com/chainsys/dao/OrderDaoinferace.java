package com.chainsys.dao;

import java.sql.SQLException;
import java.util.List;

import com.chainsys.model.Feature;
import com.chainsys.model.Order;

public interface OrderDaoinferace {
	public void creatingOrderId(Order order);
	public int GettingOrderID(Order order);
	public List<Feature> todaySale() throws ClassNotFoundException, SQLException;
	public List<Feature> weekSale() throws ClassNotFoundException, SQLException;
	public List<Feature> userOrderDetails(Feature feature) throws ClassNotFoundException, SQLException;
	public double todaySales() throws ClassNotFoundException, SQLException;
	public double weekSales() throws ClassNotFoundException, SQLException;
	public List<Order> orderdetails() throws ClassNotFoundException, SQLException;
	public double userOrderDetailse(Feature feature) throws ClassNotFoundException, SQLException ;
	public List<Order> orderdetail(Order order) throws ClassNotFoundException, SQLException ;
	public int cartCheck(Order order) throws ClassNotFoundException, SQLException;
	public void makefinal(Order order);
	public String status(Order order);
	public boolean cancel(Order order);
	public List<Order> graphsale() throws ClassNotFoundException, SQLException;
	public List<Order> listoforder() throws ClassNotFoundException, SQLException ;
	public boolean accept(Order order);

}
