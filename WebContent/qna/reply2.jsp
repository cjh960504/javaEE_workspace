<%@page import="board.model.QnADAO2"%>
<%@page import="board.model.QnA"%>
<%@include file="/inc/lib.jsp" %>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String team = request.getParameter("team");
	String rank = request.getParameter("rank");
	String depth = request.getParameter("depth");
	String writer = request.getParameter("writer"); 
	String title = request.getParameter("title"); 
	String content = request.getParameter("content"); 
	
	QnA qna = new QnA();
	qna.setTeam(Integer.parseInt(team));
	qna.setRank(Integer.parseInt(rank));
	qna.setDepth(Integer.parseInt(depth));
	qna.setWriter(writer);
	qna.setTitle(title);
	qna.setContent(content);
	
	QnADAO2 dao = new QnADAO2();
	int result = dao.reply(qna);
	if(result==0){
		out.print(getMsgBack("답글등록 실패"));
	}else{
		out.print(getMsgURL("답글등록 성공", "/qna/list3.jsp"));
	}
	
%>