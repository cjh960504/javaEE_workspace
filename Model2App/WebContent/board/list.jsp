<%@page import="common.board.Pager"%>
<%@page import="com.model2.domain.Board"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	List<Board> list = (List<Board>)request.getAttribute("boardList");
	Pager pager = new Pager();
	pager.init(request, list);
	int totalPage = pager.getTotalPage();
	int pageSize = pager.getPageSize();
	int currentPage = pager.getCurrentPage();
	int firstPage = pager.getFirstPage();
	int lastPage = pager.getLastPage();
	int curPos = pager.getCurPos();
	int num = pager.getNum();
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
	text-align: center;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

a{
text-decoration: none;
}
</style>
</head>
<body>

	<table>
		<tr>
			<th width="10%">No</th>
			<th width="50%">제목</th>
			<th width="20%">작성자</th>
			<th width="10%">작성날짜</th>
			<th width="10%">조회수</th>
		</tr>
		<%for(int i=0;i<pageSize;i++){%>
		<%if(num<1) break; %>
		<%Board board = list.get(curPos++);%>
		<tr>
			<td><%=num-- %></td>
			<td><a href="/board/detail.do?board_id=<%=board.getBoard_id()%>"><%=board.getTitle() %>(<%=board.getCnt() %>)</a></td>
			<td><%=board.getWriter() %></td>
			<td><%=board.getRegdate().substring(0, 16) %></td>
			<td><%=board.getHit()%></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="5" style="text-align:center">
			<%if(firstPage-1<1) {%>
				<a href="javascript:alert('처음페이지입니다.')">◀</a>
			<%}else{ %>
				<a href="/board/list.do?currentPage=<%=firstPage-1 %>">◀</a>
			<%} %>
				<%for(int i=firstPage;i<=lastPage;i++){ %>
				<%if(i>totalPage) break;%>
					<a href="/board/list.do?currentPage=<%=i%>"><%=i %></a>
				<%} %>
				<%if(lastPage>=totalPage) {%>
				<a href="javascript:alert('마지막페이지입니다.')">▶</a>
			<%}else{ %>
				<a href="/board/list.do?currentPage=<%=lastPage+1 %>">▶</a>
			<%} %>
			</td>
		</tr>
		<tr>
			<td colspan="5">
				<button onClick="location.href='/board/regist_form.jsp'"> 글등록</button>
			</td>
		</tr>
	</table>

</body>
</html>