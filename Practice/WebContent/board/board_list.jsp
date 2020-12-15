<%@page import="board.model.Pasing"%>
<%@page import="board.model.Board"%>
<%@page import="java.util.List"%>
<%@page import="board.model.BoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	BoardDAO dao = new BoardDAO();
	List<Board> list = dao.selectAll();
	Pasing pasing = new Pasing();
	pasing.init(list.size(), request);
	int num = pasing.getNum();
	int cusPos = pasing.getCusPos();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style></style>
<script></script>
</head>
<body>
	<table>
		<tr>
			<th>No</th>
			<th>Title</th>
			<th>Writer</th>
			<th>regdate</th>
			<th>hit</th>
		</tr>
		<%for(int i=0;i<list.size();i++){ %>
		<%Board board = list.get(cusPos++); %>
		<tr>
			<td><%=num--%></td>
			<td><a href="detail.jsp?board_id=<%=board.getBoard_id()%>"><%=board.getTitle() %></a></td>
			<td><%=board.getWriter() %></td>
			<td><%=board.getRegdate() %></td>
			<td><%=board.getHit() %></td>
		</tr>
		<%} %>
		<tr>
			<td>
			<%if(pasing.getFirstPage()-1>0) {%>
			<a href="board_list?currentPage=<%=pasing.getFirstPage()-1%>">◀</a>
			<%}else{ %>
			<a href="javascript:alert('처음페이지입니다')">◀</a>
			<%} %>
			<%for(int i=pasing.getFirstPage();i<=pasing.getLastPage();i++){ %>
				<%if(pasing.getTotalPage()<i) break; %>
				<a href="board_list.jsp?currentPage=<%=i%>">[<%=i %>]</a>
			<%} %>
			<%if(pasing.getLastPage()>=pasing.getTotalPage()){ %>
				<a href="javascript:alert('마지막페이지입니다')">▶</a>
			<%}else{ %>
				<a href="board_list?currentPage=<%=pasing.getLastPage()+1%>">▶</a>
			<%} %>
			</td>
		</tr>
	</table>
</body>
</html>