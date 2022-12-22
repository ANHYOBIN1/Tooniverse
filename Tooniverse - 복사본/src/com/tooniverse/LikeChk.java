package com.tooniverse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lchk.ra")
public class LikeChk extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		int toonNum = Integer.parseInt(request.getParameter("toonNum"));
		
		//System.out.println(userId);
		//System.out.println(toonNum);
		
		
		
		boolean result = new ToonMgr().likeChk(userId, toonNum);
		
		if(result) {
			response.getWriter().print("yes");
		}
		
	}

}
