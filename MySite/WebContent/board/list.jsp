<%@page import="board.model.Board"%>
<%@page import="java.util.List"%>
<%@page import="board.model.BoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	BoardDAO dao = new BoardDAO();
	List<Board> list = dao.selectAll();
	
	int totalRecord = list.size();
	int pageSize = 10;
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize);
	int currentPage = 1;
	if(request.getParameter("currentPage")!=null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int blockSize=10;
	int firstPage = currentPage - (currentPage-1)%blockSize;
	int lastPage = (firstPage+blockSize)-1;
	int curPos = (currentPage-1)*pageSize;
	int num = totalRecord-curPos;
	
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

img{
	width:100px;
}
</style>
</head>
<body>

	<table>
		<tr>
			<th>No</th>
			<th>이미지</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<%for(int i=0;i<pageSize;i++){ %>
			<%if(num>0){ %>
				<%Board board = list.get(i); %>
				<tr>
					<td><%=num-- %></td>
					<td><img src="/data/<%=board.getFilename() %>"></td>
					<td><%=board.getTitle() %></td>
					<td><%=board.getWriter() %></td>
					<td><%=board.getRegdate() %></td>
					<td><%=board.getHit() %></td>
				</tr>
			<%} %>
		<%} %>
		<tr>
			<td colspan="6" style="text-align: center">
			<%if(firstPage-1<=0){ %>
				<a href="javascript:alert('처음페이지입니다.')">◀</a>
			<%}else{ %>
			<a href="list.jsp?currentPage=<%=firstPage-1%>">◀</a>
			<%} %>
				<%for(int i=firstPage;i<=lastPage;i++) {%>
					<%if(i<=totalPage){ %>
						<a href="list.jsp?currentPage=<%=i%>">[<%=i %>]</a>
					<%} %>
				<%} %>
				<%if(lastPage>=totalPage){ %>
				<a href="javascript:alert('마지막페이지입니다.')">▶</a>
			<%}else{ %>
				<a href="list.jsp?currentPage=<%=lastPage+1%>">▶</a>
			<%} %>
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<button type="button" onClick="location.href='regist_form.jsp'">글등록</button>
			</td>
		</tr>
	</table>

</body>
</html>
