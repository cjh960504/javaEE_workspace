/*
 * 코멘트 게시판의 리스트 요청을 처리하는 컨트롤러,
 * 이 컨트롤러는 서블릿은 아니다, 단지 서블릿으로 전달받은 요청, 응답 객체를
 * 넘겨받았으므로, 이 객체들을 이용하여 처리한다.
*/
package com.model2.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.board.model.BoardDAO;
import com.model2.controller.Controller;

public class ListController implements Controller{
	BoardDAO dao = new BoardDAO();
	
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list = dao.selectAll();
		request.setAttribute("boardList", list);
	}

	@Override
	public String getResultView() {
		return "/view/board/list";
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
