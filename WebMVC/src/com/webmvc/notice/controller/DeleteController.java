package com.webmvc.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webmvc.notice.model.NoticeDAO;

import com.webmvc.notice.model.NoticeDAO;

public class DeleteController implements Controller{
	NoticeDAO dao = new NoticeDAO();
	String viewPage = null;
	 @Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		 String notice_id = req.getParameter("notice_id");
		 int result = dao.delete(Integer.parseInt(notice_id));
		 if(result==0) {
			 //에러페이지
		 }else {
			 viewPage = "/view/notice/delete";
		 }
	}
	 @Override
	public String getViewPage() {
		return viewPage;
	}
	 @Override
	public boolean isForward() {
		return false;
	}
}
