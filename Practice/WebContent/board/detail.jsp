<%@page import="board.model.Board"%>
<%@page import="board.model.BoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	String board_id = request.getParameter("board_id");
	BoardDAO dao = new BoardDAO();
	Board board = dao.select(Integer.parseInt(board_id));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>

table{
width:70%
}
input, textarea{
width:65%
}
</style>
<script></script>
</head>
<body>
	<form name="form1">
		<table border="1px" >
			<tr>
				<td><input type="text" name="title" value="<%=board.getTitle()%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="title" value="<%=board.getWriter()%>"></td>
			</tr>
			<tr>
				<td>
					<textarea rows="5" cols="10"><%=board.getContent() %></textarea>
				</td>
			</tr>
			<tr>
				<td>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>