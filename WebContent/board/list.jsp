<%@page import="board.model.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	// import= (앞에는 환경변수에 있는 ClassPath 경로를 참조한다)"pet.Dog";
%>
<%
	NoticeDAO noticeDAO = new NoticeDAO();
	ArrayList<Notice> list = noticeDAO.selectAll();
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
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
		$("button").on("click", function(){
			location.href="/board/regist_form.jsp";
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
  <%Notice notice = list.get(i);%>
	<tr>
		<td><%=notice.getNotice_id()%></td>
		<td><%=notice.getAuthor()%></td>
		<td><a href="/board/detail.jsp?notice_id=<%=notice.getNotice_id()%>"><%=notice.getTitle()%></a></td>
		<td><%=notice.getRegdate()%></td>
		<td><%=notice.getHit()%></td>
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
