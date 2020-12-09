<%@page import="board.model.Comments"%>
<%@page import="board.model.CommentsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/inc/lib.jsp" %>
<%	request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="comments" class="board.model.Comments"/>
<jsp:setProperty property="*" name="comments"/>
<%
	CommentsDAO dao = new CommentsDAO();
	int result = dao.insert(comments);
	if(result==0){
		out.print(getMsgBack("등록 실패"));
	}else{
		out.print(getMsgURL("등록성공", "detail.jsp?news_id="+comments.getNews_id()));
	}
%>