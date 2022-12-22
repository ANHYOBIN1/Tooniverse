package com.tooniverse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/howMySeries.do")
public class CalMySeriesMgr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String series_name = request.getParameter("series_name");
		int result = new UploadMgr().calSeries(series_name);
		response.getWriter().print(result);
		
	}

}
