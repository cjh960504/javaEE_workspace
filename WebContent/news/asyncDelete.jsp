<%@page import="board.model.Comments"%>
<%@page import="java.util.List"%>
<%@page import="board.model.CommentsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//넘겨받은 comments_id 를 이용하여 삭제 후, 알맞은 피드백 문자열로 응답!
	String comments_id = request.getParameter("comments_id");
	String news_id = request.getParameter("news_id");
	StringBuilder sb = new StringBuilder();
	String resultMsg=null;
	CommentsDAO dao = new CommentsDAO();
	
	int result = dao.delete(Integer.parseInt(comments_id));
	if(result==0){
		resultMsg="코멘트 삭제 실패";
		sb.append("{");
		sb.append("\"resultCode\":"+result);
		sb.append("}");
	}else{
		resultMsg="코멘트 삭제 성공";
		//목록조회
		List<Comments> list = dao.selectAll(Integer.parseInt(news_id)); //뉴스기사에 소속된 모든 댓글 가져오기
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