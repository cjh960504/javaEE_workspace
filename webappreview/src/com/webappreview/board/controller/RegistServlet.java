package com.webappreview.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webappreview.board.model.Notice;
import com.webappreview.board.model.NoticeDAO;

public class RegistServlet extends HttpServlet{
	NoticeDAO dao = new NoticeDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		HttpSession session = request.getSession();
		
		int result = dao.insert(notice);
		if(result==0) {
			session.setAttribute("msg", "등록 실패! 관리자에게 문의하십시오.");
			response.sendRedirect("/error/message.jsp");
		}else {
			response.sendRedirect("/board/list");
		}
	}
}
