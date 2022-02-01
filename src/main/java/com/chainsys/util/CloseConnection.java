package com.chainsys.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CloseConnection {

public static void close(PreparedStatement pstmt, Connection con) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement stmt, Connection con,ResultSet rs) {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void close(PreparedStatement pstmt, Connection con, ResultSet rs) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}



}
