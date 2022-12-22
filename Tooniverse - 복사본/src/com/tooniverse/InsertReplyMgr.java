package com.tooniverse;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/insert.ay")
public class InsertReplyMgr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nick = request.getParameter("nick");
		int toonNum = Integer.parseInt(request.getParameter("toonNum"));
		String content = request.getParameter("content");
		
		//System.out.println(nick);
		//System.out.println(toonNum);
		//System.out.println(content);
		
		ArrayList<ReplyBean> alist = new ReplyDAO().InsertReply(nick, toonNum, content);
		
		response.setContentType("text; charset=UTF-8");
		
		
		new Gson().toJson(alist, response.getWriter());
	}

}
