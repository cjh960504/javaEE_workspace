package com.webmvc.notice.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.webmvc.domain.Notice;
import com.webmvc.mybatis.config.MybatisConfigManager;

public class NoticeDAO {
	MybatisConfigManager manager = MybatisConfigManager.getInstance();
	
	public List selectAll() {
		SqlSession sqlSession = manager.getSession();
		List list = sqlSession.selectList("Notice.selectAll");
		manager.close(sqlSession);
		return list;
	}
	public Notice select(int notice_id) {
		Notice notice=null;
		SqlSession sqlSession = manager.getSession();
		notice = sqlSession.selectOne("Notice.select", notice_id);
		manager.close(sqlSession);
		return notice;
	}
	
	public int insert(Notice notice) {
		int result = 0;
		SqlSession sqlSession = manager.getSession();
		result = sqlSession.insert("Notice.insert", notice);
		sqlSession.commit();
		manager.close(sqlSession);
		return result;
	}
	
	public int update(Notice notice) {
		int result = 0;
		SqlSession sqlSession = manager.getSession();
		result = sqlSession.insert("Notice.update", notice);
		sqlSession.commit();
		manager.close(sqlSession);
		return result;
	}
	public int delete(int notice_id) {
		int result = 0;
		SqlSession sqlSession = manager.getSession();
		result = sqlSession.insert("Notice.delete", notice_id);
		sqlSession.commit();
		manager.close(sqlSession);
		return result;
	}
}
