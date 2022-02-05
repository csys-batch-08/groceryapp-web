package com.chainsys.dao;
import java.util.List;

import com.chainsys.model.Product;

public interface Productinterface {
	public boolean addproduct(Product Product) ;
	public boolean delete(Product Product) ;
	public boolean changePrice(Product Product) ;
	public boolean changeName(Product Product) ;
	public List<Product> ViewAllProducte() ;
	public List<Product> AdminViewAllProducts() ;
	public int gettingRate(Product product)  ;
	public List<Product> sortproduct(Product product) ;
}
