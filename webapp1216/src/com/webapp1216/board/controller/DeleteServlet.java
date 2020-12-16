package com.webapp1216.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webapp1216.board.model.NoticeDAO;

public class DeleteServlet extends HttpServlet{
	NoticeDAO dao = new NoticeDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notice_id = request.getParameter("notice_id");
		int result = dao.delete(Integer.parseInt(notice_id));
		
		//��Ʈ�ѷ��� Ŭ���̾�Ʈ�� ���Ե� �޽����� ó���ؾ��Ѵ�? �ƴϴ�;;
		//alert()�� ������!
		//response.setContentType("text/html;charset=utf-8");
		//PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if(result == 0) {
			/*out.print("<script>");
			out.print("alert('���� ����');");
			out.print("history.back();");
			out.print("</script>");*/
			//������������ �����ش�. �ᱹ ������ ���� �ȳ��������� ������ �ΰ�, �� �������� �����ش�.
			session.setAttribute("msg", "���� �������� �ʾҽ��ϴ�. �����ڿ��� �����ּ���.");
			response.sendRedirect("/error/message.jsp");
		}else {
			/*out.print("<script>");
			out.print("alert('���� ����');");
			out.print("location.href='/board/list';");
			out.print("</script>");*/
			response.sendRedirect("/board/list");
		}
	}
}
