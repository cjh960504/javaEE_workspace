package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;

public class QnADAO {
	DBManager dbManager = new DBManager();
	
	//���� insert
	public int insert(QnA qna) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		con = dbManager.getConnection();

		String sql = "insert into qna(writer, title, content) values(?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getWriter());
			pstmt.setString(2, qna.getTitle());
			pstmt.setString(3, qna.getContent());
			result = pstmt.executeUpdate();
			
			//team�� ��� �� ���ڵ忡 ���� �߻��� pk������ ������Ʈ!!!
			sql="update qna set team=(select last_insert_id()) where qna_id=(select last_insert_id())";
			pstmt = con.prepareStatement(sql);//������ 1:1 �����ϰ�!
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
	
	/*1. ������ ���� �� �ۺ��� rank�� ū ���� rank�� ��� 1�� �����ǽÿ�!!(����Ȯ��)
	= update qna rank=rank+1 where team=������team and rank>������rank
	2. �� ������ ���� ����!
	= insert into qna(~, team, rank, depth) values(����team, ����rank+1, ����depth+1)*/
	
	//�亯�� insert
	public int reply(QnA qna) {
		int result=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update qna set rank=rank+1 where team=? and rank>?";
		
		con = dbManager.getConnection();
		
		try {
			con.setAutoCommit(false);//�ڵ����� Ŀ��������!!!
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna.getTeam());
			pstmt.setInt(2, qna.getRank());
			result = pstmt.executeUpdate();
			
			sql="insert into qna(writer, title, content, team, rank, depth)";
			sql+=" values (?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getWriter());
			pstmt.setString(2, qna.getTitle());
			pstmt.setString(3, qna.getContent());
			pstmt.setInt(4, qna.getTeam());
			pstmt.setInt(5, qna.getRank()+1);//������ ������ ��ġ�ϱ� ���� +1
			pstmt.setInt(6, qna.getDepth()+1);//�����ۿ� ���� �亯�̹Ƿ�+1
			result = pstmt.executeUpdate();
			
			con.commit();// ���⼭ Ŀ�� �� �Ѵ� �������� �ʰ� try�� �Ϸ��ϸ� ��� �������� ����!!
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();//�� ������ �� ������ �ϳ��� �߻��ϸ�, ���� ó������ ������ �Ϸ� ����!!
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dbManager.release(con, pstmt);
		}
		
		return result;
	}
	
	//select
	public QnA select(int qna_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from qna where qna_id=?";
		con = dbManager.getConnection();
		QnA qna = new QnA(); 
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				qna.setQna_id(rs.getInt("qna_id"));
				qna.setWriter(rs.getString("writer"));
				qna.setTitle(rs.getString("title"));
				qna.setContent(rs.getString("content"));
				qna.setRegdate(rs.getString("regdate"));
				qna.setHit(rs.getInt("hit"));
				qna.setTeam(rs.getInt("team"));
				qna.setRank(rs.getInt("rank"));
				qna.setDepth(rs.getInt("depth"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qna;
	}
	
	//selectAll
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<QnA> list=  new ArrayList<QnA>();
		String sql = "select * from qna order by team desc, rank asc";
		con = dbManager.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				QnA qna = new QnA(); //���ڵ� ����ŭ VO����
				qna.setQna_id(rs.getInt("qna_id"));
				qna.setWriter(rs.getString("writer"));
				qna.setTitle(rs.getString("title"));
				qna.setContent(rs.getString("content"));
				qna.setRegdate(rs.getString("regdate"));
				qna.setHit(rs.getInt("hit"));
				qna.setTeam(rs.getInt("team"));
				qna.setRank(rs.getInt("rank"));
				qna.setDepth(rs.getInt("depth"));
				qna.setIsdel(rs.getInt("isdel"));
				list.add(qna);//����Ʈ�� �߰��ϱ�!
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//update
	public int update(QnA qna) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result=0;
		String sql = "update qna set writer=?, title=?, content=? where qna_id=?";
		con = dbManager.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getWriter());
			pstmt.setString(2, qna.getTitle());
			pstmt.setString(3, qna.getContent());
			pstmt.setInt(4, qna.getQna_id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
	//delete
	//�ڽ��� ������ ������ ���Դϴ� �ϰ� ������
	//�ڽ��� ������ ���� ����
	public int delete(QnA qna) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result=0;
		
		String sql = "select qna_id from qna where team=? and rank=? and depth=?";
		
		con = dbManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qna.getTeam());
			pstmt.setInt(2, qna.getRank()+1);
			pstmt.setInt(3, qna.getDepth()+1);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//������ �����Ϸ��� ���� �����Ը�
				sql = "update qna set isdel=1 where qna_id=?";
			}else {
				sql = "delete from qna where qna_id=?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna.getQna_id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		return result;
	}
	
}
