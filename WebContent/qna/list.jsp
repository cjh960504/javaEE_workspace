<%@page import="board.model.QnADAO"%>
<%@page import="board.model.QnA"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	// import= (앞에는 환경변수에 있는 ClassPath 경로를 참조한다)"pet.Dog";
%>
<%
	QnADAO dao = new QnADAO();
	 List<QnA> list= dao.selectAll();
	String dir = "C:/workspace/javaee_workspace/qnaApp/WebContent/data/";
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

button {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
}

input[type=image]{
  width:100px;
 
}

img{
box-sizing: border-box;
}

a{
	text-decoration: none;
}

.del{
	text-decoration: line-through;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
		$("button").on("click", function(){
			location.href="/qna/regist_form.jsp";
		});
	});
</script>
</head>
<body>

<h2>게시판</h2>

<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>작성자</th>
	<th>등록일</th>
	<th>조회수</th>
  </tr>
 
  <%for(int i=0;i<list.size();i++){%>
  <%QnA qna= list.get(i);%>
	<tr>
		<td><%=qna.getQna_id()%></td>
		<td>
			<%if(qna.getDepth()>0){ %>
				<img alt="" src="/images/reply.png" style="margin-left:<%=20*qna.getDepth()%>px">
			<%} %>
			<%if(qna.getIsdel()==0){ %>
				<a href="/qna/detail.jsp?qna_id=<%=qna.getQna_id()%>"><%=qna.getTitle()%></a>
			<%}else{%>
				<a class="del">삭제된 글입니다.</a>
			<% } %>
		</td>
		<td><%=qna.getWriter()%></td>
		<td><%=qna.getRegdate().substring(0, 10)%></td>
		<td><%=qna.getHit()%></td>
	</tr>
  <%}%>  
  <tr>
	<td colspan="5">
		<button>글 등록</button>
	</td>
  </tr>
  <tr>
	<td colspan="5" style="text-align:center">
		<%@ include file="/inc/footer.jsp"%>
	</td>
  </tr>
</table>
</body>
</html>
