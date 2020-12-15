package com.greeting;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//�����̶�? �ڹ� Ŭ���� �� ���� ������������ �ؼ� �� ����Ǿ��� �� �ִ� Ŭ����
/*�¾�� ����? ������ ��û�� ���� ��, �� �����̳ʿ� ���� 
�ν��Ͻ��� �޸𸮿� �ö��..
�ν��Ͻ��� ������ ��, �������ν� �������� ������ ����̿��� �Ѱܹ��� �� �ִµ�,
�̶� ���Ǵ� �޼��尡 init() �� ������ �¾ �� �ʱ�ȭ�޼��忡 ���� ���� ����
�� ���� ��..*/
public class HelloServlet extends HttpServlet{
	
	//�� �޼���� ������ �¾ ���Ŀ�, �ʱ�ȭ �۾��� ����
	//���� �� �޼����, Tomcat�� ���� �������̳ʿ� ���� ȣ��ȴ�.. �� ������ ���� �� �����ֱ�
	//�� ȣ���ڴ� �ٷ� ��Ĺ(�������̳�)�̴�!!
	@Override
	public void init(ServletConfig config) throws ServletException {
		String msg = config.getInitParameter("msg");
		System.out.println("init(config) ȣ�� �� �Ѱܹ��� �Ķ���� ������ "+msg);
		
		//jsp���尴ü �����ø����̼��� ������ ������ ���� ��ü.. = application
		ServletContext context = config.getServletContext();//jsp������ application���尴ü�� ����
		System.out.println(context.getRealPath("/"));
	}
	
	//������ �ϴ� ������ ��, �ʱ�ȭ������ġ��, Ŭ���̾�Ʈ�� ��û�� ó���� �غ� �Ȱ��̸�
	//Ŭ���̾�Ʈ�� ��û�� ó���ϴ� �޼��尡 �ٷ� service�޼����̴�.
	//���񽺸޼��尡 ��û�� ó���Ϸ���, Ŭ���̾�Ʈ�� ��û������ Ŭ���̾�Ʈ���� ���� ����������
	//�ʿ���ϱ⶧���� service()�޼����� �Ű������� request, response��ü�� ���޵Ǿ��
	//�Ѵ�..(����̰���)
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ŭ���̾�Ʈ�� ��û�� ���� �� �Ķ���͸� ������ ���� ����غ���!
		//jsp�� ���尴ü�� request
		String id = request.getParameter("id");
		
		//Ŭ���̾�Ʈ�� ����
		//�Ʒ��� �޼��嵵 import="text/html;charset=utf-8"
		//�̰� �����̳�? jsp�� java�� �� �𸣴� ����� ���� ������ �Ұ��� ��!!!
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("����� ������ �Ķ���ʹ� "+id);
	}
	
	//������ �Ҹ��� �� ȣ��Ǵ� �޼���
	//������ ������ �ڿ��� �ݳ��� �� (��Ʈ��, db...)
	public void destroy() {
		System.out.println("�� �׾�� �Ф�");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("Hello Servlet do !!");
	}
}
