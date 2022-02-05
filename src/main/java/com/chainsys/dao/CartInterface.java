package com.chainsys.dao;

import java.util.List;

import com.chainsys.model.Cart;
import com.chainsys.model.Feature;

public interface CartInterface {

	public void addToCart(Cart stt) ;
	public int check(Cart stt)  ;
	public void changeQuantity(Cart stt) ;
	public List<Feature> showCartin(Feature feature);
	public double showCartinTotal(Feature feature)  ;
	public boolean delete(Cart stt) ;
	public List<Integer> gettingproductidincart(Cart stt);
	public void insertcurrentvalue(Cart stt) ;
	public List<Integer> gettingproductpriceincart(Cart stt) ;
}
