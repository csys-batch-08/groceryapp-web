package com.chainsys.model;

public class Product {
	private int ProductId;
	private String ProductName;
	private Double ProductPrice;
	private String producStatus;
	private String ProductImage;

	public String getProductImage() {
		return ProductImage;
	}

	public void setProductImage(String productImage) {
		ProductImage = productImage;
	}

	public String getProducStatus() {
		return producStatus;
	}

	public void setProducStatus(String producStatus) {
		this.producStatus = producStatus;
	}

	public Product(int productId, String productName, Double productPrice) {
		super();
		ProductId = productId;
		ProductName = productName;
		ProductPrice = productPrice;
	}

	public void setProductPrice(Double productPrice) {
		ProductPrice = productPrice;
	}

	public int getProductId() {
		return ProductId;
	}

	public void setProductId(int productId) {
		ProductId = productId;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public Product(String productName, Double productPrice) {
		super();
		ProductName = productName;
		ProductPrice = productPrice;
	}

	public Product(int productId, String productName, Double productPrice, String producStatus) {
		super();
		this.ProductId = productId;
		this.ProductName = productName;
		this.ProductPrice = productPrice;
		this.producStatus = producStatus;
	}

	public Double getProductPrice() {
		return ProductPrice;
	}

	public void setProductPrice(double price) {
		ProductPrice = price;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "\nProductId=" + ProductId + "\nProductName=" + ProductName + "\nProductPrice=" + ProductPrice;
	}

}
