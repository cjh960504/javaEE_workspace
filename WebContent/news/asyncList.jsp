<%@page import="board.model.Comments"%>
<%@page import="java.util.List"%>
<%@page import="board.model.CommentsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//넘겨받은 news_id 파라미터 값으로, 하위에 등록된 모든 코멘트 목록 가져온 후 
	// json문자열로 응답하자!
	String news_id = request.getParameter("news_id");
	StringBuilder sb = new StringBuilder();
	CommentsDAO dao = new CommentsDAO();
	List<Comments> list = dao.selectAll(Integer.parseInt(news_id));
	//html로 구성된 클라이언트가 이해할 수 있는 포맷으로 보내자!! json, xml 
	sb.append("{");
	sb.append("\"resultCode\":"+1+",");
	sb.append("\"commentsList\":[");
	for(int i=0;i<list.size();i++){
		Comments c = list.get(i);
		sb.append("{");
		sb.append("\"comments_id\":"+c.getComments_id()+",");
		sb.append("\"author\":\""+c.getAuthor()+"\",");
		sb.append("\"msg\":\""+c.getMsg()+"\",");
		sb.append("\"cdate\":\""+c.getCdate()+"\"");
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