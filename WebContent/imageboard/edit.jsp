<%@page import="board.model.ImageBoard"%>
<%@page import="board.model.ImageBoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
	String board_id = request.getParameter("board_id");
	String author = request.getParameter("author");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	ImageBoard board = new ImageBoard();
	board.setBoard_id(Integer.parseInt(board_id));
	board.setAuthor(author);
	board.setTitle(title);
	board.setContent(content);
	
	ImageBoardDAO dao = new ImageBoardDAO();
	int result = dao.update(board);
	if(result==0){
		out.print(getMsgBack("수정 실패"));
	}else{
		out.print(getMsgURL("수정 성공", "/imageboard/list.jsp"));
	}
%>