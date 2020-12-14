<%@page import="admin.member.Admin"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/inc/lib.jsp" %>
<%
	/* 
	원래는 데이터베이스에서 조회를 해야하지만, 추후 하기로 하고
	일단은 스트링으로 비교해본다..
	*/
	String admin_id = "scott";
	String admin_pass="1234";
	
	String mid = request.getParameter("mid");
	String password = request.getParameter("password");
	
	if(admin_id.equals(mid)&&admin_pass.equals(password)){
		//로그인 성공에 대한 보상, 관리자 페이지 보여주기
		//js의 location.href와 동일한 기능의 jsp 기능 이용해보자
		Admin admin = new Admin();
		admin.setMid(mid);
		admin.setPassword(password);
		response.sendRedirect("/admin?admin="+admin);//클라이언트로 하여금 지정한 url로 요청을 시도
	}else{
		out.print(getMsgBack("로그인 정보가 일치하지 않습니다"));
	}
%>