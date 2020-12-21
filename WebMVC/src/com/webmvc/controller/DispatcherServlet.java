package com.webmvc.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.webmvc.notice.controller.Controller;

public class DispatcherServlet extends HttpServlet{
	FileReader reader;
	JSONParser json = new JSONParser();
	JSONObject obj;
	JSONObject controller;
	JSONObject view;
	@Override
	public void init(ServletConfig config) throws ServletException {
		String configContextLocation = config.getInitParameter("configContextLocation");
		ServletContext context = config.getServletContext();
		String realPath = context.getRealPath(configContextLocation);
		try {
			reader = new FileReader(realPath);
			obj = (JSONObject) json.parse(reader);
			controller = (JSONObject) obj.get("controller");
			view = (JSONObject) obj.get("view");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void destroy() {
		if(reader!=null) {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String uriJson = (String) controller.get(uri);
		Class nextCon;
		Controller con=null;
		try {
			nextCon = Class.forName(uriJson);
			con = (Controller) nextCon.newInstance();
			con.execute(request, response); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		String viewPage = con.getViewPage();
		String nextView = (String) view.get(viewPage);
		if(con.isForward()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextView);
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(nextView);
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}
	
}
