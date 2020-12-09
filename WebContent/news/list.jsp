<%@page import="board.model.News"%>
<%@page import="java.util.List"%>
<%@page import="board.model.NewsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	NewsDAO dao = new NewsDAO();
	List<News> list = dao.selectAll();
	int totalRecord=list.size(); // 총 레코드 수
	int pageSize=10; // 한 페이지 당 볼 수 있는 글의 건수(개발자 임의로 지정 가능) - 레코드를 나눔
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize); //총 페이지의 수
	int blockSize=10;//블럭당 보여질 페이지 수(개발자 임의로 지정가능) - 페이지를 나눔
	int currentPage=1;
	if(request.getParameter("currentPage")!=null){
		currentPage=Integer.parseInt(request.getParameter("currentPage"));
	}
	int firstPage=currentPage-(currentPage-1)%blockSize;
	int lastPage = firstPage+blockSize-1;
	int curPos = (currentPage-1) * pageSize;
	int num = totalRecord-curPos;//페이지당 시작 번호
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
table{
	width:100%;
	border:1px solid #cccccc;
}
th{
	border:1px solid #cccccc;
}
td{
	border:1px solid #cccccc;
}

a{
text-decoration: none;
}

.pageNum{
	font-size:20pt;
	color:blue;
	font-weight:bold;
}

.inactive{
	color:#cccccc;
	text-decoration: line-through;
}
</style>
<script>
	function showColor(obj) {
		obj.style.background="dodgerblue";
	}
	function hideColor(obj) {
		obj.style.background="";
	}
</script>
</head>
<body>
	<table>
		<tr>
			<th width="5%">No</th>
			<th width="70%">제목</th>
			<th width="10%">작성자</th>
			<th width="10%">등록일</th>
			<th width="5%">조회수</th>
		</tr>
		<%for(int i=1;i<=pageSize;i++){ %>
			<%if(num<1) break; %>
			<%News news = list.get(curPos++); %>
			<tr onMouseOver="showColor(this)" onMouseOut="hideColor(this)">
				<td><%=num-- %></td>
				<%if(news.getWriter().length()<1){ %>
					<td><a class="inactive"><%=news.getTitle() %></a></td>
				<%}else{ %>
					<td><a href="detail.jsp?news_id=<%=news.getNews_id()%>"><%=news.getTitle() %>
					<%if(news.getCnt()!=0) {%>
					(<%=news.getCnt() %>)
					<%} %>
					</a></td>
				<%} %>
				<td><%=news.getWriter() %></td>
				<td><%=news.getRegdate() %></td>
				<td><%=news.getHit() %></td>
			</tr>
		<%} %>
	<tr>
		<td colspan="5" align="center">
			<%if(firstPage-1>0){ %>
				<a href="list.jsp?currentPage=<%=firstPage-1%>">◀</a>
			<%}else{ %>
				<a href="javascript:alert('처음 페이지입니다!')">◀</a>
			<%} %>
			<%for(int i=firstPage;i<=lastPage;i++){ %>
				<%if(i>totalPage) break;%>
				<a href="list.jsp?currentPage=<%=i%>" <%if(currentPage==i){ %>class="pageNum"<%}%>>[<%=i %>]</a>
			<%} %>
			<%if(lastPage<totalPage){ %>
				<a href="list.jsp?currentPage=<%=lastPage+1%>">▶</a>
			<%}else{ %>
				<a href="javascript:alert('마지막 페이지입니다!')">▶</a>
			<%} %>
		</td>
	</tr>
	<tr>
		<td colspan="5">
			<button onClick="location.href='regist_form.jsp';">글쓰기</button>
		</td>
	</tr>
	</table>
</body>
</html>