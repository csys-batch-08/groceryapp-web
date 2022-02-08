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
	/**
	 * this method use to sign up for new user
	 */
	public void signup(final Customer customer) {
		Connection con = null;
		PreparedStatement stmt = null;

		con = GetConnection.getConnections();
		StringBuilder sb = new StringBuilder();
		sb.append("insert into customer");
		sb.append("(user_name ,password,first_name,last_name,address,phone, email)");
		sb.append(" values(?,?,?,?,?,?,?)");

		String query = sb.toString();
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
		} finally {
			CloseConnection.close(stmt, con);
		}

	}

	/**
	 * this method use to change password for exist user
	 */

	public boolean changepassword(final Customer customer) {
		Connection con = null;
		PreparedStatement stmt = null;

		con = GetConnection.getConnections();
		String query = "update  customer set password = ? where phone=?";
		try {
			stmt = con.prepareStatement(query);

			stmt.setString(1, customer.getPassword());
			stmt.setDouble(2, customer.getPhonenumber());
			stmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con);
		}

		return true;
	}

	/**
	 * this method use to login exist user
	 */
	public Customer login(final Customer customer) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Customer customerObj = null;
		con = GetConnection.getConnections();
		StringBuilder sb = new StringBuilder();
		sb.append("select user_name,");
		sb.append("first_name,last_name,");
		sb.append("address,phone,email,");
		sb.append("customer_id");
		sb.append(" ");
		sb.append(" from customer where phone= ?");
		sb.append(" and password= ? ");
		String query = sb.toString();
		try {
			stmt = con.prepareStatement(query);
			stmt.setLong(1, customer.getPhonenumber());
			stmt.setString(2, customer.getPassword());
			rs = stmt.executeQuery();

			if (rs.next()) {
				customerObj = new Customer();
				customerObj.setUsername(rs.getString("user_name"));
				customerObj.setFirstName(rs.getString("first_name"));
				customerObj.setLastName(rs.getString("last_name"));
				customerObj.setAddress(rs.getString("address"));
				customerObj.setPhonenumber(rs.getLong("phone"));
				customerObj.setEmailid(rs.getString("email"));
				customerObj.setCustomerid(rs.getInt("customer_id"));

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
			throw new RuntimeException("unable to execute");
		} finally {
			CloseConnection.close(stmt, con, rs);
		}
		return customerObj;

	}

	/**
	 * this method use to show list of user sign up to admin
	 */
	public List<Customer> viewallLoginUser() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		con = GetConnection.getConnections();
		String query = "select user_name,first_name,last_name,address,phone,email,customer_id from customer";
		List<Customer> userList = null;
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
		} finally {
			CloseConnection.close(stmt, con, rs);
		}
		return userList;
	}

	/**
	 * this method use to check sign up for new user
	 */
	public int signupcheck(final Customer customer) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int customerid = 0;

		con = GetConnection.getConnections();
		String query = "select sum(customer_id)  from customer where phone=? or email =?";
		try {
			stmt = con.prepareStatement(query);

			stmt.setLong(1, customer.getPhonenumber());
			stmt.setString(2, customer.getEmailid());
			rs = stmt.executeQuery();
			while (rs.next()) {
				customerid = rs.getInt("sum(customer_id)");
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con);
		}
		return customerid;

	}

}