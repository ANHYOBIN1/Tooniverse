package com.tooniverse;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/AdminChk.ad")
public class AdminChk extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("want").equals("yes")) {
			//admin인애들내놔
			ArrayList<AdminBean> alist = new AdminDAO().getAllAdminList();
			response.setContentType("application/json; charset=UTF-8");
			new Gson().toJson(alist, response.getWriter());
		}else {
			//아닌애들내놔
			ArrayList<MemberBean> mlist = new AdminDAO().getNoAdminList();
			response.setContentType("application/json; charset=UTF-8");
			new Gson().toJson(mlist, response.getWriter());
		}
			
		
	}

}
