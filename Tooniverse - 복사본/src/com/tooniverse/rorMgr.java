package com.tooniverse;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/ror.do")
public class rorMgr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int v = Integer.parseInt(request.getParameter("rNum"));		
		
		ArrayList<RorBean> rList = new ReplyDAO().getRor(v);
		
		System.out.println(rList);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(rList, response.getWriter());
		
	}

}
