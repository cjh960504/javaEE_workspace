package sports.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.Controller;

public class SportsController implements Controller{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sports = request.getParameter("sports");
		HttpSession session = request.getSession();
		if(sports.equals("soccer")) {
			session.setAttribute("msg", "�౸�Դϴ�.");
		}else if(sports.equals("baseball")){
			session.setAttribute("msg", "�߱��Դϴ�.");
		}else if(sports.equals("basketball")){
			session.setAttribute("msg", "���Դϴ�.");
		}else if(sports.equals("volleyball")){
			session.setAttribute("msg", "�豸�Դϴ�.");
		}
				
	}
	
	@Override
	public String getViewPage() {
		return "/sports/sports_result.jsp";
	}
}
