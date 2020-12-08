<%@page import="board.model.QnA"%>
<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/inc/lib.jsp" %>
<%

	int qna_id = Integer.parseInt(request.getParameter("qna_id"));
	int team = Integer.parseInt(request.getParameter("team"));
	int rank = Integer.parseInt(request.getParameter("rank"));
	int depth = Integer.parseInt(request.getParameter("depth"));
	
	
	QnADAO dao = new QnADAO();
	QnA qna = new QnA();
	
	qna.setQna_id(qna_id);
	qna.setTeam(team);
	qna.setRank(rank);
	qna.setDepth(depth);
	
	int result = dao.delete(qna);
	if(result==0){
		out.print(getMsgBack("삭제 실패"));
	}else{
		out.print(getMsgURL("삭제 성공", "/qna/list.jsp"));
	}
%>