package com.model2.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.notice.domain.Notice;
import com.model2.notice.model.NoticeDAO;

public class DetailController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO(); 
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계:알맞는 로직 객체에 일 시킨다.
		String notice_id = request.getParameter("notice_id");
		Notice notice=noticeDAO.select(Integer.parseInt(notice_id));
		System.out.println(notice);
		//4단계 : 저장할 것이 있다면 저장하기(저장을 했다면 request객체가 jsp까지
		//살아서 가야하므로 forward방식을 채택해야한다.)
		request.setAttribute("notice", notice);
		
	}
	
	@Override
	public String getResultView() {
		return "/view/notice/detail";
	}
	
	@Override
	public boolean isForward() {
		return true;
	}
}
