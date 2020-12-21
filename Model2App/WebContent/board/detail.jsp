<%@page import="com.model2.domain.Board"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//정말로 포워딩이 요청을 유지했는지테스트해보자!
	Board board= (Board)request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>글 상세보기</title>
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

input[type=button] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

img{
width:100%
}
.reply-box{
	
}
.reply-list{
	overflow:hidden;
}

.reply-list p{
	float:left
}
</style>
<script src="https://cdn.ckeditor.com/4.15.1/standard/ckeditor.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	CKEDITOR.replace( 'content' );
	var bt_edit = $("input[type='button']")[0];
	var bt_del = $("input[type='button']")[1];
	$(bt_edit).click(function(){
		edit();
	});
	$(bt_del).click(function(){
		del();
	});
	getCommentList();//댓글 목록 비동기로 가져오기!!
});
function edit() {
	if(confirm("수정하시겠어요?")){
		$("form").attr({
			action:"/board/edit", 
			method:"post"
		});
		$("form").submit();
	}
}
function del() {
	/* $("form").attr({
		action:"/board/delete",//실존하지 파일이 아닌, 가상의 매핑주소 
		method:"post"
	});
	$("form").submit(); */
	if(confirm("정말 삭제하시겠습니까?")){
		location.href="/board/delete?notice_id=<%=board.getBoard_id()%>";
	}
}

function getCommentList() {
	$.ajax({
		url:"/comment/list.do",
		type:"get",
		data:{
			board_id:<%=board.getBoard_id()%>
		},
		success:function(result){
			//result에는 서버에서 전송한 json배열이 들어있다. 이 배열을 이용하여 아래의 태그를 완성
			//서버에서 전송되어 온 데이터는 json객체가 아닌 문자열일 뿐이다.
			//json = {name:"dddd"}
			//var json = JSON.parse(result);
			$("#list-area").html("");
			var tag="";
			//var commentList = json.list;
			for(var i=0;i<result.list.length;i++){
				var json = result.list[i];
				tag +="<div class=\"reply-list\">"; 
				tag += "<p style=\"width:75%\">"+json.msg+"</p>";
		    	tag += "<p style=\"width:15%\">"+json.author+"</p>";
		    	tag += "<p style=\"width:10%\">"+json.cdate.substring(0,10)+"</p>";
			    tag +="</div>";
			}
		    
		    $("#list-area").html(tag); //innerHTML과 동일
		}
	});
}

//현재 페이지가 새로고침(reloading)되지 않게, 비동기방식으로 등록요청을 시도하자!!
//js로 ajax를 사용하면 처리가 복잡하므로, jquery-ajax로 처리해보자
function registComment() {
	$.ajax({
		url:"/comment/regist.do",
		type:"get",
		data:{
			msg:$("input[name='msg']").val(),
			author:$("input[name='author']").val(),
			board_id:<%=board.getBoard_id()%>
		},
		//피드백은 success
		//즉, 에러없이 결과값이 반환되면
		//success 우측에 명시된 익명함수가 동작하게 된다..
		success:function(result){
			if(result==1){
				getCommentList();
			}else{
				alert("등록실패");
			}
		}
	});
}
</script>
</head>
<body>
<h3>상세보기</h3>
<div class="container">
  <form>
  	<input type="hidden" name="notice_id" value="<%=board.getBoard_id() %>">
    <input type="text" name="title" value="<%=board.getTitle()%>">
    <input type="text" name="writer" value="<%=board.getWriter()%>">
    <textarea name="content" style="height:200px"><%=board.getContent() %></textarea>
    <input type="button" value="수정">
    <input type="button" value="삭제">
    <input type="button" value="목록보기" onClick="location.href='/board/list.do'">
    <div class="reply-box">
    	<input type="text" name="msg" placeholder="댓글입력"  style="width:75%">
    	<input type="text" name="author" placeholder="작성자입력"  style="width:10%">
		<button type="button" onclick="registComment()">댓글등록</button>
    </div>
    <div id="list-area">
    
    </div>
    
  </form>
</div>
</body>
</html>