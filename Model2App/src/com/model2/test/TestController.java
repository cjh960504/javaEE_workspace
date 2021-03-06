/*
 * 이 클래스는 하위컨트롤러로서 역할을 수행해야 하므로,
 * 반드시 Controller 인터페이스를 구현해야한다!
*/
package com.model2.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.controller.Controller;

public class TestController implements Controller{
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계 : 
		String msg = "테스트입니다.";
		
		//4단계:결과 저장
		HttpSession session = request.getSession();
		session.setAttribute("result", msg);
	}
	
	@Override
	public String getResultView() {
		//키값을 가지고만 있으면 된다!!!!! 
		//실제 디자인 파일명이 바뀌면 json파일내의 파일이름(value)만 변경해주면 됨!
		return "/view/test/result";//외부데이터환경!!!!
		
	}
}
