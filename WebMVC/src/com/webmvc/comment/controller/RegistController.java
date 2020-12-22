package com.webmvc.comment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webmvc.comment.model.CommentDAO;
import com.webmvc.domain.Comment;
import com.webmvc.notice.controller.Controller;

public class RegistController implements Controller{
	CommentDAO dao = new CommentDAO();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		//request에 정보를 가지고 있어야하므로 forward
		String notice_id = req.getParameter("notice_id");
		String msg = req.getParameter("msg");
		String author = req.getParameter("author");
		
		Comment comment = new Comment();
		comment.setNotice_id(Integer.parseInt(notice_id));
		comment.setMsg(msg);
		comment.setAuthor(author);
		int result = dao.insert(comment);
		req.setAttribute("notice_id", notice_id);
		
	}
	@Override
	public String getViewPage() {
		return "/view/notice/detail";
	}
	
	@Override
	public boolean isForward() {
		return true;
	}
	
}
