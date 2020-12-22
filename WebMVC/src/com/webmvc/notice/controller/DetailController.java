package com.webmvc.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webmvc.domain.Notice;
import com.webmvc.notice.model.NoticeDAO;

public class DetailController implements Controller{
	NoticeDAO dao = new NoticeDAO();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String notice_id = req.getParameter("notice_id");
		Notice notice = dao.select(Integer.parseInt(notice_id));
		req.setAttribute("notice", notice);
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
