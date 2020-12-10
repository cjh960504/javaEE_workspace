<%@page import="board.model.Comments"%>
<%@page import="java.util.List"%>
<%@page import="board.model.CommentsDAO"%>
<%@page import="board.model.News"%>
<%@page import="board.model.NewsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	String news_id=request.getParameter("news_id");
	
	NewsDAO dao = new NewsDAO();
	News news = dao.select(Integer.parseInt(news_id));
	
	//이 게시물에 딸려있는 모든 코멘트 게시물 가져오기!!
	CommentsDAO cdao = new CommentsDAO();
	List<Comments> list = cdao.selectAll(Integer.parseInt(news_id));
%>
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

input[name='msg']{
	width:50%
}

input[name='author']{
	width:18%
}

p{
	display:inline-block;
}

.msg{
	width:65%
}

.author{
	width:10%
}

.cdate{
	width:13%
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function() {
		$($("button")[0]).click(function() {//글수정
			if(confirm("수정하시겠습니까?")){
				edit();
			}
		});
		$($("button")[1]).click(function() {//삭제
			if(confirm("삭제하시겠습니까?")){
				del();
			}
		});
		$($("button")[2]).click(function() {//목록
			location.href="list.jsp";
		});
		$($("button")[3]).click(function() {//댓글등록
			reply();
		});
		$($("button")[4]).click(function() {//댓글등록
			asyncReply();
		});
		
		//댓글목록 가져오기!
		asyncList();
	});
	
	//폼양식을 서버에 전송하자!!
	function regist() {
		$("form").attr({
			method:"post",
			action:"regist.jsp"
		});
		$("form").submit();
	}
	//글 삭제하기
	function del() {
		//자식 코멘트가 존재한다면, 업데이트!!!
		<%if(list.size()>0){%>
			$("form").attr({
			method:"post",
			action:"replace.jsp"
		});
		<%}else{%>
		//자식코멘트가 없다면 그냥 삭제
		$("form").attr({
			method:"post",
			action:"delete.jsp"
		});
		<%}%>
		$("form").submit();
	}
	
	//글 수정
	function edit() {
		$("form").attr({
			method:"post",
			action:"edit.jsp"
		});
		$("form").submit();
	}
	
	//서버에 댓글 등록요청하기
	function reply() {
		$("form").attr({
			method:"post",
			action:"reply.jsp"
		});
		
		$("form").submit();
	}
	
	//비동기로 목록 가져오기!!
	function asyncList() {
		var xhttp = new XMLHttpRequest();  //비동기 통신 객체
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				getList(this.responseText); //코멘트 리스트 동적 출력
			}
		};
		xhttp.open("GET", "/news/asyncList.jsp?news_id=<%=news_id%>", true); //true:비동기 false:동기
		xhttp.send();
	}
	
	function asyncReply() {
		var xhttp = new XMLHttpRequest();  //비동기 통신 객체
		xhttp.onreadystatechange = function() {
			/*
			0: request not initialized : 요청준비도 안된 상태 
			1: server connection established : 서버와 네트워크 연결이 된 상태
			2: request received : 요청이 서버에 도달함
			3: processing request : 서버가 요청을 처리중..
			4: request finished and response is ready : 요청처리 완료, 응답을 받은 상태
			*/
			/*
			200 : 성공
			404 : 페이지 없음 
			*/
			if (this.readyState == 4 && this.status == 200) {
				//responseText 응답 텍스트
				//alert(this.responseText);
				
				//최신 댓글목록 가져오기
				
				
				//전체화면 갱신이 아닌, 부분화면 갱신...(새로고치림이 되지 않음)
				//SPA==Single Page Application
				getList(this.responseText); //코멘트 리스트 동적 출력
			}
		};
		var author = $("input[name='author']").val();
		var msg = $("input[name='msg']").val();
		var params="news_id=<%=news_id%>&author="+author+"&msg="+msg; 
		xhttp.open("POST", "/news/asyncReply.jsp", true); //true:비동기 false:동기
		//반드시 open()메서드로 post를 지정한 후에나 아래의 post속성 지정이 가능!
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		//xhttp.send("파라미터명=값&파라미터명=값");
		xhttp.send(params);
	}
	
	//응답받은 응답텍스트를 JSON으로 파싱
	function getList(data) {
		var listBox = document.getElementById("listBox");
		listBox.innerHTML="";
		var tag=""; //div가 누적될 변수
		
		//json을 파싱하자!!
		var json = JSON.parse(data); //파싱을 하게 되면, 그 결과로 반환되는 결과물은 객체가 된다.
									// 따라서 이시점부터는 문자열에 불과했던 데이터들을 객체처럼 접근하여 사용할 수 있다.
		if(json.resultCode==0){
			//alert("등록실패");
		}else{
			var jsonArray = json.commentsList; //배열을 반환
			//alert("현재까지 등록된 댓글의 수는 "+jsonArray.length);
			for(var i=0;i<jsonArray.length;i++){
				var comments = jsonArray[i]; //게시물 한건 반환(json객체)
				console.log(comments.comments_id);
				console.log(comments.author);
				console.log(comments.msg);
				console.log(comments.cdate);
				console.log("-----------------------------------------");
				
				tag += "<div>";
				tag += "<p class=\"msg\">"+comments.msg+"</p>";
				tag += "<p class=\"author\">"+comments.author+"</p>";
				tag += "<p class=\"cdate\">"+comments.cdate.substring(0,10)+"</p>";
				tag += "<p class=\"del\"><button type=\"button\" onClick=\"delComments("+comments.comments_id+");\">삭제</button></p>"
				tag += "</div>";
			}
		}
		listBox.innerHTML=tag;
	}
	
	//코멘트 삭제
	function delComments(comments_id) {
		var ans = confirm(comments_id+"를 삭제하고 싶어?");
		
		if(ans){//확인버튼을 누른 경우만..
			//삭제 후 (비동기), 리스트 가져오기(비동기)
			var xhttp = new XMLHttpRequest();  //비동기 통신 객체
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					getList(this.responseText); //코멘트 리스트 동적 출력
				}
			};
			xhttp.open("GET", "/news/asyncDelete.jsp?comments_id="+comments_id+"&news_id="+<%=news_id%>, true); //true:비동기 false:동기
			xhttp.send();
		}
	}
</script>
</head>
<body>
	<form>
		<input type="hidden" name="news_id" value="<%=news.getNews_id()%>">
		<table align="center">
			<tr>
				<td><input type="text" name="writer" value="<%=news.getWriter()%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="title" value="<%=news.getTitle()%>"></td>
			</tr>
			<tr>
				<td><textarea name="content" ><%=news.getContent()%></textarea></td>
			</tr>
			<tr>
				<td align="center">
					<button type="button">수정</button>
					<button type="button">삭제</button>
					<button type="button">목록</button>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<input type="text" name="msg" placeholder="댓글 입력">
						<input type="text" name="author" placeholder="작성자 입력">
						<button type="button">등록</button>
						<button type="button">비동기 등록</button>
					</div>
				</td>
			</tr>
			<!-- 댓글 리스트 영역 -->
			<tr>
				<td id="listBox">
					
				</td>
			</tr>
		</table>
	</form>
</body>
</html>