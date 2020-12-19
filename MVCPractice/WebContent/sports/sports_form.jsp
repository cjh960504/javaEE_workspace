<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style></style>
<script>
function send() {
	var form = document.querySelector("form");
	form.action="/sports.do";
	form.method="get";
	form.submit();
}
</script>
</head>
<body>
<form>
	<select name="sports">
		<option>구기종목을 선택해주세요</option>
		<option value="soccer">축구</option>
		<option value="baseball">야구</option>
		<option value="basketball">농구</option>
		<option value="volleyball">배구</option>
	</select>
	<button type="button" onClick="send()">설명보기</button>
</form>

</body>
</html>