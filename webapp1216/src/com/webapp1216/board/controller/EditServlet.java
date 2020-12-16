package com.webapp1216.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webapp1216.board.model.Notice;
import com.webapp1216.board.model.NoticeDAO;
//수정요청을 처리하는 컨트롤러
public class EditServlet extends HttpServlet{
	NoticeDAO dao = new NoticeDAO();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String notice_id = request.getParameter("notice_id");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setNotice_id(Integer.parseInt(notice_id));
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
//		int result = dao.update(notice);
		int result = 0;
		HttpSession session = request.getSession();
		if(result==0) {
			session.setAttribute("msg", "수정 시 시스템에러가 발생했습니다.(에러코드 2202)");
			response.sendRedirect("/error/message.jsp");
		}else {
			response.sendRedirect("/board/detail?notice_id="+notice_id);
		}
	}
}
