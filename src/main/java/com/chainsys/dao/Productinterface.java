package com.chainsys.dao;

import java.sql.SQLException;
import java.util.List;

import com.chainsys.model.Product;

public interface Productinterface {
	public boolean addproduct(Product str) throws ClassNotFoundException, SQLException;
	public boolean delete(Product str) throws ClassNotFoundException, SQLException;
	public boolean changePrice(Product str) throws ClassNotFoundException, SQLException;
	public boolean changeName(Product str) throws ClassNotFoundException, SQLException;
	public List<Product> ViewAllProducts() throws ClassNotFoundException, SQLException;
	public  int gettingRate(Product str11) throws ClassNotFoundException, SQLException;
	public List<Product> AdminViewAllProducts() throws ClassNotFoundException, SQLException;
}
