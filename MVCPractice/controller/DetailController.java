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
		//3�ܰ�:�˸´� ���� ��ü�� �� ��Ų��.
		String notice_id = request.getParameter("notice_id");
		Notice notice=noticeDAO.select(Integer.parseInt(notice_id));
		System.out.println(notice);
		//4�ܰ� : ������ ���� �ִٸ� �����ϱ�(������ �ߴٸ� request��ü�� jsp����
		//��Ƽ� �����ϹǷ� forward����� ä���ؾ��Ѵ�.)
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