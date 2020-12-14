/*
 * ���� �������� JDBC������� �ۼ��ߴ� DAO�� CRUD�޼��带 mybatis �����ӿ��� �̿��Ͽ�
 * �ڵ带 ����ȭ���Ѻ���!!!
*/
package board.model;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import mybatis.config.MybatisConfigManager;

public class MyBatisBoardDAO {
	MybatisConfigManager configManager = MybatisConfigManager.getInstance();

	public int insert(Board board) {
		int result = 0;
		SqlSession session = configManager.getSession();
		// namespace�Ʒ��� id�� ����
		result = session.insert("Board.insert", board);
		session.commit();// DML�� ��� Commit�ʼ�!!
		configManager.close(session);
		return result;
	}

	public List selectAll() {
		SqlSession session = configManager.getSession();
		List<Board> list = session.selectList("Board.selectAll");
		configManager.close(session);
		return list;
	}

	public Board select(int board_id) {
		SqlSession session = configManager.getSession();
		Board board;
		board = session.selectOne("Board.select", board_id);
		configManager.close(session);
		return board;
	}

	public int update(Board board) {
		SqlSession session = configManager.getSession();
		int result = 0;
		result = session.update("Board.update", board);
		session.commit();
		configManager.close(session);
		return result;
	}
	
	public int delete(int board_id) {
		SqlSession session = configManager.getSession();
		int result=0;
		result = session.delete("Board.delete", board_id);
		session.commit();
		configManager.close(session);
		return result;
	}
}
