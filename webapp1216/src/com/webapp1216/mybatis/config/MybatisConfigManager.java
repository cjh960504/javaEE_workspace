/*
	mybatis�� config.xml�ϻ� ���� �������� �ڹ� ���ø����̼ǰ��� ������� �����̴�.
	���� �ڹ��ڵ忡�� config.xml�� �о�鿩���Ѵ�.
	������ǥ : xml�� �鿩��, ���� �������� �����ϴ� ��ü�� [_SqlSession ��� ����_](���丮�� ����)
	
	�� Ŭ������ Ư�� new�Ҷ����� �ν��Ͻ��� ������ ���̰�, �׷��� �Ǹ� SqlSessionFactory�� �ټ��� 
	�޸𸮿� �ö���Ƿ�, �޸� ������ �� ����.. ���� SingleTon���� ��������
*/
package com.webapp1216.mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfigManager {
	//��Ű�� ����ٿ��ֱ� - �Ǽ� ����
	String resource = "com/webapp1216/mybatis/config/config.xml";
	SqlSessionFactory sqlSessionFactory ;
	InputStream inputStream;
	private static MybatisConfigManager instance;
	
	private MybatisConfigManager() {
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static MybatisConfigManager getInstance() {
		if(instance==null) {
			instance = new MybatisConfigManager();
		}
		return instance;
	}
	
	//SqlSession�� �����ϴ� �޼���
	public SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
	
	//SqlSession�� ��ȯ�޴� �޼���
	public void close(SqlSession sqlSession) {
		if(sqlSession!=null) {
			sqlSession.close();
		}
	}
}
