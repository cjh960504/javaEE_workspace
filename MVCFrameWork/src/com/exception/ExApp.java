package com.exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExApp {
	//throws�� ����޼��忡�� �ش� ���ܸ� ó������ �ʰ�, �� �޼��带 ȣ���� �ڿ��� 
	//���ѱ�� ��!!
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
