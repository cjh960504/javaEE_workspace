<%@page import="board.model.MyBatisBoardDAO"%>
<%@page import="common.file.FileManager"%>
<%@page import="board.model.Board"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="board.model.BoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/inc/lib.jsp" %>
<%request.setCharacterEncoding("utf-8"); %>
<%
	//BoardDAO dao = new BoardDAO();
	MyBatisBoardDAO dao = new MyBatisBoardDAO();
	
	//파일업로드인 경우, 파라미터 처리는 파일업로드 컴포넌트를 통해서 한다!!
	//Why? multipart/form-data에 의한 파라미터 파싱한 주체가 업로드 컴포넌트이기 떄문..
	//multipart/form-data인코딩 경우 파라미터로 받을 수 없다!
	
	DiskFileItemFactory factory = new DiskFileItemFactory();
	//파일의 임시디렉터리 경로
	String saveDir = application.getRealPath("/data");
	factory.setRepository(new File(saveDir));
	//파일업로드 사이즈
	factory.setSizeThreshold(2*1024*1024);
	//인코딩
	factory.setDefaultCharset("utf-8");
	
	ServletFileUpload upload = new ServletFileUpload(factory);
	
	List<FileItem> items = upload.parseRequest(request);//Multipart/form-data 요청분석
	Board board = new Board();
	for(int i=0;i<items.size();i++){
		FileItem item = items.get(i);
		if(item.isFormField()){
			if(item.getFieldName().equals("board_id")){
				board.setBoard_id(Integer.parseInt(item.getString()));
			}else if(item.getFieldName().equals("filename")){
				board.setFilename(item.getString());
			}else if(item.getFieldName().equals("title")){
				board.setTitle(item.getString());
			}else if(item.getFieldName().equals("writer")){
				board.setWriter(item.getString());
			}else if(item.getFieldName().equals("content")){
				board.setContent(item.getString());
			}
		}else{//파일 수정을 위해, 업로드된 파일이 발견된다면..
			if(item.getName().length()>0){//파일명이 있다면... 즉, 업로드한다면
				
				System.out.println(saveDir+"/"+board.getFilename());
				//기존파일을 삭제
				if(FileManager.deleteFile(saveDir+"/"+board.getFilename())){
					//새로운 파일 처리
					String newName = System.currentTimeMillis()+"."+FileManager.getExtend(item.getName());
					item.write(new File(saveDir+"/"+newName));
					//새로만들어진 이름을 VO에 넣어줘야 db에도 업데이트!
					board.setFilename(newName);					
				}
			}
		}
	}
	int result = dao.update(board);
	if(result==0){
		out.print(getMsgBack("수정실패"));
	}else{
		out.print(getMsgURL("수정성공", "detail.jsp?board_id="+board.getBoard_id()));
	}
%>