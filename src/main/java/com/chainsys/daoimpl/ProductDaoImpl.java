package com.chainsys.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.chainsys.dao.Productinterface;
import com.chainsys.model.Product;
import com.chainsys.util.CloseConnection;
import com.chainsys.util.GetConnection;

public class ProductDaoImpl implements Productinterface {

	int b1 = 0;

	/**
	 * this method use to add new product
	 */
	public boolean addproduct(final Product product) {
		Connection con = null;
		PreparedStatement stmt = null;
		con = GetConnection.getConnections();
		String query = " insert into product (products_name,standard_cost,productsimage)values(?,?,?)";
		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, product.getProductName());
			stmt.setDouble(2, product.getProductPrice());
			stmt.setString(3, product.getProductImage());
			stmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con);
		}
		return true;

	}

	/**
	 * this method use to disable/enable product
	 */
	public boolean delete(final Product product) {
		Connection con = null;
		PreparedStatement stmt = null;
		con = GetConnection.getConnections();
		String query = "update product set status=? where products_name=?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, product.getProducStatus());
			stmt.setString(2, product.getProductName());
			stmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con);
		}
		return true;

	}

	/**
	 * this method use to change price of product
	 */
	public boolean changePrice(final Product product) {
		Connection con = null;
		PreparedStatement stmt = null;

		con = GetConnection.getConnections();
		String query = "update product set standard_cost =? where products_name=?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setDouble(1, product.getProductPrice());
			stmt.setString(2, product.getProductName());
			stmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con);
		}
		return true;
	}

	/**
	 * this method use to change product name
	 */
	public boolean changeName(final Product product) {
		Connection con = null;
		PreparedStatement stmt = null;

		con = GetConnection.getConnections();
		String query = "update product set products_name = ? where products_id= ?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, product.getProductName());
			stmt.setInt(2, product.getProductId());
			stmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con);
		}
		return true;
	}

	/**
	 * this method use to show list of product to user
	 */
	public List<Product> ViewAllProducte() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		con = GetConnection.getConnections();
		List<Product> productList = null;
		try {
			stmt = con.createStatement();
			productList = new ArrayList<Product>();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT products_name,");
			sb.append("standard_cost,products_id,Productsimage");
			sb.append(" ");
			sb.append("FROM product");
			sb.append(" where status ='y' or status ='Y'");
			String query = sb.toString();
			rs = stmt.executeQuery(query);
			while (rs.next()) {

				Product product = new Product(rs.getInt("products_id"), rs.getString("products_name"),
						rs.getDouble("standard_cost"), rs.getString("Productsimage"));
				productList.add(product);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.closeStatement(stmt, con, rs);
		}
		return productList;

	}

	/**
	 * this method use to show list of product to admin
	 */
	public List<Product> AdminViewAllProducts() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		con = GetConnection.getConnections();
		List<Product> productList = null;
		try {
			stmt = con.createStatement();
			productList = new ArrayList<Product>();
			String view = " SELECT products_name,products_id,standard_cost, status,Productsimage FROM product";
			rs = stmt.executeQuery(view);
			while (rs.next()) {

				Product product = new Product();
				product.setProductImage(rs.getString("Productsimage"));
				product.setProductName(rs.getString("products_name"));
				product.setProductId(rs.getInt("products_id"));
				product.setProductPrice(rs.getDouble("standard_cost"));
				product.setProducStatus(rs.getString("status"));
				productList.add(product);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.closeStatement(stmt, con, rs);
		}
		return productList;
	}

	/**
	 * this method use to get current rate of product
	 */
	public int gettingRate(final Product product) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		con = GetConnection.getConnections();
		String query = " select standard_cost from product where products_id=? ";
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, product.getProductId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				b1 = rs.getInt("standard_cost");

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con, rs);
		}
		return b1;
	}

	/**
	 * this method use to search of product
	 */
	public List<Product> sortproduct(final Product product) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Product products = null;
		con = GetConnection.getConnections();
		List<Product> sortproductlist = new ArrayList<Product>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT products_name,");
		sb.append("standard_cost,products_id,Productsimage");
		sb.append(" ");
		sb.append("FROM product ");
		sb.append(" where status in('y' ,'Y')");
		sb.append("and upper( products_name) LIKE upper(?)");
		String query = sb.toString();
		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, "%" + product.getProductName() + "%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				products = new Product();
				products.setProductName(rs.getString("products_name"));
				products.setProductPrice(rs.getDouble("standard_cost"));
				products.setProductId(rs.getInt("products_id"));
				products.setProductImage(rs.getString("Productsimage"));
				sortproductlist.add(products);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con, rs);
		}
		return sortproductlist;

	}

}