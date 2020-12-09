<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>뉴스 등록</title>
<style>
table{
	width:70%
}
td{
	border:2px solid #cccccc;
}

td input, textarea{
	width:97%;
	font-size:1.2em;
	font-weight: bold;
}

textarea{
	height:150px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function() {
		$($("button")[0]).click(function() {
			regist();
		});
		$($("button")[1]).click(function() {
			location.href="list.jsp";
		});
	});
	
	//폼양식을 서버에 전송하자!!
	function regist() {
		$("form").attr({
			method:"post",
			action:"regist.jsp"
		});
		$("form").submit();
	}
</script>
</head>
<body>
	<form>
		<table align="center">
			<tr>
				<td><input type="text" name="writer" placeholder="작성자 입력"></td>
			</tr>
			<tr>
				<td><input type="text" name="title" placeholder="제목 입력"></td>
			</tr>
			<tr>
				<td><textarea name="content" placeholder="내용 입력"></textarea></td>
			</tr>
			<tr>
				<td align="center">
					<button type="button">등록</button>
					<button type="button">목록</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>