/*
������ jsp�� ����ϰ� �־��� ��Ʈ�ѷ����� ������ ���� Ŭ������ �и���Ű��!!
�׷��� jsp�� ������ �������� �Ǳ� ������ �������� �� ��ü���� ����!
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
	//dispatcher�� ���� ������Ʈ�ѷ��̹Ƿ� �Ű������� ���־��Ѵ�
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3�ܰ� : �˸´� ���� ��ü���� �� ��Ų��.
		
		//�Ķ���� �ޱ�
		String blood = request.getParameter("blood");
		if(blood!=null) {
			BloodAdviser bloodAdviser = new  BloodAdviser();
			String msg  = bloodAdviser.getAdvice(blood);
			
			//����� ���� ����� �������� View�� ����ϹǷ�, �� �������� ó���ϸ� �ȵȴ�!!
			//��� jsp���� �޼����� �����ַ���, ������ �޸𸮿� �ӽ������� �����س��� �ʿ䰡 �ִ�!
			//����μ��� ���ǿ� ����...
			//4�ܰ� : Ŭ���̾�Ʈ���� ������ ����� �����س��´�. 
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			//Ŭ���̾�Ʈ�� �Ͽ��� ������ url�� �������� ��������!! �������!
		}else {
			response.sendRedirect("/blood/blood_form.jsp");
		}
	}

	public String getViewPage() {
		return "/blood/blood_result.jsp";
	}
}
