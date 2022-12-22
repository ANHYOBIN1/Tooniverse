package com.tooniverse;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/paging.do")
public class Paging extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int endNum = Integer.parseInt(request.getParameter("Num1"));
		ArrayList<ToonBean> rlist = new PagingDAO().getAllToonWithPaging(endNum);
		
		//System.out.println(endNum);
		//System.out.println(rlist);
		
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(rlist, response.getWriter());
		
	}

}
