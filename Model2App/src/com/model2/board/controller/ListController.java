/*
 * �ڸ�Ʈ �Խ����� ����Ʈ ��û�� ó���ϴ� ��Ʈ�ѷ�,
 * �� ��Ʈ�ѷ��� ������ �ƴϴ�, ���� �������� ���޹��� ��û, ���� ��ü��
 * �Ѱܹ޾����Ƿ�, �� ��ü���� �̿��Ͽ� ó���Ѵ�.
*/
package com.model2.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.board.model.BoardDAO;
import com.model2.controller.Controller;

public class ListController implements Controller{
	BoardDAO dao = new BoardDAO();
	
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list = dao.selectAll();
		request.setAttribute("boardList", list);
	}

	@Override
	public String getResultView() {
		return "/view/board/list";
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
