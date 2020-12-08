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
	
	//원글 insert
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
			
			//team을 방금 들어간 레코드에 의해 발생한 pk값으로 업데이트!!!
			sql="update qna set team=(select last_insert_id()) where qna_id=(select last_insert_id())";
			pstmt = con.prepareStatement(sql);//쿼리문 1:1 대응하게!
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
	
	/*1. 기존에 내가 본 글보다 rank가 큰 글의 rank는 모두 1씩 증가되시오!!(공간확보)
	= update qna rank=rank+1 where team=내본글team and rank>내본글rank
	2. 빈 공간을 내가 차지!
	= insert into qna(~, team, rank, depth) values(내본team, 내본rank+1, 내본depth+1)*/
	
	//답변글 insert
	public int reply(QnA qna) {
		int result=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update qna set rank=rank+1 where team=? and rank>?";
		
		con = dbManager.getConnection();
		
		try {
			con.setAutoCommit(false);//자동으로 커밋하지마!!!
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
			pstmt.setInt(5, qna.getRank()+1);//내본글 다음에 위치하기 위해 +1
			pstmt.setInt(6, qna.getDepth()+1);//내본글에 대한 답변이므로+1
			result = pstmt.executeUpdate();
			
			con.commit();// 여기서 커밋 즉 둘다 에러나지 않고 try를 완료하면 모두 성공으로 간주!!
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();//두 쿼리문 중 에러가 하나라도 발생하면, 차라리 처음부터 없었던 일로 하자!!
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
				QnA qna = new QnA(); //레코드 수만큼 VO생성
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
				list.add(qna);//리스트에 추가하기!
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
	//자식이 있으면 삭제된 글입니다 하고 못보게
	//자식이 없으면 삭제 가능
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
				//있으면 삭제하려는 글을 못보게만
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
