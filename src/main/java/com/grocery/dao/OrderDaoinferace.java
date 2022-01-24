package com.grocery.dao;

import java.sql.SQLException;
import java.util.List;

import com.grocery.model.Feature;
import com.grocery.model.Order;

public interface OrderDaoinferace {
	public void creatingOrderId(Order str);
	public int GettingOrderID(Order str);
	public List<Feature> todaySale() throws ClassNotFoundException, SQLException;
	public List<Feature> weekSale() throws ClassNotFoundException, SQLException;
	public List<Order> orderdetail(Order order) throws ClassNotFoundException, SQLException;
	public List<Feature> userOrderDetails(Order order) throws ClassNotFoundException, SQLException;
	public double todaySales() throws ClassNotFoundException, SQLException;
	public double weekSales() throws ClassNotFoundException, SQLException;
}
