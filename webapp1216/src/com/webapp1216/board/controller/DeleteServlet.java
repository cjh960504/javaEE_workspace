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
		
		//컨트롤러가 클라이언트가 보게될 메시지를 처리해야한다? 아니다;;
		//alert()은 디자인!
		//response.setContentType("text/html;charset=utf-8");
		//PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if(result == 0) {
			/*out.print("<script>");
			out.print("alert('삭제 실패');");
			out.print("history.back();");
			out.print("</script>");*/
			//실패페이지를 보여준다. 결국 에러에 대한 안내페이지를 별도로 두고, 그 페이지를 보여준다.
			session.setAttribute("msg", "글이 삭제되지 않았습니다. 관리자에게 문의주세요.");
			response.sendRedirect("/error/message.jsp");
		}else {
			/*out.print("<script>");
			out.print("alert('삭제 성공');");
			out.print("location.href='/board/list';");
			out.print("</script>");*/
			response.sendRedirect("/board/list");
		}
	}
}
