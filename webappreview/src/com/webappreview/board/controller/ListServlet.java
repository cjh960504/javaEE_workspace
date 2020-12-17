package com.webappreview.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webappreview.board.model.Notice;
import com.webappreview.board.model.NoticeDAO;

public class ListServlet extends HttpServlet{
	NoticeDAO dao = new NoticeDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Notice> list = dao.selectAll();
		HttpSession session = request.getSession();
		session.setAttribute("noticeList", list);
		response.sendRedirect("/board/list.jsp");
	}
}
