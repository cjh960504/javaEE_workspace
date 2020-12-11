<%@page import="board.model.NewsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/inc/lib.jsp" %>
<%//댓글(자식)이 존재하여 삭제하지 않고 삭제를 대체하는 수정을 하는 jsp %>
<%
	String news_id = request.getParameter("news_id");
	NewsDAO dao = new NewsDAO();
	int result = dao.replace(Integer.parseInt(news_id));
	if(result==0){
		out.print(getMsgBack("삭제 실패"));
	}else{
		out.print(getMsgURL("삭제 성공", "list.jsp"));
	}
%>
