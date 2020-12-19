/*
	웹상의 모든 클라이언트의 요청을 받고, 응답을 전담하는 컨트롤러 정의
*/
package com.model2.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DispatcherServlet extends HttpServlet{
	FileReader fr; //컨트롤러  매핑 설정파일을 읽기위한 스트림
	JSONObject controllerMap;
	JSONObject viewMap;
	
	public void init(ServletConfig config) throws ServletException {
		String contextConfigLocation = config.getInitParameter("contextConfigLocation"); 
		String realPath = config.getServletContext().getRealPath(contextConfigLocation);
		JSONParser jsonParser = new JSONParser();
		System.out.println(realPath);
		try {
			fr = new FileReader(realPath);
			//파싱
			JSONObject json = (JSONObject)jsonParser.parse(fr);
			
			//데이터에 접근
			controllerMap = (JSONObject)json.get("controller");
			viewMap = (JSONObject)json.get("view");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy() {
		if(fr!=null) {
			try {
				fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//1단계) doXXX형 메서드를 정의하여 요청을 받자!!
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//상위컨트롤러가 설정하면 하위는 안해도되니까!

		//2단계) 요청을 분석한다.
		//클라이언트가 요청 시 사용한 uri 자체가 요청의 구분값으로 사용될 수 있다.
		String uri = request.getRequestURI();
		
		//if문을 대신할 구조화된 데이터를 선택하자(xml, json, properties)
		String controllerName= (String) controllerMap.get(uri);
		System.out.println("지금 들어온 요청을 처리할 컨트롤러 클래스는 " + controllerName);
		
		//하위 컨트롤러의 이름을 알았으니, 인스턴스를 만들고, execute(), getResultView호출
		Class controllerClass=null;
		Controller controller=null;
		try {
			controllerClass = Class.forName(controllerName);//문자열로 지정한 클래스에 대한 실제 클래스 반환
			controller = (Controller)controllerClass.newInstance();//하위 컨트롤러의 인스턴스 생성
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		controller.excute(request, response);
		
		//하위 컨트롤러로부터 넘겨받은 뷰에 대한 정보를 이용하여 클라이언트에게 알맞는 뷰를 보여주자
		String resultKey = controller.getResultView();
		System.out.println("하위 컨트롤러부터 넘겨받은 키값은 "+ resultKey);
		String resultValue = (String)viewMap.get(resultKey);
		//응답 시 sendRedirect로 처리해야하는 경우가 있고,
		//전혀 다른 페이지로 재접속을 시도
		if(controller.isForward()) {//forward가 필요할 때
			//때로는 forwarding 처리해야 할 경우가 있다....
			//새로운 데이터를 유지하고 싶을 때
			RequestDispatcher dis = request.getRequestDispatcher(resultValue);
			dis.forward(request, response);//응답없이(연결을 끊지 않고) 서버상의 또 다른 자원으로 요청을 전달!!
		}else {//redirect가 필요할 때
			response.sendRedirect(resultValue);//세션을 맹신하고있음..
		}

		
	}
}
