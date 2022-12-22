package com.tooniverse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/newSeriesDAO.do")
public class NewSeriesDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String newSeriesName = request.getParameter("newSeriesName");
		if(newSeriesName==null||newSeriesName.equals("")) {
			response.getWriter().print("stop");
		}else {
			new UploadMgr().emptySeriesClean(newSeriesName);
			boolean result = new UploadMgr().searchNewSeries(newSeriesName);
			if(result) {
				response.getWriter().print("YY");
			}else {
				new UploadMgr().insertNewSeries(newSeriesName, id);
			}
		}
		
			
		
	}


}
