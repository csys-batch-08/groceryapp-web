package com.grocery.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.grocery.dao.Productinterface;
import com.grocery.model.Feature;
import com.grocery.model.Product;
import com.grocery.util.GetConnection;



public class ProductDaoImpl implements Productinterface  {
	Scanner sc = new Scanner(System.in);
	int	b1 = 0;
	public boolean addproduct(Product products) throws ClassNotFoundException, SQLException {
			Connection con = GetConnection.getConnections();
			String query = " insert into product (products_name,standard_cost,productsimage)values(?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, products.getProductName());
			stmt.setDouble(2, products.getProductPrice());
			stmt.setString(3, products.getProductImage());
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
	public boolean changePrice(Product products) throws ClassNotFoundException, SQLException {

			Connection con = GetConnection.getConnections();
			String query = "update product set standard_cost =? where products_name=?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setDouble(1, products.getProductPrice());
			stmt.setString(2, products.getProductName());
			stmt.executeUpdate();
			System.out.println("Product price update");
			return true;
	}

	public boolean changeName(Product producte) throws ClassNotFoundException, SQLException  {
			Connection con = GetConnection.getConnections();
			String query = "update product set products_name = ? where products_id= ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, producte.getProductName());
			stmt.setInt(2, producte.getProductId());
			stmt.executeUpdate();
			System.out.println("hi7");
			System.out.println("Product name change");
			return true;
	}

	public List<Product>ViewAllProducts() throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		Statement stmt = con.createStatement();
		List<Product> productList = new ArrayList<Product>();
		String view = " SELECT products_name,standard_cost FROM product where status ='y' or status ='Y'";
		ResultSet rs = stmt.executeQuery(view);
		while (rs.next()) {
			// System.out.println(rs.getInt(2) + " " + rs.getString(1)+" "+ rs.getInt(3));
			Product product = new Product(rs.getString(1),rs.getDouble(2));
			productList.add(product);
		}
		return productList;
	}
		
		
		public List<Product>ViewAllProducte() throws ClassNotFoundException, SQLException {
			Connection con = GetConnection.getConnections();
			Statement stmt = con.createStatement();
			List<Product> productList1 = new ArrayList<Product>();
			String view = " SELECT products_name,standard_cost,products_id,Productsimage FROM product where status ='y' or status ='Y'";
			ResultSet rs = stmt.executeQuery(view);
			while (rs.next()) {
				// System.out.println(rs.getInt(2) + " " + rs.getString(1)+" "+ rs.getInt(3));
				Product product = new Product( rs.getInt(3),rs.getString(1),rs.getDouble(2),rs.getString(4));
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
			// System.out.println(rs.getInt(2) + " " + rs.getString(1)+" "+ rs.getInt(3));
			Product product = new Product();
			product.setProductImage(rs.getString(5));
			product.setProductName(rs.getString(1));
			product.setProductId(rs.getInt(2));
			product.setProductPrice( rs.getDouble(3));
			product.setProducStatus(rs.getString(4));
			productList.add(product);
		}
		return productList;
	}
	public  int gettingRate(Product product) throws ClassNotFoundException, SQLException
	{
			Connection con = GetConnection.getConnections();
			String query = " select standard_cost from product where products_id=? ";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1,product.getProductId() );
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				b1 = rs.getInt("standard_cost");
				//System.out.println(b);

			} else {
				System.out.println("error pr");
				System.exit(0);

			}
	         
			return b1;
	}	
	public List<Product> sortproduct(Product product) throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		List<Product> sortproductlist = new ArrayList<Product>();
		String query = " SELECT  products_name,standard_cost,products_id,Productsimage FROM product where status in('y' ,'Y') and  products_name LIKE ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, "%" + product.getProductName()+ "%" );
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