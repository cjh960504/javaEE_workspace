package com.webmvc.comment.model;

import org.apache.ibatis.session.SqlSession;

import com.webmvc.domain.Comment;
import com.webmvc.mybatis.config.MybatisConfigManager;

public class CommentDAO {
	MybatisConfigManager manager = MybatisConfigManager.getInstance();
	
	public int insert(Comment comment) {
		SqlSession sqlSession = manager.getSession();
		int result = sqlSession.insert("Comment.insert", comment);
		sqlSession.commit();
		manager.close(sqlSession);
		System.out.println(result);
		return result;
	}
}
