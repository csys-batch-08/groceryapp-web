package com.chainsys.dao;

import java.sql.SQLException;
import java.util.List;

import com.chainsys.model.Product;

public interface Productinterface {
	public boolean addproduct(Product Product) throws ClassNotFoundException, SQLException;
	public boolean delete(Product Product) throws ClassNotFoundException, SQLException;
	public boolean changePrice(Product Product) throws ClassNotFoundException, SQLException;
	public boolean changeName(Product Product) throws ClassNotFoundException, SQLException;
	public List<Product> ViewAllProducts() throws ClassNotFoundException, SQLException;
	public List<Product> ViewAllProducte() throws ClassNotFoundException, SQLException;
	public List<Product> AdminViewAllProducts() throws ClassNotFoundException, SQLException;
	public int gettingRate(Product product) throws ClassNotFoundException, SQLException;
	public List<Product> sortproduct(Product product) throws ClassNotFoundException, SQLException;
}
