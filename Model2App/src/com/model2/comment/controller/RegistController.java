package com.model2.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.comment.model.CommentDAO;
import com.model2.controller.Controller;
import com.model2.domain.Comment;

public class RegistController implements Controller{
	CommentDAO dao = new CommentDAO();
	
	//댓글의 등록은 비동기요청으로 들어오기떄문에 응답정보는 페이지를 보여주는게 아니라
	//데이터를 전송해야한다!!!!
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = request.getParameter("msg");
		String author = request.getParameter("author");
		String board_id = request.getParameter("board_id");
		Comment comment = new Comment();
		comment.setMsg(msg);
		comment.setAuthor(author);
		comment.setBoard_id(Integer.parseInt(board_id));
		int result = dao.insert(comment);
		
		//4단계 생략 : DML수행결과를 저장하겠다.
		request.setAttribute("result", result);//boxing...
		
	}
	@Override
	public String getResultView() {
		return "/view/comment/regist";//등록 결과를 클라이언트에게 보여줄 jsp 키값
	}
	@Override
	public boolean isForward() {
		return true;
	}
	
}
