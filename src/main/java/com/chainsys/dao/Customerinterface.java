package com.chainsys.dao;

import java.util.List;

import com.chainsys.model.Customer;

public interface Customerinterface {
	public void signup(final Customer customer);

	public boolean changepassword(final Customer customer);

	public Customer login(final Customer customer);

	public List<Customer> viewallLoginUser();

}
