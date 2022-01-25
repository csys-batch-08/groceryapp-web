package com.chainsys.dao;

import java.sql.SQLException;
import java.util.List;

import com.chainsys.model.Cart;
import com.chainsys.model.Feature;

public interface CartInterface {

	public void addToCart(Cart stt) throws ClassNotFoundException, SQLException;
	public int check(Cart stt) throws ClassNotFoundException, SQLException ;
	public void incease(Cart stt) throws ClassNotFoundException, SQLException ;
	public List<Feature> showCartin(Feature feature) throws ClassNotFoundException, SQLException;
	public double showCartinTotal(Feature feature) throws ClassNotFoundException, SQLException ;
	public boolean delete(Cart stt) throws ClassNotFoundException, SQLException ;
	public List<Integer> gettingproductidincart(Cart stt) throws ClassNotFoundException, SQLException;
	public void insertcurrentvalue(Cart stt) throws ClassNotFoundException, SQLException;
	public List<Integer> gettingproductpriceincart(Cart stt) throws ClassNotFoundException, SQLException;
}
