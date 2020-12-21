<%@page import="com.model2.domain.Comment"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/json;charset=utf-8"%>
<%
	out.print(request.getAttribute("result"));
%>