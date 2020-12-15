/*
jsp만으로 개발햇던 방식에서 regist.jsp가 담당하는 업무를 서블릿으로 구현해본다!
*/
package board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import board.model.Board;
import board.model.BoardDAO;
import common.file.FileManager;

public class RegistServlet extends HttpServlet{
	BoardDAO boardDao = new BoardDAO();//doPost안에 생성하게 되면 쓸데없이 계속 생성한다!!
	
	//클라이언트가 get으로 요청: doGet(), post요청: doPost()
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//출력콘텐츠 인코딩
		response.setContentType("text/html;charset=utf-8");
		//출력스트림 뽑아놓기
		PrintWriter out = response.getWriter();
		//클라이언트의 요청에 multipart/form-data방식이 포함될 경우
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//임시파일 경로
		ServletContext context = request.getServletContext();//어플리케이션과 관련한 정보
		//ServletContext는 jsp에서는 application내장객체로 접근해야한다....
		String saveDir = context.getRealPath("/data");
		factory.setRepository(new File(saveDir));
		
		//크기제한
		factory.setSizeThreshold(2*1024*1024);
		
		//인코딩방식
		factory.setDefaultCharset("utf-8");
		ServletFileUpload upload=new ServletFileUpload(factory);
		
		//업로드된 정보 분석하자(파싱)
		try {
			//업로드된 컴포넌트들을 item리스트로 받아놓자!
			List<FileItem> items = upload.parseRequest(request);
			Board board = new Board();
			boolean flag = false;//파일업로드 성공여부
			for(FileItem item:items) {
				if(item.isFormField()) {//일반텍스트 컴포넌트라면(작성자, 제목, 내용...)
					//vo에 앎맞게 담자!
					if(item.getFieldName().equals("title")) {
						board.setTitle(item.getString());
					}else if(item.getFieldName().equals("writer")) {
						board.setWriter(item.getString());
					}else if(item.getFieldName().equals("content")) {
						board.setContent(item.getString());
					}
				}else {//파일컴포넌트라면
					long time = System.currentTimeMillis();
					String newName = time+"."+FileManager.getExtend(item.getName());//파일명
					board.setFilename(newName);//새로 만들어진 이름을 VO에 담아야 insert문에 들어감
					//파일 물리적으로 저장
					item.write(new File(saveDir+"/"+newName));
					flag=true;
				}
			}
			//db에 insert
			if(flag) {
				int result = boardDao.insert(board);
				if(result==0) {
					out.print("<script>");
					out.print("alert('등록실패');");
					out.print("history.back();");
					out.print("</script>");
				}else {
					out.print("<script>");
					out.print("alert('등록성공');");
					out.print("location.href='list.jsp';");
					out.print("</script>");
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
