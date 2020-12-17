/*
기존의 jsp가 담당하고 있었던 컨트롤러서의 업무를 현재 클래스로 분리시키자!!
그래야 jsp는 순수한 디자인이 되기 떄문에 유지보수 시 교체까지 가능!
*/
package blood.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.Controller;

import blood.model.BloodAdviser;

public class BloodController implements Controller{
	//dispatcher는 단지 전달컨트롤러이므로 매개변수는 냅둬야한다
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계 : 알맞는 로직 객체에게 일 시킨다.
		
		//파라미터 받기
		String blood = request.getParameter("blood");
		if(blood!=null) {
			BloodAdviser bloodAdviser = new  BloodAdviser();
			String msg  = bloodAdviser.getAdvice(blood);
			
			//결과에 대한 출력은 디자인인 View가 담당하므로, 이 서블릿에서 처리하면 안된다!!
			//결과 jsp에서 메세지를 보여주려면, 서버의 메모리에 임시적으로 저장해놓을 필요가 있다!
			//현재로서는 세션에 담자...
			//4단계 : 클라이언트에게 전달할 결과를 저장해놓는다. 
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			//클라이언트로 하여금 지정한 url로 재접속을 유도하자!! 명령하자!
		}else {
			response.sendRedirect("/blood/blood_form.jsp");
		}
	}

	public String getViewPage() {
		return "/blood/blood_result.jsp";
	}
}
