package com.model2.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.board.model.BoardDAO;
import com.model2.controller.Controller;
import com.model2.domain.Board;

public class RegistController implements Controller{
	BoardDAO dao = new BoardDAO();
	String viewPage;
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		int result = dao.insert(board);
		if(result==0) {
			viewPage="/view/board/error";
		}else {
			viewPage="/view/board/regist";
		}
	}

	@Override
	public String getResultView() {
		return viewPage;
	}

	@Override
	public boolean isForward() {
		return false;
	}
	
}
