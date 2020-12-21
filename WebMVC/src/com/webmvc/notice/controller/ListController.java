package com.webmvc.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webmvc.notice.model.NoticeDAO;

public class ListController implements Controller{
	NoticeDAO dao = new NoticeDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List list = dao.selectAll();
		request.setAttribute("noticeList", list);
	}
	@Override
	public String getViewPage() {
		return "/view/notice/list";
	}
	@Override
	public boolean isForward() {
		return true;
	}
}
