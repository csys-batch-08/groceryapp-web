package com.chainsys.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.chainsys.dao.OrderDaoinferace;
import com.chainsys.model.Feature;
import com.chainsys.model.Order;
import com.chainsys.util.GetConnection;

public class OrderDaoImpl implements OrderDaoinferace {
	int custmerid = 0;

	public void creatingOrderId(Order order) {
		Connection con = null;
		try {
			con = GetConnection.getConnections();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		String query = " INSERT INTO order_details (customer_id,status) VALUES (?,?)";

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, order.getCustomerid());
			stmt.setString(2, order.getStatus());
			stmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public int GettingOrderID(Order order) {
		Connection con = null;
		try {
			con = GetConnection.getConnections();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		String query1 = "  SELECT order_id FROM order_details where customer_id = ?  order by order_date desc fetch first rows only";
		try {
			PreparedStatement stmt = con.prepareStatement(query1);
			stmt.setInt(1, order.getCustomerid());

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				custmerid = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return custmerid;
	}

	public List<Feature> todaySale() throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		List<Feature> todaysale = new ArrayList<Feature>();
		String query = "  SELECT products_name,price,quantity,cost,productsimage FROM  today_product_sale";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			Feature feature = new Feature();
			feature.setProductName(rs.getString(1));
			feature.setPrice(rs.getDouble(2));
			feature.setQuantity(rs.getInt(3));
			feature.setCost(rs.getDouble(4));
			feature.setProductImage(rs.getString(5));
			todaysale.add(feature);

		}
		return todaysale;
	}

	public double todaySales() throws ClassNotFoundException, SQLException {
		double total = 0;
		Connection con = GetConnection.getConnections();
		String query1 = "  select cost from  today_product_amount_sale";
		Statement stmt1 = con.createStatement();
		ResultSet rs1 = stmt1.executeQuery(query1);
		if (rs1.next()) {

			total = rs1.getDouble(1);
		}
		return total;

	}

	public List<Feature> weekSale() throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		List<Feature> todaysale = new ArrayList<Feature>();
		String query = " select products_name,price,quantity,cost,productsimage from  week_product_sale";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			Feature feature = new Feature();
			feature.setProductName(rs.getString(1));
			feature.setPrice(rs.getDouble(2));
			feature.setQuantity(rs.getInt(3));
			feature.setCost(rs.getDouble(4));
			feature.setProductImage(rs.getString(5));
			todaysale.add(feature);

		}
		return todaysale;
	}

	public double weekSales() throws ClassNotFoundException, SQLException {
		double total = 0;
		Connection con = GetConnection.getConnections();
		String query1 = "  SELECT cost FROM   week_product_amount_sale";
		Statement stmt1 = con.createStatement();
		ResultSet rs1 = stmt1.executeQuery(query1);
		if (rs1.next()) {

			total = rs1.getDouble(1);
		}
		return total;

	}

	public List<Order> orderdetails() throws ClassNotFoundException, SQLException {
		List<Order> todayOrder = new ArrayList<Order>();
		Connection con = GetConnection.getConnections();
		String query = "  SELECT order_id,status,order_date FROM order_details where  status ='confirm'";
		PreparedStatement stmt = con.prepareStatement(query);
		Order order = new Order();
		// stmt.executeUpdate();
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			order = new Order();
			order.setOrderid(rs.getInt(1));
			order.setStatus(rs.getString(2));
			order.setOrderdate(rs.getDate(3));

			todayOrder.add(order);
		}
		return todayOrder;
	}

	public List<Feature> userOrderDetails(Feature feature) throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		List<Feature> orderlist = new ArrayList<Feature>();
		String query = "  select p.products_name,c.quantity,c.price,(c.quantity*c.price),p.Productsimage as cost from order_details o join cart c on o.order_id =c.order_id join product p on p.products_id=c.product_id where o.order_id=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, feature.getOrderId());
		// stmt.executeUpdate();
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Feature feature1 = new Feature();
			feature1.setProductName(rs.getString(1));
			feature1.setQuantity(rs.getInt(2));
			feature1.setPrice(rs.getDouble(3));
			feature1.setCost(rs.getDouble(4));
			feature1.setProductImage(rs.getString(5));

			orderlist.add(feature1);
		}
		return orderlist;
	}

	public double userOrderDetailse(Feature feature) throws ClassNotFoundException, SQLException {
		double b = 0;
		Connection con = GetConnection.getConnections();

		String query1 = "select sum(c.quantity*c.price) as cost from order_details o join cart c on o.order_id =c.order_id join product p on p.products_id=c.product_id where o.order_id=?";
		PreparedStatement stmt1 = con.prepareStatement(query1);
		stmt1.setInt(1, feature.getOrderId());
		// stmt.executeUpdate();
		ResultSet rs1 = stmt1.executeQuery();
		if (rs1.next()) {

			b = rs1.getInt(1);

		}
		return b;
	}

	public List<Order> orderdetail(Order order) throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		List<Order> orderList = new ArrayList<Order>();
		String query = " SELECT order_id,status,order_date FROM order_details where  customer_id= ? and status in ('delivered',  'confirm','cancel') order by order_date desc";
		PreparedStatement stmt = con.prepareStatement(query);

		stmt.setInt(1, order.getCustomerid());
		// stmt.executeUpdate();

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Order orders = new Order(rs.getInt(1), rs.getString(2), rs.getDate(3));
			orderList.add(orders);

		}
		return orderList;
	}

	public int cartCheck(Order order) throws ClassNotFoundException, SQLException

	{
		int b = 0;
		Connection con = GetConnection.getConnections();
		String query = "   SELECT order_id FROM order_details where customer_id=? and status='in cart' ";
		PreparedStatement stmt = con.prepareStatement(query);

		stmt.setInt(1, order.getCustomerid());
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			b = rs.getInt(1);
		}
		return b;
	}

	public void makefinal(Order order) {
		Connection con = null;
		try {
			con = GetConnection.getConnections();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		String query = " update order_details set status ='confirm' , order_date=TO_CHAR(SYSDATE, 'DD-MM-YYYY') where order_id=?";

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, order.getOrderid());

			stmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public String status(Order order) {
		Connection con = null;
		String statuse = "";
		try {
			con = GetConnection.getConnections();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		String query1 = "  SELECT status FROM order_details where order_id = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(query1);
			stmt.setInt(1, order.getOrderid());

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				statuse = rs.getString(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return statuse;

	}

	public boolean cancel(Order order) {
		Connection con = null;
		try {
			con = GetConnection.getConnections();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		String query = " update order_details set status ='cancel'  where order_id=?";

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, order.getOrderid());

			stmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return true;
	}

	public List<Order> graphsale() throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		List<Order> todaysale = new ArrayList<Order>();
		String query = "SELECT count(status),trunc(order_date) FROM order_details where  trunc(order_date) between trunc(sysdate-7) and trunc(sysdate ) and status ='delivered' group by trunc(order_date)";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			Order orders = new Order();
			orders.setOrderdate(rs.getDate(2));
			orders.setOrderid(rs.getInt(1));
			todaysale.add(orders);

		}
		return todaysale;
	}

	public List<Order> listoforder() throws ClassNotFoundException, SQLException {
		Connection con = GetConnection.getConnections();
		List<Order> todaysale = new ArrayList<Order>();
		String query = "  SELECT order_id,status,order_date  FROM order_details where  status in ('delivered',  'confirm','cancel') order by order_date desc";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			Order orders = new Order();
			orders.setStatus(rs.getString(2));
			orders.setOrderdate(rs.getDate(3));
			orders.setOrderid(rs.getInt(1));
			todaysale.add(orders);

		}
		return todaysale;
	}

	public boolean accept(Order order) {
		Connection con = null;
		try {
			con = GetConnection.getConnections();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		String query = " update order_details set status ='delivered'  where order_id=?";

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, order.getOrderid());

			stmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return true;
	}

}
