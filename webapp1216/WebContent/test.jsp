<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//데이터베이스와 연결이 된다면 모든 설정이 완료된 것임...
	
	InitialContext context = new InitialContext();//JNDI검색객체
	DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/mysql");
	
	//찾아낸 커넥션풀을 이용하여 커넥션한개를 끄집어내고 주소값이 나오면 성공!!
	Connection con = dataSource.getConnection();
	out.print(con);//주소값 나오나 확인
%>