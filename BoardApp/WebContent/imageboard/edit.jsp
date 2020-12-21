<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ include file="/inc/lib.jsp"%>
<%@page import="board.model.ImageBoard"%>
<%@page import="board.model.ImageBoardDAO"%>
<%@page import="common.notice.FileManager"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%!//멤버영역 == 클래스의 멤버변수
	String saveDir = "C:/workspace/javaee_workspace/BoardApp/WebContent/data";
	int maxSize = 1024 * 1024 * 3; //3MB%>
<%
	ImageBoardDAO dao = new ImageBoardDAO();

//실습했던 예제보다 기능이 더 추가됨, db에 넣어야 함..DAO이용
//업로드 컴포넌트에 대한 설정을 하기 위해 FileItemFactory객체를 이용한다..	

DiskFileItemFactory itemFactory = new DiskFileItemFactory();
itemFactory.setRepository(new File(saveDir));
itemFactory.setSizeThreshold(maxSize);
itemFactory.setDefaultCharset("utf-8");

ServletFileUpload upload = new ServletFileUpload(itemFactory);
//upload.setHeaderEncoding("utf-8");
//request.setCharacterEncoding("utf-8");
//업로드된 정보 분석!! 각각의 컴포넌트들을 FileItem단위로 쪼갠다!!
List<FileItem> items = upload.parseRequest(request);

ImageBoard board = new ImageBoard();//Empty
for (FileItem item : items) {
	if (item.isFormField()) {//textField라면, db에 넣기
		String fieldName = item.getFieldName();
		if (fieldName.equals("author")) {//필드명이 author라면
	board.setAuthor(item.getString()); //textField들의 값들을 getString
		} else if (fieldName.equals("title")) {//필드명이 title라면
	board.setTitle(item.getString());
		} else if (fieldName.equals("content")) {//필드명이 content라면
	board.setContent(item.getString());
		} else if (fieldName.equals("board_id")) {
	board.setBoard_id(Integer.parseInt(item.getString()));
		} else if (fieldName.equals("filename")) {
	board.setFilename(item.getString());
		}
	} else {//textField가 아니라면, 업로드 처리
		File prefile = new File("C:/workspace/javaee_workspace/BoardApp/WebContent/data/" + board.getFilename());
		if (prefile.delete()) {
	out.print("업로드한 파일명 : " + item.getName());
	// 사용자가 파일을 업로드 했다면...
	if (item.getName().length() > 0) { //파일을 교체한다면, 즉 업로드 하길 원한다면...
		String newName = System.currentTimeMillis() + "." + FileManager.getExtend(item.getName());
		String destFile = saveDir + "/" + newName;
		File file = new File(destFile);
		item.write(file);//물리적 저장시점!

		out.print("업로드 완료");
		board.setFilename(newName); //vo에 파일명 담기!

	}
		}
	}
}
//반복문을 지나온 board는 이미 채워진 상태일 것이다!

int result = dao.update(board);
if (result == 0) {
	out.print(getMsgBack("수정 실패"));
} else {
	out.print(getMsgURL("수정 성공", "/imageboard/list.jsp"));
}
%>