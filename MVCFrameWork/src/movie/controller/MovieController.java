package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.Controller;

import movie.model.MovieInfo;

public class MovieController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3�ܰ� : �˸´� ���� ��ü���� �� ��Ų��.
		
		String movie = request.getParameter("movie");
		System.out.println(movie);
		if(movie.equals("none")) {
			response.sendRedirect("/movie/movie_form.jsp");
		}else {
			MovieInfo movieInfo = new MovieInfo();
			String msg = movieInfo.getInfo(movie);
			
			//4�ܰ� : Ŭ���̾�Ʈ���� ������ ����� �����س��´�. 
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			
		}
	}
	public String getViewPage() {
		return "/movie/movie_result.jsp";
	}
}
