package com.tooniverse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calP.ca")
public class calP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int toonNum = Integer.parseInt(request.getParameter("toonNum"));
		
		int[] goToonNum = new ToonMgr().calP(toonNum);
		
		response.getWriter().print(goToonNum[0]+" "+goToonNum[1]);
		
	}

}
