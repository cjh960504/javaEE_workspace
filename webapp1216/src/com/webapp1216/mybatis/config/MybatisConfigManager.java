/*
	mybatis의 config.xml일뿐 현재 실행중인 자바 어플리케이션과는 상관없는 상태이다.
	따라서 자바코드에서 config.xml을 읽어들여야한다.
	최종목표 : xml을 들여서, 실제 쿼리문을 수행하는 객체인 [_SqlSession 얻기 위함_](팩토리로 부터)
	
	이 클래스는 특히 new할때마다 인스턴스가 생성될 것이고, 그렇게 되면 SqlSessionFactory로 다수가 
	메모리에 올라오므로, 메모리 누수가 될 것임.. 따라서 SingleTon으로 정의하자
*/
package com.webapp1216.mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfigManager {
	//패키지 복사붙여넣기 - 실수 방지
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
	
	//SqlSession을 제공하는 메서드
	public SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
	
	//SqlSession을 반환받는 메서드
	public void close(SqlSession sqlSession) {
		if(sqlSession!=null) {
			sqlSession.close();
		}
	}
}
