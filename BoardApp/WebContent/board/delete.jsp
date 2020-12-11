<%@ page contentType="text/html;charset=utf-8"%>
 <%@page import="board.model.NoticeDAO"%>

<%@ include file="/inc/lib.jsp"%>
<%
	String notice_id=request.getParameter("notice_id");
	NoticeDAO noticeDAO = new NoticeDAO();	
	int result = noticeDAO.delete(Integer.parseInt(notice_id));

	if(result==0){
		out.print(getMsgURL("삭제 실패!", "location.href='/board/detail.jsp?notice_id="+notice_id));
	}else{
		out.print(getMsgURL("삭제 성공!", "/board/list.jsp"));
	}
%>