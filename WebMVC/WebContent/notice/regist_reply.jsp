<%@ page contentType="text/html;charset=utf-8"%>
<%
	String msg = request.getParameter("msg");
	String author = request.getParameter("author");
	String notice_id = request.getParameter("notice_id");
	out.print(msg+"/"+author+"/"+notice_id);
%>