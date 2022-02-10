package com.chainsys.dto;

public class Feature {
	private String ProductName;
	private String ProductImage;
	private double price;
	private int quantity;
	private double Cost;
	private double total;
	private int orderId;
	private int productId;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductImage() {
		return ProductImage;
	}

	public void setProductImage(String productImage) {
		this.ProductImage = productImage;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		this.ProductName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getCost() {
		return Cost;
	}

	public void setCost(double cost) {
		Cost = cost;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Feature(String productName, double price, int quantity, double cost, double total) {
		super();
		ProductName = productName;
		this.price = price;
		this.quantity = quantity;
		Cost = cost;
		this.total = total;
	}

	@Override
	public String toString() {
		return "Feature [ProductName=" + ProductName + ", price=" + price + ", quantity=" + quantity + ", Cost=" + Cost
				+ ", total=" + total + "]";
	}

	public Feature() {
		super();
	}

}
