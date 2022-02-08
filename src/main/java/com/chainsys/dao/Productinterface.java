package com.chainsys.dao;

import java.util.List;

import com.chainsys.model.Product;

public interface Productinterface {
	public boolean addproduct(final Product Product);

	public boolean delete(final Product Product);

	public boolean changePrice(final Product Product);

	public boolean changeName(final Product Product);

	public List<Product> ViewAllProducte();

	public List<Product> AdminViewAllProducts();

	public int gettingRate(final Product product);

	public List<Product> sortproduct(final Product product);
}
