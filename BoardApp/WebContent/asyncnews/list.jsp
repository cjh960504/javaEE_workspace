<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style></style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function() {
		asyncList();
	});
	
	function asyncList() {
		var xhttp = new XMLHttpRequest();  //비동기 통신 객체
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				getList(this.responseText); //코멘트 리스트 동적 출력
			}
		};
		xhttp.open("GET", "/asyncnews/asyncList.jsp", true); //true:비동기 false:동기
		xhttp.send();
	}
	
	function getList(data) {
		var list = document.getElementById("list");
		list.innerHTML ="";
		var tag="";
		
		var json = JSON.parse(data);
		
		if(json.resultCode==0){
			alert("목록조회 실패");
		}else{
			var newsList = json.newsList;
			tag += "<th>No</th>";
			tag +=	"<th>제목</th>"
			tag +=	"<th>작성자</th>";
			tag +=	"<th>작성날짜</th>";
			tag +=	"<th>조회수</th>";
			for(var i=0;i<newsList.length;i++){
				var news = newsList[i];
				console.log(news);
				tag += "<tr>";
				tag += "<td>"+news.news_id+"</td>";
				tag += "<td>"+news.title+"</td>";
				tag += "<td>"+news.writer+"</td>";
				tag += "<td>"+news.regdate+"</td>";
				tag += "<td>"+news.hit+"</td>";
				tag += "</tr>";
			}
			
			list.innerHTML=tag;
		}
	}
</script>
</head>
<body>
	<table id="list">
	</table>
</body>
</html>