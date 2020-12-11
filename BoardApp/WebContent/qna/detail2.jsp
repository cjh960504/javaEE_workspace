<%@page import="board.model.QnA"%>
<%@page import="board.model.QnADAO2"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	String qna_id =request.getParameter("qna_id");
	QnADAO2 dao = new QnADAO2();
	QnA qna = dao.select(Integer.parseInt(qna_id));
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=submit] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$($("input[type='button']")[0]).click(function(){
			$("form").attr({
				method:"post",
				action:"/qna/reply2_form.jsp"
			});
			$("form").submit();
		});
		$($("input[type='button']")[1]).click(function(){
			location.href="/qna/list3.jsp"
		});
	})
</script>
</head>
<body>


<div class="container">
  <form>
  	<input type="hidden" name="qna_id" value=<%=qna.getQna_id()%>>
  	<input type="hidden" name="team" value=<%=qna.getTeam()%>>
  	<input type="hidden" name="rank" value=<%=qna.getRank()%>>
  	<input type="hidden" name="depth" value=<%=qna.getDepth()%>>
    <label for="fname">작성자</label>
    <input type="text" id="fname" name="writer" value=<%=qna.getWriter() %>>

    <label for="lname">글제목</label>
    <input type="text" id="lname" name="title" value=<%=qna.getTitle() %>>
	
    <label for="subject">내용</label>
    <textarea id="subject" name="content" placeholder="Write something.." style="height:200px"><%=qna.getContent() %></textarea>

    <input type="button" value="답글달기">
    <input type="button" value="목록으로">
  </form>
</div>

</body>
</html>
