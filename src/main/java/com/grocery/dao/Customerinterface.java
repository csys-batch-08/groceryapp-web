package com.grocery.dao;

import java.sql.SQLException;
import java.util.List;

import com.grocery.model.Customer;

public interface Customerinterface {
	public boolean signup(Customer str1) throws ClassNotFoundException, SQLException;
	public boolean changepassword(Customer str3) throws ClassNotFoundException, SQLException;
	public Customer login(Customer str) throws ClassNotFoundException, SQLException;
	public void viewLoginUser(Customer str2) throws ClassNotFoundException, SQLException;
	public List<Customer> viewallLoginUser() throws ClassNotFoundException, SQLException;

}
