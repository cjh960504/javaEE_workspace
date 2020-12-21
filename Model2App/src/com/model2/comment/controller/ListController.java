/*
 * 댓글 목록을 처리하는 컨트롤러
 * 댓글 목록은 클라이언트에게 페이지 재접속을 유도하면 안되며, 비동기적으로 데이터를 전송해야한다.
*/
package com.model2.comment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.comment.model.CommentDAO;
import com.model2.controller.Controller;

public class ListController implements Controller{
	CommentDAO dao = new CommentDAO();
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계 : 코멘트 목록가져오기
		String board_id = request.getParameter("board_id");
		List commentList = dao.selectAll(Integer.parseInt(board_id));
		
		//4단계 : 저장할 것이 있다면, 결과저장
		request.setAttribute("commentList", commentList);
	}
	@Override
	public String getResultView() {
		return "/view/comment/list";
	}
	
	@Override
	public boolean isForward() {
		return true;
	}
	
}
