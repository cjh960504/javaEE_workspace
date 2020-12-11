<%@page import="java.util.List"%>
<%@page import="board.model.Comments"%>
<%@page import="board.model.CommentsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/inc/lib.jsp" %>
<%	request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="comments" class="board.model.Comments"/>
<jsp:setProperty property="*" name="comments"/>
<%
	CommentsDAO dao = new CommentsDAO();
	String resultMsg = null;
	StringBuilder sb = new StringBuilder();
	
	int result = dao.insert(comments);
	if(result==0){
		resultMsg="코멘트 등록 실패";
		sb.append("{");
		sb.append("\"resultCode\":"+result);
		sb.append("}");
	}else{
		resultMsg="코멘트 등록 성공";
		//목록조회
		List<Comments> list = dao.selectAll(comments.getNews_id()); //뉴스기사에 소속된 모든 댓글 가져오기
		//html로 구성된 클라이언트가 이해할 수 있는 포맷으로 보내자!! json, xml 
		sb.append("{");
		sb.append("\"resultCode\":"+result+",");
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
	}
	
	out.print(sb.toString());
%>