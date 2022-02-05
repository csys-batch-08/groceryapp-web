package com.chainsys.dao;

import java.util.List;

import com.chainsys.model.Customer;

public interface Customerinterface {
	public boolean signup(Customer customer) ;
	public boolean changepassword(Customer customer) ;
	public Customer login(Customer customer) ;
	public List<Customer> viewallLoginUser() ;

}
