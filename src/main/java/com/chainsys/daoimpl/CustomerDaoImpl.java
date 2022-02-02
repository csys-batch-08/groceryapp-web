package com.chainsys.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.dao.Customerinterface;
import com.chainsys.model.Customer;
import com.chainsys.util.CloseConnection;
import com.chainsys.util.GetConnection;

public class CustomerDaoImpl implements Customerinterface {

	public boolean signup(Customer customer) {
		Connection con = null;
		PreparedStatement stmt= null;
		

		 con = GetConnection.getConnections();
		String query = "insert into customer (user_name ,password,first_name,last_name,address,phone, email) values(?,?,?,?,?,?,?)";
		 try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, customer.getUsername());
			stmt.setString(2, customer.getPassword());
			stmt.setString(3, customer.getFirstName());
			stmt.setString(4, customer.getLastName());
			stmt.setString(5, customer.getAddress());
			stmt.setLong(6, customer.getPhonenumber());
			stmt.setString(7, customer.getEmailid());
			stmt.executeUpdate();
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		 finally {
				CloseConnection.close(stmt, con);
			}
		return true;

	}

	public boolean changepassword(Customer customer) {
		Connection con = null;
		PreparedStatement stmt= null;
		
		con = GetConnection.getConnections();
		String query = "update  customer set password = ? where phone=?";
		 try {
			stmt = con.prepareStatement(query);

			stmt.setString(1, customer.getPassword());
			stmt.setDouble(2, customer.getPhonenumber());
			stmt.executeUpdate();
		} catch (SQLException e) {
		
			System.out.println(e.getMessage());
		}
		 finally {
				CloseConnection.close(stmt, con);
			}

		return true;
	}

	public Customer login(Customer customer)  {
		Connection con = null;
		PreparedStatement stmt= null;
		ResultSet rs=null;

		 con = GetConnection.getConnections();
		String query = "select user_name,first_name,last_name,address,phone,email,customer_id from customer where phone= ? and password= ? ";
		try {
			stmt = con.prepareStatement(query);
			stmt.setLong(1, customer.getPhonenumber());
			stmt.setString(2, customer.getPassword());

			 rs = stmt.executeQuery();

			if (rs.next()) {

				customer.setUsername(rs.getString(1));
				customer.setFirstName(rs.getString(2));
				customer.setLastName(rs.getString(3));
				customer.setAddress(rs.getString(4));
				customer.setPhonenumber(rs.getLong(5));
				customer.setEmailid(rs.getString(6));
				customer.setCustomerid(rs.getInt(7));

			}
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		finally {
			CloseConnection.close(stmt, con, rs);
		}
		return customer;

	}

	public List<Customer> viewallLoginUser()  {
		Connection con = null;
		PreparedStatement stmt= null;
		ResultSet rs=null;
		 con = GetConnection.getConnections();
		String query = "select user_name,first_name,last_name,address,phone,email,customer_id from customer";
		 List<Customer> userList=null;
		try {
			stmt = con.prepareStatement(query);
			userList = new ArrayList<Customer>();

			 rs = stmt.executeQuery();
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
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		finally {
			CloseConnection.close(stmt, con, rs);
		}
		return userList;
	}

}