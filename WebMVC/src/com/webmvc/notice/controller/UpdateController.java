package com.webmvc.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webmvc.notice.domain.Notice;
import com.webmvc.notice.model.NoticeDAO;

public class UpdateController implements Controller {
	NoticeDAO dao = new NoticeDAO();
	String viewPage = "/notice/detail.do";
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String title=req.getParameter("title");
		String writer =req.getParameter("writer");
		String content = req.getParameter("content");
		String notice_id = req.getParameter("notice_id");
		Notice notice=new Notice();
		notice.setNotice_id(Integer.parseInt(notice_id));
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		int result = dao.update(notice);
		if(result==0) {
			//에러페이지로 
		}else {
			req.setAttribute("notice_id", notice_id);
		}
	}

	@Override
	public String getViewPage() {
		return "/view/notice/update";
	}

	@Override
	public boolean isForward() {
		return true;
	}
}
