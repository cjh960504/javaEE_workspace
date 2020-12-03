/*
DAO��?
- Data Access Object�� �ǹ��ϴ� ���ø����̼��� ���� �о� ���
- Data Access�� �����ͺ��̽����� CRUD�۾��� �����Ѵٴ� �ǹ�	
*/
package board.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBManager;

public class NoticeDAO {
	DBManager dbManager=new DBManager();
	
	//insert�� �� �Ѱ�~~ �ϳ��� VO
	public int regist(Notice notice) {
		Connection con=null;
		PreparedStatement pstmt = null;
		String sql = "insert into notice(author, title, content) values(?,?,?)";
		int result=0;
		con = dbManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, notice.getAuthor());//�ۼ���
			pstmt.setString(2, notice.getTitle());//����
			pstmt.setString(3, notice.getContent());//����
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
	
	//�Խù� 1�� ��������(�󼼺���)
	public Notice select(int notice_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice notice=null;//rs��� ������ 1���� ���� ��ü

		String sql ="select * from notice where notice_id=?";
		
		con = dbManager.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_id);
			rs = pstmt.executeQuery();
			//���� ź���� rs�� �� ����, ���� rs�� ��ü�� ��ü�� �ʿ��ϴ�!!
			//rs�� ���ڵ� �Ѱ��� ��� ��ü�̹Ƿ�, ���ڵ� 1���� ��� ���޿����� ���Ǵ� VO�� �̿�����!
			if(rs.next()) {
				notice = new Notice();
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setHit(rs.getInt("hit"));
				notice.setRegdate(rs.getString("regdate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		return notice;
	}
	
	//��� ���ڵ� ��������!
	public ArrayList<Notice> selectAll() {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		
		con = dbManager.getConnection();
		String sql = "select * from notice order by notice_id desc";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//rs�� ���ڵ尡 ������ �̹Ƿ�, �� ������ �̹Ƿ� VO���� �������� �ʿ��ϰ�
			//�� VO�� �Ѳ����� ��Ƽ� ��ȯ�ؾ��ϹǷ�, ������ �ڷ����� �ʿ��ϴ�!
			//��ü�� ��Ƴ��� ������ �����ϴ� �����ӿ�ũ�� CollectionFrameWork�̹Ƿ�
			//�� �� �ϳ��� api�� �̿��غ���
			while(rs.next()) {
				Notice notice = new Notice();
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setHit(rs.getInt("hit"));
				notice.setRegdate(rs.getString("regdate"));
				list.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
		
		return list;
	}
	
	//�� ����
	public int update(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt=null;
		int result=0;
		String sql = "update notice set author=?, title=?, content=? where notice_id=?";
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice.getAuthor());
			pstmt.setString(2, notice.getTitle());
			pstmt.setString(3, notice.getContent());
			pstmt.setInt(4, notice.getNotice_id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	//�� ����
	public int delete(int notice_id) {
		Connection con = null;
		PreparedStatement pstmt=null;
		int result=0;
		String sql = "delete from notice where notice_id=?";
		
		con = dbManager.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
