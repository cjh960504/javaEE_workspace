/*
	������ ��� Ŭ���̾�Ʈ�� ��û�� �ް�, ������ �����ϴ� ��Ʈ�ѷ� ����
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
	FileReader fr; //��Ʈ�ѷ�  ���� ���������� �б����� ��Ʈ��
	JSONObject controllerMap;
	JSONObject viewMap;
	
	public void init(ServletConfig config) throws ServletException {
		String contextConfigLocation = config.getInitParameter("contextConfigLocation"); 
		String realPath = config.getServletContext().getRealPath(contextConfigLocation);
		JSONParser jsonParser = new JSONParser();
		System.out.println(realPath);
		try {
			fr = new FileReader(realPath);
			//�Ľ�
			JSONObject json = (JSONObject)jsonParser.parse(fr);
			
			//�����Ϳ� ����
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
	
	//1�ܰ�) doXXX�� �޼��带 �����Ͽ� ��û�� ����!!
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//������Ʈ�ѷ��� �����ϸ� ������ ���ص��Ǵϱ�!

		//2�ܰ�) ��û�� �м��Ѵ�.
		//Ŭ���̾�Ʈ�� ��û �� ����� uri ��ü�� ��û�� ���а����� ���� �� �ִ�.
		String uri = request.getRequestURI();
		
		//if���� ����� ����ȭ�� �����͸� ��������(xml, json, properties)
		String controllerName= (String) controllerMap.get(uri);
		System.out.println("���� ���� ��û�� ó���� ��Ʈ�ѷ� Ŭ������ " + controllerName);
		
		//���� ��Ʈ�ѷ��� �̸��� �˾�����, �ν��Ͻ��� �����, execute(), getResultViewȣ��
		Class controllerClass=null;
		Controller controller=null;
		try {
			controllerClass = Class.forName(controllerName);//���ڿ��� ������ Ŭ������ ���� ���� Ŭ���� ��ȯ
			controller = (Controller)controllerClass.newInstance();//���� ��Ʈ�ѷ��� �ν��Ͻ� ����
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		controller.excute(request, response);
		
		//���� ��Ʈ�ѷ��κ��� �Ѱܹ��� �信 ���� ������ �̿��Ͽ� Ŭ���̾�Ʈ���� �˸´� �並 ��������
		String resultKey = controller.getResultView();
		System.out.println("���� ��Ʈ�ѷ����� �Ѱܹ��� Ű���� "+ resultKey);
		String resultValue = (String)viewMap.get(resultKey);
		//���� �� sendRedirect�� ó���ؾ��ϴ� ��찡 �ְ�,
		//���� �ٸ� �������� �������� �õ�
		if(controller.isForward()) {//forward�� �ʿ��� ��
			//���δ� forwarding ó���ؾ� �� ��찡 �ִ�....
			//���ο� �����͸� �����ϰ� ���� ��
			RequestDispatcher dis = request.getRequestDispatcher(resultValue);
			dis.forward(request, response);//�������(������ ���� �ʰ�) �������� �� �ٸ� �ڿ����� ��û�� ����!!
		}else {//redirect�� �ʿ��� ��
			response.sendRedirect(resultValue);//������ �ͽ��ϰ�����..
		}

		
	}
}
