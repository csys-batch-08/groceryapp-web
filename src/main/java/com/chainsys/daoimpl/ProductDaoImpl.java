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
import com.chainsys.util.GetConnection;

public class ProductDaoImpl implements Productinterface {

	int b1 = 0;

	public boolean addproduct(Product product) throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		String query = " insert into product (products_name,standard_cost,productsimage)values(?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, product.getProductName());
		stmt.setDouble(2, product.getProductPrice());
		stmt.setString(3, product.getProductImage());
		stmt.executeUpdate();
		return true;

	}

	public boolean delete(Product product) throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		String query = "update product set status=? where products_name=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, product.getProducStatus());
		stmt.setString(2, product.getProductName());
		stmt.executeUpdate();
		return true;

	}

	public boolean changePrice(Product product) throws ClassNotFoundException, SQLException {

		Connection con = GetConnection.getConnections();
		String query = "update product set standard_cost =? where products_name=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setDouble(1, product.getProductPrice());
		stmt.setString(2, product.getProductName());
		stmt.executeUpdate();

		return true;
	}

	public boolean changeName(Product product) throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		String query = "update product set products_name = ? where products_id= ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, product.getProductName());
		stmt.setInt(2, product.getProductId());
		stmt.executeUpdate();

		return true;
	}

	public List<Product> ViewAllProducts() throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		Statement stmt = con.createStatement();
		List<Product> productList = new ArrayList<Product>();
		String view = " SELECT products_name,standard_cost FROM product where status ='y' or status ='Y'";
		ResultSet rs = stmt.executeQuery(view);
		while (rs.next()) {

			Product product = new Product(rs.getString(1), rs.getDouble(2));
			productList.add(product);
		}
		return productList;
	}

	public List<Product> ViewAllProducte() throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		Statement stmt = con.createStatement();
		List<Product> productList1 = new ArrayList<Product>();
		String view = " SELECT products_name,standard_cost,products_id,Productsimage FROM product where status ='y' or status ='Y'";
		ResultSet rs = stmt.executeQuery(view);
		while (rs.next()) {

			Product product = new Product(rs.getInt(3), rs.getString(1), rs.getDouble(2), rs.getString(4));
			productList1.add(product);
		}
		return productList1;

	}

	public List<Product> AdminViewAllProducts() throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		Statement stmt = con.createStatement();
		List<Product> productList = new ArrayList<Product>();
		String view = " SELECT products_name,products_id,standard_cost, status,Productsimage FROM product";
		ResultSet rs = stmt.executeQuery(view);
		while (rs.next()) {

			Product product = new Product();
			product.setProductImage(rs.getString(5));
			product.setProductName(rs.getString(1));
			product.setProductId(rs.getInt(2));
			product.setProductPrice(rs.getDouble(3));
			product.setProducStatus(rs.getString(4));
			productList.add(product);
		}
		return productList;
	}

	public int gettingRate(Product product) throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		String query = " select standard_cost from product where products_id=? ";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, product.getProductId());
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			b1 = rs.getInt("standard_cost");

		}

		return b1;
	}

	public List<Product> sortproduct(Product product) throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		List<Product> sortproductlist = new ArrayList<Product>();
		String query = " SELECT  products_name,standard_cost,products_id,Productsimage FROM product where status in('y' ,'Y') and upper( products_name) LIKE upper(?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, "%" + product.getProductName() + "%");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			product = new Product();
			product.setProductName(rs.getString(1));
			product.setProductPrice(rs.getDouble(2));
			product.setProductId(rs.getInt(3));
			product.setProductImage(rs.getString(4));
			sortproductlist.add(product);

		}
		return sortproductlist;

	}

}