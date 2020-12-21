package com.model2.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.board.model.BoardDAO;
import com.model2.controller.Controller;
import com.model2.domain.Board;

public class DetailController implements Controller{
	BoardDAO dao = new BoardDAO();
	
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계 : 알맞는 로직 객체에 일 시키기
		String board_id = request.getParameter("board_id");
		Board board = dao.select(Integer.parseInt(board_id));
		
		request.setAttribute("board", board);
	}
	@Override
	public String getResultView() {
		return "/view/board/detail";
	}
	@Override
	public boolean isForward() {
		return true;
	}
}
