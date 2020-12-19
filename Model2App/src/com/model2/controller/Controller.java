/*
 * 모든 하위 컨트롤러가 반드시 구현해야할 메서드를 정의한다!!
*/
package com.model2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	//알맞는 비지니스 객체에 일 시키기
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	//어떤 뷰 페이지를 보여줘야 할지 결정
	//만일 하위 컨트롤러가 이 업무를 처리하지 않으면, DispatcherServlet에서 if문으로 처리해야한다.
	public String getResultView();
	
	//요청을 끊어야할지, 유지해야할 지를 결정하는 메서드
	public boolean isForward();
}
