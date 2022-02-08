package com.chainsys.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.dao.CartInterface;
import com.chainsys.dto.Feature;
import com.chainsys.model.Cart;
import com.chainsys.util.CloseConnection;
import com.chainsys.util.GetConnection;

public class CartDaoImpl implements CartInterface {
	/**
	 * this method use to add product to cart
	 */
	public void addToCart(final Cart stt) {
		Connection con = null;
		PreparedStatement stmt = null;

		con = GetConnection.getConnections();
		String query = "insert into cart(order_id,product_id,quantity,price) values (?,?,?,?)";
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, stt.getOrderid());
			stmt.setInt(2, stt.getProductid());
			stmt.setInt(3, stt.getQuantity());
			stmt.setDouble(4, stt.getPrice());
			stmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con);
		}

	}

	/**
	 * this method use to get quantity for current order and product
	 */
	public int check(final Cart stt) {
		int b = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		con = GetConnection.getConnections();
		String query = "SELECT quantity FROM cart where order_id=? and product_id =?";
		try {
			stmt = con.prepareStatement(query);

			stmt.setInt(1, stt.getOrderid());
			stmt.setInt(2, stt.getProductid());
			rs = stmt.executeQuery();
			if (rs.next()) {
				b = rs.getInt("quantity");
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con, rs);
		}
		return b;
	}

	/**
	 * this method use to increase/decrease quantity of product
	 */
	public void changeQuantity(final Cart stt) {
		Connection con = null;
		PreparedStatement stmt = null;

		con = GetConnection.getConnections();
		String query = "  update  cart set quantity =? where order_id=? and product_id =?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, stt.getQuantity());
			stmt.setInt(2, stt.getOrderid());
			stmt.setInt(3, stt.getProductid());
			stmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con);
		}

	}

	/**
	 * this method use to show product in cart
	 */
	public List<Feature> showCartin(final Feature feature) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Feature features = null;

		con = GetConnection.getConnections();
		List<Feature> incart = new ArrayList<Feature>();
		StringBuilder sb = new StringBuilder();
		sb.append("select p.products_name as products_name,");
		sb.append("c.quantity as quantity,");
		sb.append("p.standard_cost as standard_cost,");
		sb.append("(c.quantity*p.standard_cost) as total,");
		sb.append("p.products_id as products_id,");
		sb.append("Productsimage as cost");
		sb.append(" ");
		sb.append(" from order_details o");
		sb.append(" join cart c on o.order_id =c.order_id ");
		sb.append("join product p on p.products_id=c.product_id ");
		sb.append("where o.order_id=?");
		String singleString = sb.toString();
		try {
			stmt = con.prepareStatement(singleString);
			stmt.setInt(1, feature.getOrderId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				features = new Feature();
				features.setProductName(rs.getString("products_name"));
				features.setQuantity(rs.getInt("quantity"));
				features.setPrice(rs.getDouble("standard_cost"));
				features.setCost(rs.getDouble("total"));
				features.setProductId(rs.getInt("products_id"));
				features.setProductImage(rs.getString("cost"));
				incart.add(features);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con, rs);
		}
		return incart;
	}

	/**
	 * this method use to show product total in cart
	 */
	public double showCartinTotal(final Feature feature) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		con = GetConnection.getConnections();
		double total = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("select sum(c.quantity*p.standard_cost) as cost");
		sb.append(" ");
		sb.append(" from order_details o");
		sb.append(" join cart c on o.order_id =c.order_id ");
		sb.append("join product p on p.products_id=c.product_id ");
		sb.append("where o.order_id=?");
		String query = sb.toString();

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, feature.getOrderId());
			rs = stmt.executeQuery();

			while (rs.next()) {
				total = rs.getDouble("cost");

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con, rs);
		}
		return total;
	}

	/**
	 * this method use to remove product from cart
	 */
	public boolean delete(final Cart stt) {
		Connection con = null;
		PreparedStatement stmt = null;

		con = GetConnection.getConnections();
		String query = " delete FROM cart where product_id=? and order_id=? ";
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, stt.getProductid());
			stmt.setInt(2, stt.getOrderid());
			stmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con);
		}

		return true;

	}

	/**
	 * this method use to get list of product id from cart
	 */
	public List<Integer> gettingproductidincart(final Cart stt) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		con = GetConnection.getConnections();
		List<Integer> ProductId = new ArrayList<Integer>();
		String query = " SELECT product_id FROM cart where order_id=?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, stt.getOrderid());
			rs = stmt.executeQuery();
			while (rs.next()) {
				ProductId.add(rs.getInt("product_id"));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con, rs);
		}
		return ProductId;
	}

	/**
	 * this method use to update current price to product
	 */
	public void insertcurrentvalue(final Cart stt) {
		Connection con = null;
		PreparedStatement stmt = null;
		con = GetConnection.getConnections();
		String query = " update cart SET price = ?  where order_id=? and product_id=?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setDouble(1, stt.getPrice());
			stmt.setInt(2, stt.getOrderid());
			stmt.setInt(3, stt.getProductid());
			stmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con);
		}

	}

	/**
	 * this method use to get quantity of product in cart
	 */
	public List<Integer> gettingproductpriceincart(final Cart stt) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		con = GetConnection.getConnections();
		List<Integer> ProductIds = new ArrayList<Integer>();
		String query = " SELECT quantity FROM cart where order_id=?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, stt.getOrderid());
			rs = stmt.executeQuery();
			while (rs.next()) {
				ProductIds.add(rs.getInt("quantity"));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con, rs);
		}
		return ProductIds;
	}

}
