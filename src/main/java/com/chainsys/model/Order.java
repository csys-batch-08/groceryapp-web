package com.chainsys.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Order {
	private int orderid;
	private int customerid;
	private String status;
	private Date orderdate;

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getStatus() {
		return status;
	}

	public Order(int orderid, String status, Date orderdate) {
		super();
		this.orderid = orderid;
		this.status = status;
		this.orderdate = orderdate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(int orderid, int customerid, String status, Date orderdate) {
		super();
		this.orderid = orderid;
		this.customerid = customerid;
		this.status = status;
		this.orderdate = orderdate;
	}

}
