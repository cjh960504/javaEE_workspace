package com.exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExApp {
	//throws는 현재메서드에서 해당 예외를 처리하지 않고, 이 메서드를 호출한 자에게 
	//떠넘기는 것!!
	public void insert() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		/* try { */
			pstmt = con.prepareStatement("insert...");
			pstmt.executeUpdate();
	/*	} catch (SQLException e) {
			e.printStackTrace();
		}*/
	}
	
	public static void main(String[] args) {
		ExApp app = new ExApp();
		try {
			app.insert();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
