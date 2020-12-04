//데이터베이스 접속 및 해제와 관련된 코드가 너무나 중복되므로, 공통의 로직으로
//분리시켜 재사용하기 위함!!
package db;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager{
	//mysql에 select문을 수행하자!!
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/db1202";
	String user="root";
	String password="1234";
	
	//접속 개체 얻기 
	public Connection getConnection(){
		Connection con=null;//return 시키기 위해..
		try{
		Class.forName(driver);
		System.out.println("드라이버 로드 성공");

		con = DriverManager.getConnection(url, user,password);
	
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("드라이버 로드 실패");
		}catch(SQLException e){
			e.printStackTrace();	
		}
		return con;
	}

	//자원해제
	public void release(Connection con){//쿼리문 수행 안했을 때..
		if(con!=null){
			try{
				con.close();	
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

	public void release(Connection con, PreparedStatement pstmt){//DML용
		if(pstmt!=null){
			try{
				pstmt.close();	
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(con!=null){
			try{
				con.close();	
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	public void release(Connection con, PreparedStatement pstmt, ResultSet rs){//SELECT용
		if(rs!=null){
			if(rs!=null){
				try{
					rs.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		if(pstmt!=null){
			try{
				pstmt.close();	
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(con!=null){
			try{
				con.close();	
			}catch(SQLException e){
				e.printStackTrace();
			}
		}	
	}

}
