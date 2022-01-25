package com.chainsys.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.dao.Customerinterface;
import com.chainsys.model.Customer;

import com.chainsys.util.GetConnection;

public class CustomerDaoImpl implements Customerinterface {

	public boolean signup(Customer customer) throws ClassNotFoundException, SQLException {

		Connection con = GetConnection.getConnections();
		String query = "insert into customer (user_name ,password,first_name,last_name,address,phone, email) values(?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, customer.getUsername());
		stmt.setString(2, customer.getPassword());
		stmt.setString(3, customer.getFirstName());
		stmt.setString(4, customer.getLastName());
		stmt.setString(5, customer.getAddress());
		stmt.setLong(6, customer.getPhonenumber());
		stmt.setString(7, customer.getEmailid());
		stmt.executeUpdate();

		return true;

	}

	public boolean changepassword(Customer customer) throws ClassNotFoundException, SQLException {

		Connection con = GetConnection.getConnections();
		String query = "update  customer set password = ? where phone=?";
		PreparedStatement stmt = con.prepareStatement(query);

		stmt.setString(1, customer.getPassword());
		stmt.setDouble(2, customer.getPhonenumber());
		stmt.executeUpdate();

		return true;
	}

	public Customer login(Customer customer) throws ClassNotFoundException, SQLException {

		Connection con = GetConnection.getConnections();
		String query = "select user_name,first_name,last_name,address,phone,email,customer_id from customer where phone= ? and password= ? ";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setLong(1, customer.getPhonenumber());
		stmt.setString(2, customer.getPassword());

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {

			customer.setUsername(rs.getString(1));
			customer.setFirstName(rs.getString(2));
			customer.setLastName(rs.getString(3));
			customer.setAddress(rs.getString(4));
			customer.setPhonenumber(rs.getLong(5));
			customer.setEmailid(rs.getString(6));
			customer.setCustomerid(rs.getInt(7));

		}
		return customer;

	}

	public List<Customer> viewallLoginUser() throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		String query = "select user_name,first_name,last_name,address,phone,email,customer_id from customer";
		PreparedStatement stmt = con.prepareStatement(query);
		List<Customer> userList = new ArrayList<Customer>();

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Customer customer = new Customer();
			customer.setUsername(rs.getString(1));
			customer.setFirstName(rs.getString(2));
			customer.setLastName(rs.getString(3));
			customer.setAddress(rs.getString(4));
			customer.setPhonenumber(rs.getLong(5));
			customer.setEmailid(rs.getString(6));
			customer.setCustomerid(rs.getInt(7));
			userList.add(customer);
		}

		return userList;
	}

}