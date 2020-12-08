<%@page import="board.model.QnA"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.model.QnADAO2"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	QnADAO2 dao = new QnADAO2();
	ArrayList<QnA> list = dao.selectAll();
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
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$("input[type='button']").click(function(){
			location.href="/qna/regist2_form.jsp";
		});
	});
</script>
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
  <%for(int i=0;i<list.size();i++){ %>
  <%QnA qna = list.get(i); %>
	  <tr>
	    <td><%=qna.getQna_id()%></td>
	    <td>
	    <%if(qna.getDepth()>0){ %>
	    <img alt="" src="/images/reply.png"/  style="margin-left:<%=20*qna.getDepth()%>px">
	    <%} %>
	    <a href="/qna/detail2.jsp?qna_id=<%=qna.getQna_id()%>"><%=qna.getTitle() %></a>
	    </td>
	    <td><%=qna.getWriter() %></td>
	    <td><%=qna.getRegdate() %></td>
	    <td><%=qna.getHit() %></td>
	  </tr>
  <%} %>
  <tr>
  	<td><input type="button" value="글쓰기"></td>
  </tr>
  </table>

</body>
</html>
