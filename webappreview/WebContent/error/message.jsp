<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style></style>
<script></script>
</head>
<body>
	<p><%=session.getAttribute("msg") %></p>
	<a href="/board/list">목록으로</a>
</body>
</html>