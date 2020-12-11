<%@page import="board.model.News"%>
<%@page import="java.util.List"%>
<%@page import="board.model.NewsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	NewsDAO dao = new NewsDAO();
	List<News> list = dao.selectAll();
	
	
	
	StringBuilder sb = new StringBuilder();
	sb.append("{");
	sb.append("\"resultCode\":"+1+",");
	sb.append("\"newsList\":[");
	for(int i=0;i<list.size();i++){
		News news = list.get(i);
		sb.append("{");
		sb.append("\"news_id\":"+news.getNews_id()+",");
		sb.append("\"title\":\""+news.getTitle()+"\",");
		sb.append("\"writer\":\""+news.getWriter()+"\",");
		sb.append("\"regdate\":\""+news.getRegdate()+"\",");
		sb.append("\"hit\":"+news.getHit());
		if(i==list.size()-1){
			sb.append("}");
		}else{
			sb.append("},");	
		}
	}
	sb.append("]");
	sb.append("}");
	
	out.print(sb.toString());
%>