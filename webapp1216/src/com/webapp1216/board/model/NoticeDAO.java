package com.webapp1216.board.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.webapp1216.mybatis.config.MybatisConfigManager;

public class NoticeDAO {
	MybatisConfigManager manager = MybatisConfigManager.getInstance();
	
	// CRUD
	public int insert(Notice notice) {
		int result = 0;
		//jdbc�� �ƴ� mybatis�� !!
		SqlSession sqlSession = manager.getSqlSession();//������簴ü(xml�� ������ ȣ���� �� �ִ�.)
		result = sqlSession.insert("Notice.insert", notice);
		sqlSession.commit();//DML�� ��� commitó��
		manager.close(sqlSession);
		return result;
	}

	public List<Notice> selectAll() {
		List<Notice> list =null;
		SqlSession sqlSession = manager.getSqlSession();
		list = sqlSession.selectList("Notice.selectAll");
		manager.close(sqlSession);
		return list;
	}

	public Notice select(int notice_id) {
		Notice notice = null;
		SqlSession sqlSession = manager.getSqlSession();
		notice = sqlSession.selectOne("Notice.select", notice_id);
		manager.close(sqlSession);
		return notice;
	}

	public int update(Notice notice) {
		int result = 0;
		SqlSession sqlSession = manager.getSqlSession();
		result = sqlSession.update("Notice.update", notice);
		sqlSession.commit();
		manager.close(sqlSession);
		return result;
	}

	public int delete(int notice_id) {
		int result = 0;
		SqlSession sqlSession = manager.getSqlSession();
		result=sqlSession.delete("Notice.delete", notice_id);
		sqlSession.commit();
		manager.close(sqlSession);
		return result;
	}

}