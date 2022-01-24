package com.grocery.dao;

import java.sql.SQLException;

import com.grocery.model.Cart;

public interface CartInterface {

	public void addToCart(Cart stt) throws ClassNotFoundException, SQLException;
	public void toShowOrder() throws ClassNotFoundException, SQLException;
}
