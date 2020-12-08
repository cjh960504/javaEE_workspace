<%@page import="board.model.QnADAO"%>
<%@page import="board.model.QnA"%>
<%@include file="/inc/lib.jsp" %>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//넘겨받은 파라미터 값을 이용하여 답글 달자!
	request.setCharacterEncoding("utf-8");

	String team = request.getParameter("team");//내본글 team
	String rank = request.getParameter("rank");//내본글 rank
	String depth = request.getParameter("depth");//내본글 depth
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	QnA qna = new QnA();
	qna.setWriter(writer);
	qna.setTitle(title);
	qna.setContent(content);
	qna.setTeam(Integer.parseInt(team));
	qna.setRank(Integer.parseInt(rank));
	qna.setDepth(Integer.parseInt(depth));
	
	QnADAO dao = new QnADAO();
	int result = dao.reply(qna);
	if(result==0){
		out.print(getMsgBack("답글 등록 실패"));
	}else{
		out.print(getMsgURL("답글 등록 성공", "/qna/list.jsp"));
	}

	//1단계 : 후발로 등록된 글이 들어갈 자리 확보 (기존 글들을 밀어내는 효과)
	//답글을 달기 위한 쿼리문을 알아야 한다!
	//String sql = "update qna set rank=rank+1 where team="+team+" and rank>"+rank;
	///out.print(sql);
	
	//out.print("<br>");
	//2단계 : 내가 본 글의 바로 아래쪽에 답변 insert
	//sql="insert into qna(team, rank, depth) values("+team+","+(rank+1)+","+(depth+1)+")";
	//out.print(sql);
%>