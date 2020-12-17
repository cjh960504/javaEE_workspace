/*
	MVC�������� �����ϴٺ���, �þ�� ��Ʈ�ѷ��� ���� ������ ���ΰ����� �����ؾ��Ѵ�.
	�̶� �ʹ� ���� ������ �����ϱⰡ ��ٷӴ�. ���� ������������ ��������..
	���ǰ� �����ϰ� ���ø����̼ǿ����� �������� ó�� �� Ŭ���̾�Ʈ�� ��û�� ��ٷ� �ش�
	��Ʈ�ѷ����� ó���ϰ� ���� �ʰ�, �߰��� ���� ��Ʈ�ѷ��� �ΰ�, ��� ��û�� �� ����
	��Ʈ�ѷ����� ó���Ͽ� ������ ���� ��Ʈ�ѷ����� �����Ű�� ����� �̿��Ѵ�...
	
	�� ��Ʈ�ѷ��� �����÷������̼��� ���~~~everything ��û�� 1�������� �޴´�.
	
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
	
	//init�� ������ �����ֱ⿡��, ���� �����ڿ� ���� ��Ĺ�� �ν��Ͻ��� �����ϸ�, �̿� ���ÿ� �ʱ�ȭ
	//�޼���ν� ȣ��ȴ�...
	@Override
	public void init(ServletConfig config) throws ServletException {
		//getRealPath�� jsp�� application�� ���� ������ ���� application ���尴ü���� ������!
		// ���������� ServletContext!
		ServletContext context = config.getServletContext();
		//web.xml���� init�� �Ķ���ͷ� �Ѱ��� �����͸� ������ �� �ִ�.
		//<init-param> [������ �ܺ����Ͽ���!]
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
	
	//fis�� init�� �����ǰ� ������Ǵ� ��찡 �����Ƿ� ������ ���μ����� ����� �� fis�� ������Ѿ���
	//(��, �ѹ��� ����� �ѹ��� ����)
	@Override
	public void destroy() {//������ �Ҹ��Ҷ� ȣ��Ǵ� �޼���
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

	// get or post �������, ��� ��û�� �� �޼���� ó������!!!
	public void doRequest(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//1�ܰ� : ��û�� �޴´�!
		System.out.println("���� ��û�� �޾Ҿ��");
		//Ŭ���̾�Ʈ�� ��ȭ�� ���ϸ�? --> ��ȭ��� ��Ʈ�ѷ����� ����
		
		//Ŭ���̾�Ʈ�� �������� ���ϸ�? --> ��������� ��Ʈ�ѷ����� ����
		
		//�۾���?, ����? ������?...
		//Ŭ���̾�Ʈ�� ���ϴ� �� �������� �˾Ƹ���ߴ�...
		//�ش��� Ŭ���̾�Ʈ ��û ��ü�� �ִ�.. �� ��û �� ���� URI�� ��û�����̴�!!!
		String uri = request.getRequestURI();
		System.out.println("���� ���� ��û�� " + uri);
		
		Controller controller = null;
		
		String className = null;

		//if�� ���, ������Ƽ�� ��ü�� �̿��Ͽ� key������ �޸𸮿� �÷��� ��Ʈ�ѷ���
		//�̸��� value�� ��ȯ����!
		className = props.getProperty(uri); //uri���� �̿��� ������Ƽ���� ����� uri�� ���� value���� �����´�!
		
		try {
			Class controllerClass = Class.forName(className);//Ŭ���� �ε�
			controller = (Controller)controllerClass.newInstance();
			//2�ܰ�:��û�� �м��Ͽ�, �˸´� ��Ʈ�ѷ����� ��û�� ����
			controller.execute(request, response); //���������� �����....(������ : �������ڵ�..)
			//5�ܰ� : Ŭ���̾�Ʈ���� �˸´� ����� �����ش�!!!
			//Ŭ���̾�Ʈ�� �Ͽ��� ������ url�� �������� ��������
			//������Ʈ�ѷο��� ��� ��!
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
