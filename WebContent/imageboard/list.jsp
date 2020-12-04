<%@page import="board.model.ImageBoard"%>
<%@page import="board.model.ImageBoardDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	// import= (앞에는 환경변수에 있는 ClassPath 경로를 참조한다)"pet.Dog";
%>
<%
	ImageBoardDAO dao = new ImageBoardDAO();
	ArrayList<ImageBoard> list = new ArrayList<ImageBoard>();
	list = dao.selectAll();
	String dir = "C:/workspace/javaee_workspace/BoardApp/WebContent/data/";
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
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
		$("button").on("click", function(){
			location.href="/imageboard/regist_form.jsp";
		});
	});
</script>
</head>
<body>

<h2>게시판</h2>

<table>
  <tr>
    <th>No</th>
    <th></th>
    <th>제목</th>
    <th>작성자</th>
	<th>등록일</th>
	<th>조회수</th>
  </tr>
 
  <%for(int i=0;i<list.size();i++){%>
  <%ImageBoard board= list.get(i);%>
	<tr>
		<td><%=board.getBoard_id()%></td>
		<td><input type="image" src="/data/<%=board.getFilename()%>"></td>
		<td><a href="/imageboard/detail.jsp?board_id=<%=board.getBoard_id()%>"><%=board.getTitle()%></a></td>
		<td><%=board.getAuthor()%></td>
		<td><%=board.getRegdate()%></td>
		<td><%=board.getHit()%></td>
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
