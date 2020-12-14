<%@page import="board.model.MyBatisBoardDAO"%>
<%@page import="common.file.FileManager"%>
<%@page import="board.model.BoardDAO"%>
<%@include file="/inc/lib.jsp" %>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//넘겨받은 board_id를 이용하여 글 한건 삭제
	String board_id = request.getParameter("board_id");
	String filename = request.getParameter("filename");
	//BoardDAO dao = new BoardDAO();
	MyBatisBoardDAO dao = new MyBatisBoardDAO();
	
	//이미지 삭제하고, db레코드지우기
	String realPath = application.getRealPath("/data");	
	if(FileManager.deleteFile(realPath+"/"+filename)){
		int result = dao.delete(Integer.parseInt(board_id));
		if (result == 0) {
			out.print(getMsgBack("삭제실패"));
		} else {
			out.print(getMsgURL("삭제성공","/board/list.jsp"));
		}		
	}else{
		out.print(getMsgBack("파일을 삭제할 수 없습니다."));
	}
%>