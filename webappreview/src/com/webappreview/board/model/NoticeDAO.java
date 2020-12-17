package com.webappreview.board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.webappreview.mybatis.config.MybatisConfigManager;

public class NoticeDAO {
	MybatisConfigManager manager = MybatisConfigManager.getInstance();
	
	public Notice select(int notice_id) {
		Notice notice = null;
		SqlSession sqlSession = manager.getSession();
		sqlSession.selectOne("Notice.select", notice_id);
		manager.close(sqlSession);
		return notice;
	}
	
	public List<Notice> selectAll(){
		List<Notice> list = null;
		SqlSession sqlSession = manager.getSession();
		list = sqlSession.selectList("Notice.selectAll");
		manager.close(sqlSession);
		return list;
	}
	
	public int insert(Notice notice) {
		int result = 0;
		SqlSession sqlSession = manager.getSession();
		result = sqlSession.insert("Notice.insert", notice);
		sqlSession.commit();
		manager.close(sqlSession);
		return result;
	}
}
