/*
 * 기존 전통적인 JDBC방식으로 작성했던 DAO의 CRUD메서드를 mybatis 프레임웍을 이용하여
 * 코드를 간략화시켜보자!!!
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
		// namespace아래의 id로 구분
		result = session.insert("Board.insert", board);
		session.commit();// DML의 경우 Commit필수!!
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
