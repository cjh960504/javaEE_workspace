<%@page import="board.model.QnA"%>
<%@page import="java.util.List"%>
<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	QnADAO dao = new QnADAO();
	List<QnA> list= dao.selectAll();
	int totalRecord=list.size();
	int pageSize=10;
	int currentPage=1;
	if(request.getParameter("currentPage")!=null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int totalPage=(int)Math.ceil((float)totalRecord/pageSize);
	int blockSize = 10;
	int firstPage = currentPage - (currentPage-1)%10;
	int lastPage = firstPage+(blockSize-1);
	int num = totalRecord-((currentPage-1)*pageSize);
	int curPos = (currentPage-1)*pageSize;
%>
totalRecord = <%=totalRecord %><br>
pageSize= <%=pageSize%><br>
currentPage= <%=currentPage%><br>
totalPage= <%=totalPage%><br>
blockSize= <%=blockSize %><br>
firstPage =<%=firstPage %><br>
lastPage =<%=lastPage %><br>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
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
</style>
<script></script>
</head>
<body>
<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>작성자</th>
    <th>작성날짜</th>
    <th>조회수</th>
  </tr>
  <%for(int i=1;i<=pageSize;i++){ %>
  <%if(num<1) break;%>
  <% QnA qna = list.get(curPos++);%>
	  <tr>
	  <td><%=num-- %></td>
	 	<td><%=qna.getTitle() %></td>
	  </tr>
  <%} %>

	<tr>
		<td colspan="5" style="text-align: center">
			<%if(firstPage-1>0) {%>
			 <a href="/qna/pageList.jsp?currentPage=<%=firstPage-1%>">◀</a>
			 <%}else{ %>
			 <a href="javascript:alert('처음 페이지입니다.')">◀</a>
			 <%} %>
			<%for(int i=firstPage;i<=lastPage;i++) {%>
				<%if(i<=totalPage){%>
				<a href="/qna/pageList.jsp?currentPage=<%=i%>"><%=i %></a>
				<%} %>
			<%} %>
			<%if(lastPage<totalPage){ %>
			<a href="/qna/pageList.jsp?currentPage=<%=lastPage+1%>">▶</a>
			<%}else{ %>
			<a href="javascript:alert('마지막 페이지입니다.')">▶</a>
			<%} %>
		</td>
	</tr>
  <tr>
  	<td colspan="5"><input type="button" value="글쓰기"></td>
  </tr>
  </table>
</body>
</html>