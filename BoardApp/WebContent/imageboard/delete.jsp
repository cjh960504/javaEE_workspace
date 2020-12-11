<%@page import="java.io.File"%>
<%@page import="board.model.ImageBoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	//삭제 업무 (DB삭제+파일삭제)
	String board_id = request.getParameter("board_id");
	String filename = request.getParameter("filename");
	File file = new File("C:/workspace/javaee_workspace/BoardApp/WebContent/data/"+filename);
	//파일 삭제 (파일을 다룰 수 있는 클래스 : java.io.File)
	if(file.delete()){ //파일삭제 성공 시 true
		//DB 삭제 
		ImageBoardDAO dao = new ImageBoardDAO();
		int result = dao.delete(Integer.parseInt(board_id));
		if(result==0){
			out.print(getMsgBack("삭제 실패"));
		}else{
			out.print(getMsgURL("삭제 성공", "/imageboard/list.jsp"));
		}		
	}
%>