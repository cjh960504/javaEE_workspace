<%@page import="board.model.NewsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/inc/lib.jsp"%>
<%request.setCharacterEncoding("utf8"); %>
<jsp:useBean id="news" class="board.model.News"/>
<jsp:setProperty property="*" name="news"/>
<%
	NewsDAO dao = new NewsDAO();
	int result = dao.update(news);
	if(result==0){
		out.print(getMsgBack("수정 실패"));
	}else{
		out.print(getMsgURL("수정 성공", "detail.jsp?news_id="+news.getNews_id()));
	}
%>