/*
	MVC패턴으로 개발하다보면, 늘어나는 컨트롤러에 대해 일일이 매핑과정을 진행해야한다.
	이때 너무 많은 매핑은 관리하기가 까다롭다. 따라서 유지보수성이 떨어진다..
	현실과 유사하게 어플리케이션에서도 대형업무 처리 시 클라이언트이 요청을 곧바로 해당
	컨트롤러에게 처리하게 하지 않고, 중간에 메인 컨트롤러를 두고, 모든 요청을 이 메인
	컨트롤러에서 처리하여 적절한 하위 컨트롤러에게 전담시키는 방식을 이용한다...
	
	이 컨트롤러는 웹어플레이케이션의 모든~~~everything 요청을 1차적으로 받는다.
	
*/
package com.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	FileInputStream fis;
	Properties props;
	
	//init은 서블릿의 생명주기에서, 최초 접속자에 의해 톰캣이 인스턴스를 생성하며, 이와 동시에 초기화
	//메서드로써 호출된다...
	@Override
	public void init(ServletConfig config) throws ServletException {
		//getRealPath는 jsp의 application에 대한 정보를 갖는 application 내장객체에서 지원함!
		// 서블릿에서는 ServletContext!
		ServletContext context = config.getServletContext();
		//web.xml에서 init시 파라미터로 넘겨줄 데이터를 설정할 수 있다.
		//<init-param> [설정은 외부파일에서!]
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		String savePath = context.getRealPath(contextConfigLocation);
		System.out.println(savePath);
		try {
			fis = new FileInputStream(savePath);
			props = new Properties();
			props.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	//fis가 init시 생성되고 재생성되는 경우가 없으므로 서블릿의 프로세스가 종료될 때 fis를 종료시켜야함
	//(즉, 한번만 만들고 한번만 죽임)
	@Override
	public void destroy() {//서블릿이 소멸할때 호출되는 메서드
		if(fis!=null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	// get or post 상관없이, 모든 요청을 이 메서드로 처리하자!!!
	public void doRequest(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//1단계 : 요청을 받는다!
		System.out.println("제가 요청을 받았어용");
		//클라이언트가 영화를 원하면? --> 영화담당 컨트롤러에게 전가
		
		//클라이언트가 혈액형을 원하면? --> 혈액형담당 컨트롤러에게 전가
		
		//글쓰기?, 삭제? 혈액형?...
		//클라이언트가 원하는 게 무엇인지 알아맞춰야댐...
		//해답은 클라이언트 요청 자체에 있다.. 즉 요청 시 사용된 URI가 요청구분이다!!!
		String uri = request.getRequestURI();
		System.out.println("지금 들어온 요청은 " + uri);
		
		Controller controller = null;
		
		String className = null;

		//if문 대신, 프로터티스 객체를 이용하여 key값으로 메모리에 올려질 컨트롤러의
		//이름을 value를 반환받자!
		className = props.getProperty(uri); //uri값을 이용해 프로퍼티스의 저장된 uri에 대한 value값을 가져온다!
		
		try {
			Class controllerClass = Class.forName(className);//클래스 로드
			controller = (Controller)controllerClass.newInstance();
			//2단계:요청을 분석하여, 알맞는 컨트롤러에게 요청을 전달
			controller.execute(request, response); //다형적으로 실행됨....(다형성 : 유연한코딩..)
			//5단계 : 클라이언트에게 알맞는 결과를 보여준다!!!
			//클라이언트로 하여금 지정한 url로 재접속을 유도하자
			//하위컨트롤로에게 물어본 값!
			response.sendRedirect(controller.getViewPage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		
	}
}
