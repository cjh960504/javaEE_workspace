/*
 * ��� ����� ó���ϴ� ��Ʈ�ѷ�
 * ��� ����� Ŭ���̾�Ʈ���� ������ �������� �����ϸ� �ȵǸ�, �񵿱������� �����͸� �����ؾ��Ѵ�.
*/
package com.model2.comment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.comment.model.CommentDAO;
import com.model2.controller.Controller;

public class ListController implements Controller{
	CommentDAO dao = new CommentDAO();
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3�ܰ� : �ڸ�Ʈ ��ϰ�������
		String board_id = request.getParameter("board_id");
		List commentList = dao.selectAll(Integer.parseInt(board_id));
		
		//4�ܰ� : ������ ���� �ִٸ�, �������
		request.setAttribute("commentList", commentList);
	}
	@Override
	public String getResultView() {
		return "/view/comment/list";
	}
	
	@Override
	public boolean isForward() {
		return true;
	}
	
}
