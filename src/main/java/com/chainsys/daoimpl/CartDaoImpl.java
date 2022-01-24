package com.chainsys.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.chainsys.dao.CartInterface;
import com.chainsys.model.Cart;
import com.chainsys.model.Feature;
import com.chainsys.util.GetConnection;

public class CartDaoImpl implements CartInterface {
	Scanner sc = new Scanner(System.in);

	public void addToCart(Cart stt) throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		String query = "insert into cart(order_id,product_id,quantity,price) values (?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, stt.getOrderid());
		stmt.setInt(2, stt.getProductid());
		stmt.setInt(3, stt.getQuantity());
		stmt.setDouble(4, stt.getPrice());
		stmt.executeUpdate();
		System.out.println("order place success");

	}

	public void toShowOrder() throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		Statement stmt = con.createStatement();
		String view = "   SELECT p.products_name,c.quantity,c.price ,(c.quantity*c.price) as total from buses o join  bsus c on o.order_id=c.order_id join busesd p on p.products_id=c.product_id ";
		ResultSet rs = stmt.executeQuery(view);
		while (rs.next()) {
			System.out.println(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getInt(3) + " " + rs.getDouble(4));
			// Product product = new Product(rs.getInt(2), rs.getString(1),
			// rs.getDouble(3));

		}
	}

	public int check(Cart stt) throws ClassNotFoundException, SQLException {
		int b = 0;

		Connection con = GetConnection.getConnections();
		String query = "SELECT quantity FROM cart where order_id=? and product_id =?";
		PreparedStatement stmt = con.prepareStatement(query);

		stmt.setInt(1, stt.getOrderid());
		stmt.setInt(2, stt.getProductid());
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			b = rs.getInt(1);
		}
		return b;
	}

	public void incease(Cart stt) throws ClassNotFoundException, SQLException {
		int b = 0;

		Connection con = GetConnection.getConnections();
		String query = "  update  cart set quantity =? where order_id=? and product_id =?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, stt.getQuantity());
		stmt.setInt(2, stt.getOrderid());
		stmt.setInt(3, stt.getProductid());
		stmt.executeUpdate();
		System.out.println("quantity increases");

	}

	public List<Feature> showCartin(Feature feature) throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		List<Feature> incart = new ArrayList<Feature>();
		String query = "select p.products_name,c.quantity,p.standard_cost,(c.quantity*p.standard_cost),p.products_id,Productsimage as cost from order_details o join cart c on o.order_id =c.order_id join product p on p.products_id=c.product_id where o.order_id=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, feature.getOrderId());
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			feature = new Feature();
			feature.setProductName(rs.getString(1));
			feature.setQuantity(rs.getInt(2));
			feature.setPrice(rs.getDouble(3));
			feature.setCost(rs.getDouble(4));
			feature.setProductId(rs.getInt(5));
			feature.setProductImage(rs.getString(6));
			incart.add(feature);

		}
		return incart;
	}
	public double showCartinTotal (Feature feature) throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		double total=0;
		String query = "select sum(c.quantity*p.standard_cost) as cost from order_details o join cart c on o.order_id =c.order_id join product p on p.products_id=c.product_id where o.order_id=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, feature.getOrderId());
		ResultSet rs = stmt.executeQuery();
	
		while (rs.next()) {
			total = rs.getDouble(1);

		}
		return total;
	}
	public boolean delete(Cart stt) throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		String query = " delete FROM cart where product_id=? and order_id=? ";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, stt.getProductid());
		stmt.setInt(2, stt.getOrderid());
		stmt.executeUpdate();
		System.out.println("delete");
		return true;

	}
	public List<Integer>  gettingproductidincart (Cart stt) throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		List<Integer> ProductId = new ArrayList<Integer>();
		String query = " SELECT product_id FROM cart where order_id=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, stt.getOrderid());
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ProductId.add(rs.getInt(1));
		}
		return ProductId;
	}
	public void insertcurrentvalue(Cart stt) throws ClassNotFoundException, SQLException
	{
		Connection con = GetConnection.getConnections();
		String query = " update cart SET price = ?  where order_id=? and product_id=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setDouble(1, stt.getPrice());
		stmt.setInt(2, stt.getOrderid());
		stmt.setInt(3, stt.getProductid());
		stmt.executeUpdate();
		System.out.println("order value compleled ");
	}
	public List<Integer>  gettingproductpriceincart (Cart stt) throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		List<Integer> ProductIds = new ArrayList<Integer>();
		String query = " SELECT quantity FROM cart where order_id=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, stt.getOrderid());
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ProductIds.add(rs.getInt(1));
		}
		return ProductIds;
	}
	
	}
