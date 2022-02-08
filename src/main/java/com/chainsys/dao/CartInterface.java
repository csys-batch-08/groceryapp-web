package com.chainsys.dao;

import java.util.List;

import com.chainsys.dto.Feature;
import com.chainsys.model.Cart;

public interface CartInterface {

	public void addToCart(final Cart stt);

	public int check(final Cart stt);

	public void changeQuantity(final Cart stt);

	public List<Feature> showCartin(final Feature feature);

	public double showCartinTotal(final Feature feature);

	public boolean delete(final Cart stt);

	public List<Integer> gettingproductidincart(final Cart stt);

	public void insertcurrentvalue(final Cart stt);

	public List<Integer> gettingproductpriceincart(final Cart stt);
}
