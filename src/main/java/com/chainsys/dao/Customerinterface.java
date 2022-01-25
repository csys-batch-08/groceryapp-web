package com.chainsys.dao;

import java.sql.SQLException;
import java.util.List;

import com.chainsys.model.Customer;

public interface Customerinterface {
	public boolean signup(Customer customer) throws ClassNotFoundException, SQLException;
	public boolean changepassword(Customer customer) throws ClassNotFoundException, SQLException;
	public Customer login(Customer customer) throws ClassNotFoundException, SQLException;
	public List<Customer> viewallLoginUser() throws ClassNotFoundException, SQLException;

}
