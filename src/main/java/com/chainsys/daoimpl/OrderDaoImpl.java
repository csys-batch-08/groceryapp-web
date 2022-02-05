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
import com.chainsys.util.CloseConnection;
import com.chainsys.util.GetConnection;

public class OrderDaoImpl implements OrderDaoinferace {
	int custmerid = 0;

	public void creatingOrderId(Order order) {
		Connection con = null;
		PreparedStatement stmt=null;
		
			con = GetConnection.getConnections();
		

					String query = " INSERT INTO order_details (customer_id,status) VALUES (?,?)";

		try {
		 stmt = con.prepareStatement(query);
			stmt.setInt(1, order.getCustomerid());
			stmt.setString(2, order.getStatus());
			stmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		finally {
			CloseConnection.close(stmt, con);
		}
	}
/**
 * this method use to get order id for given customer id
 */
	public int GettingOrderID(Order order) {
		Connection con = null;
		PreparedStatement stmt= null;
		ResultSet rs=null;
		con = GetConnection.getConnections();
				String query1 = "  SELECT order_id FROM order_details where customer_id = ?  order by order_date desc fetch first rows only";
		try {
			 stmt = con.prepareStatement(query1);
			stmt.setInt(1, order.getCustomerid());

			 rs = stmt.executeQuery();
			if (rs.next()) {
				custmerid = rs.getInt(1);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		finally {
			CloseConnection.close(stmt, con, rs);
		}

		return custmerid;
	}

	public List<Feature> todaySale() {
		Connection con = null;
		Statement stmt=null;
		ResultSet rs=null;
		con = GetConnection.getConnections();
		List<Feature> todaysale = new ArrayList<Feature>();
		String query = "  SELECT products_name,price,quantity,cost,productsimage FROM  today_product_sale";
		 try {
			stmt = con.createStatement();
			 rs = stmt.executeQuery(query);
			while (rs.next()) {
				Feature feature = new Feature();
				feature.setProductName(rs.getString(1));
				feature.setPrice(rs.getDouble(2));
				feature.setQuantity(rs.getInt(3));
				feature.setCost(rs.getDouble(4));
				feature.setProductImage(rs.getString(5));
				todaysale.add(feature);

			}
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		 finally {
				CloseConnection.closeStatement(stmt, con, rs);
			}
		return todaysale;
	}

	public double todaySales()  {
		Connection con = null;
		Statement stmt=null;
		ResultSet rs=null;
		double total = 0;
	con = GetConnection.getConnections();
		String query = "  select cost from  today_product_amount_sale";
		try {
			stmt = con.createStatement();
			 rs = stmt.executeQuery(query);
			if (rs.next()) {

				total = rs.getDouble(1);
			}
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		finally {
			CloseConnection.closeStatement(stmt, con, rs);
		}
		return total;

	}

	public List<Feature> weekSale()  {
		Connection con = null;
		Statement stmt=null;
		ResultSet rs=null;
		 con = GetConnection.getConnections();
		List<Feature> todaysale = new ArrayList<Feature>();
		String query = " select products_name,price,quantity,cost,productsimage from  week_product_sale";
		try {
			stmt = con.createStatement();
			 rs = stmt.executeQuery(query);
			while (rs.next()) {
				Feature feature = new Feature();
				feature.setProductName(rs.getString(1));
				feature.setPrice(rs.getDouble(2));
				feature.setQuantity(rs.getInt(3));
				feature.setCost(rs.getDouble(4));
				feature.setProductImage(rs.getString(5));
				todaysale.add(feature);

			}
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		 finally {
				CloseConnection.closeStatement(stmt, con, rs);
			}
		return todaysale;
	}

	public double weekSales()  {
		Connection con = null;
		Statement stmt=null;
		ResultSet rs=null;
		double total = 0;
		 con = GetConnection.getConnections();
		String query = "  SELECT cost FROM   week_product_amount_sale";
		 try {
			stmt = con.createStatement();
			 rs = stmt.executeQuery(query);
			if (rs.next()) {

				total = rs.getDouble(1);
			}
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		 finally {
				CloseConnection.closeStatement(stmt, con, rs);
			}
		return total;

	}

	public List<Order> orderdetails() {
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<Order> todayOrder = new ArrayList<Order>();
	 con = GetConnection.getConnections();
		String query = "  SELECT order_id,status,order_date FROM order_details where  status ='confirm'";
	 try {
		stmt = con.prepareStatement(query);
			Order order = new Order();// stmt.executeUpdate();
		 rs = stmt.executeQuery();
			while (rs.next()) {
				order = new Order();
				order.setOrderid(rs.getInt(1));
				order.setStatus(rs.getString(2));
				order.setOrderdate(rs.getDate(3));

				todayOrder.add(order);
			}
	} catch (SQLException e) {
		
		System.out.println(e.getMessage());
	}
	 finally {
			CloseConnection.close(stmt, con, rs);
		}
		return todayOrder;
	}

	public List<Feature> userOrderDetails(Feature feature)  {
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		 con = GetConnection.getConnections();
		List<Feature> orderlist = new ArrayList<Feature>();
		String query = "  select p.products_name,c.quantity,c.price,(c.quantity*c.price),p.Productsimage as cost from order_details o join cart c on o.order_id =c.order_id join product p on p.products_id=c.product_id where o.order_id=?";
		 try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, feature.getOrderId());
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				Feature feature1 = new Feature();
				feature1.setProductName(rs.getString(1));
				feature1.setQuantity(rs.getInt(2));
				feature1.setPrice(rs.getDouble(3));
				feature1.setCost(rs.getDouble(4));
				feature1.setProductImage(rs.getString(5));

				orderlist.add(feature1);
			}
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		 finally {
				CloseConnection.close(stmt, con, rs);
			}
		return orderlist;
	}

	public double userOrderDetailse(Feature feature)  {
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		double b = 0;
		 con = GetConnection.getConnections();

		String query = "select sum(c.quantity*c.price) as cost from order_details o join cart c on o.order_id =c.order_id join product p on p.products_id=c.product_id where o.order_id=?";
		 try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, feature.getOrderId());
			
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

	public List<Order> orderdetail(Order order)  {
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		con = GetConnection.getConnections();
		List<Order> orderList = new ArrayList<Order>();
		String query = " SELECT order_id,status,order_date FROM order_details where  customer_id= ? and status in ('delivered',  'confirm','cancel') order by order_date desc";
		try {
			stmt = con.prepareStatement(query);

			stmt.setInt(1, order.getCustomerid());


			 rs = stmt.executeQuery();
			while (rs.next()) {
				Order orders = new Order(rs.getInt(1), rs.getString(2), rs.getDate(3));
				orderList.add(orders);

			}
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		finally {
			CloseConnection.close(stmt, con, rs);
		}
		return orderList;
	}

	public int cartCheck(Order order) 

	{
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int b = 0;
		con = GetConnection.getConnections();
		String query = "   SELECT order_id FROM order_details where customer_id=? and status='in cart' ";
		 try {
			stmt = con.prepareStatement(query);

			stmt.setInt(1, order.getCustomerid());
			 rs = stmt.executeQuery();
			while (rs.next()) {
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

	public void makefinal(Order order) {
		Connection con = null;
		PreparedStatement stmt=null;
		
			con = GetConnection.getConnections();
		
		String query = " update order_details set status ='confirm' , order_date=TO_CHAR(SYSDATE, 'DD-MM-YYYY') where order_id=?";

		try {
			 stmt = con.prepareStatement(query);
			stmt.setInt(1, order.getOrderid());

			stmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		finally {
			CloseConnection.close(stmt, con);
		}
	}

	public String status(Order order) {
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String statuse = "";
		
			con = GetConnection.getConnections();
		
		String query1 = "  SELECT status FROM order_details where order_id = ?";
		try {
			 stmt = con.prepareStatement(query1);
			stmt.setInt(1, order.getOrderid());

			 rs = stmt.executeQuery();
			if (rs.next()) {
				statuse = rs.getString(1);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		finally {
			CloseConnection.close(stmt, con, rs);
		}
		

		return statuse;

	}

	public boolean cancel(Order order) {
		Connection con = null;
		PreparedStatement stmt =null;
		
			con = GetConnection.getConnections();
		
		String query = " update order_details set status ='cancel'  where order_id=?";

		try {
			 stmt = con.prepareStatement(query);
			stmt.setInt(1, order.getOrderid());

			stmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		finally {
			CloseConnection.close(stmt, con);
		}
		return true;
	}

	public List<Order> graphsale()  {
		Connection con = null;
		Statement stmt=null;
		ResultSet rs=null;
		 con = GetConnection.getConnections();
		List<Order> todaysale = new ArrayList<Order>();
		String query = "SELECT count(status),trunc(order_date) FROM order_details where  trunc(order_date) between trunc(sysdate-7) and trunc(sysdate ) and status ='delivered' group by trunc(order_date)";
		 try {
			stmt = con.createStatement();
			 rs = stmt.executeQuery(query);
			while (rs.next()) {
				Order orders = new Order();
				orders.setOrderdate(rs.getDate(2));
				orders.setOrderid(rs.getInt(1));
				todaysale.add(orders);

			}
		} catch (SQLException e) {
		
			System.out.println(e.getMessage());
		}
		 finally {
				CloseConnection.closeStatement(stmt, con, rs);
			}
		return todaysale;
	}

	public List<Order> listoforder() {
		Connection con = null;
		Statement stmt=null;
		ResultSet rs=null;
		con = GetConnection.getConnections();
		List<Order> todaysale = new ArrayList<Order>();
		String query = "  SELECT order_id,status,order_date  FROM order_details where  status in ('delivered',  'confirm','cancel') order by order_date desc";
		 try {
			stmt = con.createStatement();
			 rs = stmt.executeQuery(query);
		
		
			while (rs.next()) {
				Order orders = new Order();
				orders.setStatus(rs.getString(2));
				orders.setOrderdate(rs.getDate(3));
				orders.setOrderid(rs.getInt(1));
				todaysale.add(orders);

			}
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		finally {
			CloseConnection.closeStatement(stmt, con, rs);
		}
		return todaysale;
	}

	public boolean accept(Order order) {
		Connection con = null;
		PreparedStatement stmt=null;
		
			con = GetConnection.getConnections();
		
		String query = " update order_details set status ='delivered'  where order_id=?";

		try {
			 stmt = con.prepareStatement(query);
			stmt.setInt(1, order.getOrderid());

			stmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		finally {
			CloseConnection.close(stmt, con);
		}
		return true;
	}
	public List<Feature> todaySalegraph() {
		Connection con = null;
		Statement stmt=null;
		ResultSet rs=null;
		 con = GetConnection.getConnections();
		List<Feature> todaysale = new ArrayList<Feature>();
		String query = "  SELECT products_name,price,quantity,cost,productsimage FROM  today_product_salegraph";
		 try {
			stmt = con.createStatement();
			 rs = stmt.executeQuery(query);
		
			while (rs.next()) {
				Feature feature = new Feature();
				feature.setProductName(rs.getString(1));
				feature.setPrice(rs.getDouble(2));
				feature.setQuantity(rs.getInt(3));
				feature.setCost(rs.getDouble(4));
				feature.setProductImage(rs.getString(5));
				todaysale.add(feature);

			}
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		finally {
			CloseConnection.closeStatement(stmt, con, rs);
		}
		return todaysale;
	}

}
