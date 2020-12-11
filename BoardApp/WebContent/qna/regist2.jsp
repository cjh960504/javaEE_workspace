<%@page import="board.model.QnA"%>
<%@page import="board.model.QnADAO2"%>
<%@include file="/inc/lib.jsp" %>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String writer = request.getParameter("writer"); 
	String title = request.getParameter("title"); 
	String content = request.getParameter("content"); 
		
	QnADAO2 dao = new QnADAO2();
	QnA qna = new QnA();
	qna.setWriter(writer);
	qna.setTitle(title);
	qna.setContent(content);
	
	int result = dao.insert(qna);
	if(result==0){
		out.print(getMsgBack("등록실패"));
	}else{
		out.print(getMsgURL("등록성공", "/qna/list3.jsp"));
	}
%>