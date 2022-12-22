package com.tooniverse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DTT.az")
public class DTT extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int toonNum = Integer.parseInt(request.getParameter("toonNum"));
		new ToonMgr().deleteToon(toonNum);
		response.getWriter().print("done");
	}

}
