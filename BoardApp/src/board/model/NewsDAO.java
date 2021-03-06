package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;

public class NewsDAO {
	DBManager dbManager = new DBManager();
	
	public List<News> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from news order by news_id desc";
		ArrayList<News> list = new ArrayList<News>();
		con = dbManager.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select n.news_id as news_id, title, writer, regdate, hit, count(comments_id) as cnt ");
		sb.append("from news n left outer join comments c ");
		sb.append("on n.news_id=c.news_id ");
		sb.append("group by n.news_id, title, writer, regdate, hit order by n.news_id desc");
		
		try {
			pstmt=con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				News news = new News();
				news.setNews_id(rs.getInt("news_id"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
				news.setCnt(rs.getInt("cnt"));
				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		return list;
	}

	public News select(int news_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from news where news_id=?";
		con = dbManager.getConnection();
		News news = new News();
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				news.setNews_id(rs.getInt("news_id"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				news.setContent(rs.getString("content"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		return news;
	}

	public int insert(News news) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into news(title, writer, content) values (?, ?, ?)";
		con = dbManager.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, news.getTitle());
			pstmt.setString(2, news.getWriter());
			pstmt.setString(3, news.getContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}

	public int update(News news) {
		int result = 0;
		String sql = "update news set title=?, writer=?, content=? where news_id=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		con = dbManager.getConnection();
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, news.getTitle());
			pstmt.setString(2, news.getWriter());
			pstmt.setString(3, news.getContent());
			pstmt.setInt(4, news.getNews_id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		
		return result;
	}
	
	public int delete(int news_id) {
		int result = 0;
		String sql = "delete from news where news_id=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		con = dbManager.getConnection();
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
	
	//게시물 지우지 않고, 삭제된 게시물이라는 표시 처리
	public int replace(int news_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result=0;
		String sql = "update news set title='작성자에 의해 삭제된 게시물입니다.', writer='', content='' where news_id=?";
		
		con=dbManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
}
