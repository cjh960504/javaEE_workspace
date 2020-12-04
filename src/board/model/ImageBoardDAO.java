/*
ImageBoard 테이블에 대한 CRUD만을 전담하는 DAO정의
*/
package board.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBManager;

public class ImageBoardDAO {
	//ImageBoardDAO의 인스턴스가 생성될 때 DBManager인스턴스도 같이생성
	DBManager dbManager = new DBManager();
	
	//create(insert)
	public int insert(ImageBoard imageBoard) {
		Connection con=null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into imageboard(author, title, content, filename)";
		sql+= " values(?,?,?,?)";
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, imageBoard.getAuthor());
			pstmt.setString(2, imageBoard.getTitle());
			pstmt.setString(3, imageBoard.getContent());
			pstmt.setString(4, imageBoard.getFilename());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		
		
		return result;
	}
	//selectAll
	public ArrayList<ImageBoard> selectAll(){
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from imageboard order by board_id desc";
		ArrayList<ImageBoard> list = new ArrayList<ImageBoard>();
		con = dbManager.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ImageBoard board = new ImageBoard();
				board.setBoard_id(rs.getInt("board_id"));
				board.setAuthor(rs.getString("author"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setFilename(rs.getString("filename"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		
		return list;
	}
	//select
	public ImageBoard select(int board_id) {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select * from imageboard where board_id=?";
		ImageBoard board=new ImageBoard();
		con = dbManager.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board.setBoard_id(rs.getInt("board_id"));
				board.setAuthor(rs.getString("author"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setFilename(rs.getString("filename"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return board;
	}
	
	//update
	public int update(ImageBoard imageBoard) {
		Connection con=null;
		PreparedStatement pstmt = null;
		String sql ="update imageboard set author=?, title=?, content=? where board_id=?";
		int result = 0;
		con = dbManager.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, imageBoard.getAuthor());
			pstmt.setString(2, imageBoard.getTitle());
			pstmt.setString(3, imageBoard.getContent());
			pstmt.setInt(4, imageBoard.getBoard_id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	//delete
	public int delete(int board_id) {
		Connection con=null;
		PreparedStatement pstmt = null;
		String sql = "delete from imageboard where board_id=?";
		int result = 0;
		con = dbManager.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
