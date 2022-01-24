package com.grocery.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.grocery.dao.Customerinterface;
import com.grocery.model.Customer;
import com.grocery.model.Product;
import com.grocery.util.GetConnection;


public class CustomerDaoImpl implements Customerinterface {
	Scanner sc = new Scanner(System.in);

	public boolean signup(Customer str1) throws ClassNotFoundException, SQLException {

		Connection con = GetConnection.getConnections();
		String query = "insert into customer (user_name ,password,first_name,last_name,address,phone, email) values(?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, str1.getUsername());
		stmt.setString(2, str1.getPassword());
		stmt.setString(3, str1.getFirstName());
		stmt.setString(4, str1.getLastName());
		stmt.setString(5, str1.getAddress());
		stmt.setLong(6, str1.getPhonenumber());
		stmt.setString(7, str1.getEmailid());
		stmt.executeUpdate();
		System.out.println("successful ");
		return true;

	}

	public boolean changepassword(Customer customer) throws ClassNotFoundException, SQLException {

		Connection con = GetConnection.getConnections();
		String query = "update  customer set password = ? where phone=?";
		PreparedStatement stmt = con.prepareStatement(query);
		// System.out.println(str3.getCustomerid());
		stmt.setString(1, customer.getPassword());
		stmt.setDouble(2, customer.getPhonenumber());
		stmt.executeUpdate();
		System.out.println("password update successful ");
		return true;
	}

	public  Customer  login( Customer customer) throws ClassNotFoundException, SQLException {

		Connection con = GetConnection.getConnections();
		String query = "select user_name,first_name,last_name,address,phone,email,customer_id from customer where phone= ? and password= ? ";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setLong(1, customer.getPhonenumber());
		stmt.setString(2, customer.getPassword());
		// stmt.executeUpdate();

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			System.out.println("welcome " + rs.getString("first_name"));
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
//
	public void viewLoginUser(Customer str2) throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		String query = "select first_name,last_name,address,phone,email from customer where customer_id = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		

		// Customer str = new Customer();
		// System.out.println(str2.getFindcustomerid());
		stmt.setInt(1, str2.getCustomerid());
	
		// stmt.executeUpdate();
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			System.out.println("FIRSTNAME " + rs.getString("first_name") + "\nLASTNAME " + rs.getString("last_name")
					+ "\nADDRESS " + rs.getString("address") + "\nPHONE " + rs.getLong("phone") + "\nEMAIL "
					+ rs.getString("email"));
		} else {
			System.out.println("error");
		}
		

	}
	
	
	
	
	
	
	
	public List<Customer> viewallLoginUser() throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		String query = "select user_name,first_name,last_name,address,phone,email,customer_id from customer";
		PreparedStatement stmt = con.prepareStatement(query);
		List<Customer> userList = new ArrayList<Customer>();
		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
//			System.out.println("FIRSTNAME " + rs.getString("first_name") + "\nLASTNAME " + rs.getString("last_name")
//					+ "\nADDRESS " + rs.getString("address") + "\nPHONE " + rs.getLong("phone") + "\nEMAIL\n "
//					+ rs.getString("email"));
			Customer customer =new Customer();
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