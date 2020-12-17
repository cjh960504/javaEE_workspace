<%@page import="board.model.MyBatisBoardDAO"%>
<%@page import="com.webappreview.common.board.Pager"%>
<%@page import="board.model.Board"%>
<%@page import="java.util.List"%>
<%@page import="board.model.BoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//BoardDAO dao = new BoardDAO();
	MyBatisBoardDAO dao = new MyBatisBoardDAO();
	List<Board> list = dao.selectAll();
	Pager pager = new Pager();
	pager.init(request, list); // 페이지 처리에 대한 계산!!
	/*
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
	*/
	
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
		<%int num = pager.getNum(); %>
		<%int cusPos = pager.getCurPos(); %>
		<%for(int i=0;i<pager.getPageSize();i++){ %>
			<%if(num<1)break; %>
				<%Board board = list.get(cusPos++); %>
				<tr>
					<td><%=num-- %></td>
					<td><img src="/data/<%=board.getFilename() %>"></td>
					<td><a href="detail.jsp?board_id=<%=board.getBoard_id()%>"><%=board.getTitle() %></a></td>
					<td><%=board.getWriter() %></td>
					<td><%=board.getRegdate() %></td>
					<td><%=board.getHit() %></td>
				</tr>
		<%} %>
		<tr>
			<td colspan="6" style="text-align: center">
			<%if(pager.getFirstPage()-1<=0){ %>
				<a href="javascript:alert('처음페이지입니다.')">◀</a>
			<%}else{ %>
			<a href="list.jsp?currentPage=<%=pager.getFirstPage()-1%>">◀</a>
			<%} %>
				<%for(int i=pager.getFirstPage();i<=pager.getLastPage();i++) {%>
					<%if(i<=pager.getTotalPage()){ %>
						<a href="list.jsp?currentPage=<%=i%>">[<%=i %>]</a>
					<%} %>
				<%} %>
				<%if(pager.getLastPage()>=pager.getTotalPage()){ %>
				<a href="javascript:alert('마지막페이지입니다.')">▶</a>
			<%}else{ %>
				<a href="list.jsp?currentPage=<%=pager.getLastPage()+1%>">▶</a>
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
