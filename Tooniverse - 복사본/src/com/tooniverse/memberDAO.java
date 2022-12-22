package com.tooniverse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 회원가입 ajax표현을 위한 기능구현 서블릿
 */
@WebServlet("/memberDAO.do")
public class memberDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		// 일치하는 아이디가 있으면 true반환
		boolean searchId = new memberMgr().searchId(id);
		response.setContentType("text; charset=UTF-8");
		if(searchId) {
			response.getWriter().print("idyesyes");
		}else {
			response.getWriter().print("idnono");
		}
		
		/* System.out.println(searchId); */
		
		
	}

}
