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
	/*1. 기존에 내가 본 글보다 rank가 큰 글의 rank는 모두 1씩 증가되시오!!(공간확보)
	= update qna rank=rank+1 where team=내본글team and rank>내본글rank
	2. 빈 공간을 내가 차지!
	= insert into qna(~, team, rank, depth) values(내본team, 내본rank+1, 내본depth+1)*/
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
	
	//답변글 insert
	public int reply() {
		int result=0;
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
				
				list.add(qna);//리스트에 추가하기!
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//update
	public int update() {
		int result=0;
		return result;
	}
	//delete
	public int delete() {
		int result=0;
		return result;
	}
	
}
