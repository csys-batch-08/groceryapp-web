package com.chainsys.model;

import java.sql.Date;

public class Order {
	private int orderId;
	private int customerId;
	private String status;
	private Date orderDate;

	public int getOrderid() {
		return orderId;
	}

	public void setOrderid(int orderid) {
		this.orderId = orderid;
	}

	public int getCustomerid() {
		return customerId;
	}

	public void setCustomerid(int customerid) {
		this.customerId = customerid;
	}

	public String getStatus() {
		return status;
	}

	public Order(int orderid, String status, Date orderdate) {
		super();
		this.orderId = orderid;
		this.status = status;
		this.orderDate = orderdate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOrderdate() {
		return orderDate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderDate = orderdate;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(int orderid, int customerid, String status, Date orderdate) {
		super();
		this.orderId = orderid;
		this.customerId = customerid;
		this.status = status;
		this.orderDate = orderdate;
	}

}
