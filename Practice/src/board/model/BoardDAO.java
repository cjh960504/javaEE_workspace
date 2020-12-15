package board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis.conf.MybatisConfigManager;

public class BoardDAO {
	MybatisConfigManager manager = MybatisConfigManager.getInstance();
	
	public int insert(Board board) {
		int result = 0;
		SqlSession sqlSession = manager.getSession();
		result = sqlSession.insert("Board.insert", board);
		sqlSession.commit();
		manager.close(sqlSession);
		return result;
	}
	
	public Board select(int board_id) {
		Board board;
		SqlSession sqlSession = manager.getSession();
		board = sqlSession.selectOne("Board.select", board_id);
		manager.close(sqlSession);
		return board;
	}
	
	public List<Board> selectAll(){
		List<Board> list;
		SqlSession sqlSession = manager.getSession();
		list = sqlSession.selectList("Board.selectAll");
		manager.close(sqlSession);
		return list;
	}
}
