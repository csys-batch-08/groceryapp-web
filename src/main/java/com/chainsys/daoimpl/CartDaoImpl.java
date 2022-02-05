package com.chainsys.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.dao.CartInterface;
import com.chainsys.model.Cart;
import com.chainsys.model.Feature;
import com.chainsys.util.CloseConnection;
import com.chainsys.util.GetConnection;

public class CartDaoImpl implements CartInterface {
	/**
	 * this method use to add product to cart
	 */
	public void addToCart(Cart stt)  {
		Connection con = null;
		PreparedStatement stmt= null;
	
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
		}
		finally {
			CloseConnection.close(stmt, con);
		}
		

	}
	/**
	 * this method use to get quantity for current order and product
	 */
	public int check(Cart stt)  {
		int b = 0;
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		con = GetConnection.getConnections();
		String query = "SELECT quantity FROM cart where order_id=? and product_id =?";
		try {
			stmt = con.prepareStatement(query);

			stmt.setInt(1, stt.getOrderid());
			stmt.setInt(2, stt.getProductid());
   rs = stmt.executeQuery();
			if (rs.next()) {
				b = rs.getInt(1);
			}
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		finally {
			CloseConnection.close(stmt, con, rs);
		}
		return b;
	}
	/**
	 * this method use to increase/decrease quantity of product
	 */
	public void changeQuantity(Cart stt) {
		Connection con = null;
		PreparedStatement stmt=null;
		
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
		}
		 finally {
				CloseConnection.close(stmt, con);
			}

	}
	/**
	 * this method use to show product in cart
	 */
	public List<Feature> showCartin(Feature feature)  {
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		 con = GetConnection.getConnections();
		List<Feature> incart = new ArrayList<Feature>();
		String query = "select p.products_name,c.quantity,p.standard_cost,(c.quantity*p.standard_cost),p.products_id,Productsimage as cost from order_details o join cart c on o.order_id =c.order_id join product p on p.products_id=c.product_id where o.order_id=?";
		 try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, feature.getOrderId());
			 rs = stmt.executeQuery();
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
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		 finally {
				CloseConnection.close(stmt, con, rs);
			}
		return incart;
	}
	/**
	 * this method use to show product total in cart
	 */
	public double showCartinTotal(Feature feature)  {
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		con = GetConnection.getConnections();
		double total = 0;
		String query = "select sum(c.quantity*p.standard_cost) as cost from order_details o join cart c on o.order_id =c.order_id join product p on p.products_id=c.product_id where o.order_id=?";
	   try {
		stmt = con.prepareStatement(query);
			stmt.setInt(1, feature.getOrderId());
			 rs = stmt.executeQuery();

			while (rs.next()) {
				total = rs.getDouble(1);

			}
	} catch (SQLException e) {
		
		System.out.println(e.getMessage());
	}
	   finally {
			CloseConnection.close(stmt, con, rs);
		}
		return total;
	}
	/**
	 * this method use to remove product from cart
	 */
	public boolean delete(Cart stt)  {
		Connection con = null;
		PreparedStatement stmt=null;
		
		con = GetConnection.getConnections();
		String query = " delete FROM cart where product_id=? and order_id=? ";
	    try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, stt.getProductid());
			stmt.setInt(2, stt.getOrderid());
			stmt.executeUpdate();
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
	    finally {
			CloseConnection.close(stmt, con);
		}

		return true;

	}
	/**
	 * this method use to get list of product id from cart
	 */
	public List<Integer> gettingproductidincart(Cart stt)  {
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		 con = GetConnection.getConnections();
		List<Integer> ProductId = new ArrayList<Integer>();
		String query = " SELECT product_id FROM cart where order_id=?";
		 try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, stt.getOrderid());
			 rs = stmt.executeQuery();
			while (rs.next()) {
				ProductId.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		 finally {
				CloseConnection.close(stmt, con, rs);
			}
		return ProductId;
	}
	/**
	 * this method use to update current price to product 
	 */
	public void insertcurrentvalue(Cart stt) {
		Connection con = null;
		PreparedStatement stmt=null;
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
		}
		 finally {
				CloseConnection.close(stmt, con);
			}

	}
	/**
	 * this method use to get quantity of product in cart
	 */
	public List<Integer> gettingproductpriceincart(Cart stt)  {
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		 con = GetConnection.getConnections();
		List<Integer> ProductIds = new ArrayList<Integer>();
		String query = " SELECT quantity FROM cart where order_id=?";
		 try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, stt.getOrderid());
			 rs = stmt.executeQuery();
			while (rs.next()) {
				ProductIds.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		 finally {
				CloseConnection.close(stmt, con, rs);
			}
		return ProductIds;
	}

}
