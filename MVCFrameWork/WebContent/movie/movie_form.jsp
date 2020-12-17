<%@page import="blood.model.BloodAdviser"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!-- 서블릿의 service()에 의해 out.print()로 페이지를 보여준다!  -->
<%
		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style></style>
<script>
function send() {
	var form = document.querySelector("form");
	form.action="/movie.do";
	form.method="post";
	form.submit();
}
</script>
</head>
<body>
	<form>
		<select name="movie">
			<option value="none">영화를 선택하세요</option>
			<option value="스타워즈">스타워즈</option>
			<option value="존윅">존윅</option>
			<option value="분뇨의질주">분뇨의질주</option>
			<option value="미션임파서블">미션임파서블</option>
		</select>
		<button type="button" onClick="send()">정보보기</button>
	</form>
</body>
</html>