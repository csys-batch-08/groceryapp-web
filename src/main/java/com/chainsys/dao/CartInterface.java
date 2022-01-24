package com.chainsys.dao;

import java.sql.SQLException;

import com.chainsys.model.Cart;

public interface CartInterface {

	public void addToCart(Cart stt) throws ClassNotFoundException, SQLException;
	public void toShowOrder() throws ClassNotFoundException, SQLException;
}
