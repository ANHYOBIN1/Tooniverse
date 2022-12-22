package com.tooniverse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inserRor.do")
public class InsertRor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int t = Integer.parseInt(request.getParameter("tNum"));
		int toonNum = Integer.parseInt(request.getParameter("toonNum"));
		String cont = request.getParameter("cont");
		String id = request.getParameter("id");
		
		new ReplyDAO().insertRor(t, toonNum, cont, id);
		
		response.getWriter().print("success");
		
	}

}
