package com.chainsys.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.chainsys.dao.OrderDaoinferace;
import com.chainsys.dto.Feature;
import com.chainsys.model.Order;
import com.chainsys.util.CloseConnection;
import com.chainsys.util.GetConnection;

public class OrderDaoImpl implements OrderDaoinferace {
	int custmerid = 0;

	/**
	 * this method use to create order id for given customer id
	 */
	public void creatingOrderId(Order order) {
		Connection con = null;
		PreparedStatement stmt = null;

		con = GetConnection.getConnections();

		String query = " INSERT INTO order_details (customer_id,status) VALUES (?,?)";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, order.getCustomerid());
			stmt.setString(2, order.getStatus());
			stmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con);
		}
	}

	/**
	 * this method use to get order id for given customer id
	 */
	public int GettingOrderID(Order order) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		con = GetConnection.getConnections();
		String query1 = "  SELECT order_id FROM order_details "
				+ "where customer_id = ?  "
				+ "order by order_date desc fetch first rows only";
		try {
			stmt = con.prepareStatement(query1);
			stmt.setInt(1, order.getCustomerid());

			rs = stmt.executeQuery();
			if (rs.next()) {
				custmerid = rs.getInt("order_id");
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con, rs);
		}

		return custmerid;
	}

	/**
	 * this method use to show today sale
	 */
	public List<Feature> todaySale() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		con = GetConnection.getConnections();
		List<Feature> todaysale = new ArrayList<Feature>();
		String query = "  SELECT products_name,price,quantity,cost,productsimage FROM  today_product_sale";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Feature feature = new Feature();
				feature.setProductName(rs.getString("products_name"));
				feature.setPrice(rs.getDouble("price"));
				feature.setQuantity(rs.getInt("quantity"));
				feature.setCost(rs.getDouble("cost"));
				feature.setProductImage(rs.getString("productsimage"));
				todaysale.add(feature);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.closeStatement(stmt, con, rs);
		}
		return todaysale;
	}

	/**
	 * this method use to show today sale total
	 */
	public double todaySales() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		double total = 0;
		con = GetConnection.getConnections();
		String query = "  select cost from  today_product_amount_sale";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {

				total = rs.getDouble("cost");
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.closeStatement(stmt, con, rs);
		}
		return total;

	}

	/**
	 * this method use to show week sale
	 */
	public List<Feature> weekSale() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		con = GetConnection.getConnections();
		List<Feature> todaysale = new ArrayList<Feature>();
		String query = " select products_name,price,quantity,cost,productsimage from  week_product_sale";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Feature feature = new Feature();
				feature.setProductName(rs.getString("products_name"));
				feature.setPrice(rs.getDouble("price"));
				feature.setQuantity(rs.getInt("quantity"));
				feature.setCost(rs.getDouble("cost"));
				feature.setProductImage(rs.getString("productsimage"));
				todaysale.add(feature);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.closeStatement(stmt, con, rs);
		}
		return todaysale;
	}

	/**
	 * this method use to show week sale amount
	 */
	public double weekSales() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		double total = 0;
		con = GetConnection.getConnections();
		String query = "  SELECT cost FROM   week_product_amount_sale";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {

				total = rs.getDouble("cost");
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.closeStatement(stmt, con, rs);
		}
		return total;

	}

	/**
	 * this method use to show pending order to admin
	 */
	public List<Order> orderdetails() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Order> todayOrder = new ArrayList<Order>();
		con = GetConnection.getConnections();
		String query = "  SELECT order_id,status,order_date "
				+ "FROM order_details where  status ='confirm'";
		try {
			stmt = con.prepareStatement(query);
			Order order = new Order();
			rs = stmt.executeQuery();
			while (rs.next()) {
				order = new Order();
				order.setOrderid(rs.getInt("order_id"));
				order.setStatus(rs.getString("status"));
				order.setOrderdate(rs.getDate("order_date"));

				todayOrder.add(order);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con, rs);
		}
		return todayOrder;
	}

	/**
	 * this method use to show list of order details to given user
	 */
	public List<Feature> userOrderDetails(final Feature feature) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		con = GetConnection.getConnections();
		List<Feature> orderlist = new ArrayList<Feature>();
		String query = "  select p.products_name as products_name "
				+ ",c.quantity as quantity ,c.price as price"
				+ ",(c.quantity*c.price) as total ,p.Productsimage as cost "
				+ "from order_details o"
				+ " join cart c on o.order_id =c.order_id "
				+ "join product p on p.products_id=c.product_id where o.order_id=?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, feature.getOrderId());

			rs = stmt.executeQuery();
			while (rs.next()) {
				Feature feature1 = new Feature();
				feature1.setProductName(rs.getString("products_name"));
				feature1.setQuantity(rs.getInt("quantity"));
				feature1.setPrice(rs.getDouble("price"));
				feature1.setCost(rs.getDouble("total"));
				feature1.setProductImage(rs.getString("cost"));

				orderlist.add(feature1);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con, rs);
		}
		return orderlist;
	}

	/**
	 * this method use to show list of order details total to given user
	 */
	public double userOrderDetailse(final Feature feature) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		double b = 0;
		con = GetConnection.getConnections();

		String query = "select sum(c.quantity*c.price) as cost"
				+ " from order_details o "
				+ "join cart c on o.order_id =c.order_id "
				+ "join product p on p.products_id=c.product_id "
				+ "where o.order_id=?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, feature.getOrderId());

			rs = stmt.executeQuery();
			if (rs.next()) {

				b = rs.getInt("cost");

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con, rs);
		}
		return b;
	}

	/**
	 * this method use to show list of order to given user
	 */
	public List<Order> orderdetail(final Order order) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		con = GetConnection.getConnections();
		List<Order> orderList = new ArrayList<Order>();
		String query = " SELECT order_id,status,order_date "
				+ "FROM order_details "
				+ "where  customer_id= ? "
				+ "and status in ('delivered',  'confirm','cancel')"
				+ " order by order_date desc";
		try {
			stmt = con.prepareStatement(query);

			stmt.setInt(1, order.getCustomerid());

			rs = stmt.executeQuery();
			while (rs.next()) {
				Order orders = new Order(rs.getInt("order_id"), rs.getString("status"), rs.getDate("order_date"));
				orderList.add(orders);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con, rs);
		}
		return orderList;
	}

	/**
	 * this method use to check cart available and get order id
	 */
	public int cartCheck(final Order order)

	{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int b = 0;
		con = GetConnection.getConnections();
		String query = "   SELECT order_id FROM order_details where customer_id=? and status='in cart' ";
		try {
			stmt = con.prepareStatement(query);

			stmt.setInt(1, order.getCustomerid());
			rs = stmt.executeQuery();
			while (rs.next()) {
				b = rs.getInt("order_id");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con, rs);
		}
		return b;
	}

	/**
	 * this method use to make final order date
	 */
	public void makefinal(final Order order) {
		Connection con = null;
		PreparedStatement stmt = null;

		con = GetConnection.getConnections();

		String query = " update order_details "
				+ "set status ='confirm' ,"
				+ " order_date=TO_CHAR(SYSDATE, 'DD-MM-YYYY') "
				+ "where order_id=?";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, order.getOrderid());

			stmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con);
		}
	}

	/**
	 * this method use to check cart available
	 */
	public String status(final Order order) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String statuse = "";

		con = GetConnection.getConnections();

		String query1 = "  SELECT status FROM order_details where order_id = ?";
		try {
			stmt = con.prepareStatement(query1);
			stmt.setInt(1, order.getOrderid());

			rs = stmt.executeQuery();
			if (rs.next()) {
				statuse = rs.getString("status");
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con, rs);
		}

		return statuse;

	}

	/**
	 * this method use to cancel order
	 */
	public boolean cancel(final Order order) {
		Connection con = null;
		PreparedStatement stmt = null;

		con = GetConnection.getConnections();

		String query = " update order_details set status ='cancel'  where order_id=?";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, order.getOrderid());

			stmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con);
		}
		return true;
	}

	/**
	 * this method use to show to order in week
	 */
	public List<Order> graphsale() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		con = GetConnection.getConnections();
		List<Order> todaysale = new ArrayList<Order>();
		String query = "SELECT count(status),trunc(order_date)"
				+ " FROM order_details where  trunc(order_date) "
				+ "between trunc(sysdate-7)"
				+ " and trunc(sysdate ) and status ='delivered' "
				+ "group by trunc(order_date)";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Order orders = new Order();
				orders.setOrderdate(rs.getDate("trunc(order_date)"));
				orders.setOrderid(rs.getInt("count(status)"));
				todaysale.add(orders);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.closeStatement(stmt, con, rs);
		}
		return todaysale;
	}

	/**
	 * this method use to show today place order
	 */
	public List<Order> listoforder() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		con = GetConnection.getConnections();
		List<Order> todaysale = new ArrayList<Order>();
		String query = "  SELECT order_id,status,order_date  "
				+ "FROM order_details"
				+ " where  status in ('delivered',  'confirm','cancel') "
				+ "order by order_date desc";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Order orders = new Order();
				orders.setStatus(rs.getString("status"));
				orders.setOrderdate(rs.getDate("order_date"));
				orders.setOrderid(rs.getInt("order_id"));
				todaysale.add(orders);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.closeStatement(stmt, con, rs);
		}
		return todaysale;
	}

	/**
	 * this method use to admin accept order
	 */
	public boolean accept(final Order order) {
		Connection con = null;
		PreparedStatement stmt = null;

		con = GetConnection.getConnections();

		String query = " update order_details set status ='delivered'  where order_id=?";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, order.getOrderid());

			stmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.close(stmt, con);
		}
		return true;
	}

	/**
	 * this method use to show today place order graph
	 */
	public List<Feature> todaySalegraph() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		con = GetConnection.getConnections();
		List<Feature> todaysale = new ArrayList<Feature>();
		String query = "  SELECT products_name,price,quantity"
				+ ",cost,productsimage FROM  today_product_salegraph";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Feature feature = new Feature();
				feature.setProductName(rs.getString("products_name"));
				feature.setPrice(rs.getDouble("price"));
				feature.setQuantity(rs.getInt("quantity"));
				feature.setCost(rs.getDouble("cost"));
				feature.setProductImage(rs.getString("productsimage"));
				todaysale.add(feature);

			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			CloseConnection.closeStatement(stmt, con, rs);
		}
		return todaysale;
	}

}
