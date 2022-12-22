package com.tooniverse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tl.do")
public class ToonLikeDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int chk = Integer.parseInt(request.getParameter("thisChk"));
		String userId = request.getParameter("userId");
		int toonNum = Integer.parseInt(request.getParameter("toonNum"));
		
		if(chk == 0) {
			//체크안된경우니까 좋아요를 insert하는 기능
			boolean result = new ToonMgr().likeIt(userId, toonNum);
		}else if(chk == 1) {
			//체크해놓은경우니까 체크를 해제하면서 좋아요 눌렀던걸 찾아서 delete
			boolean result = new ToonMgr().unLikeIt(userId, toonNum);
		}else {
			System.out.println("ToonLikeDAO에서 에러발생");
		}
		
		
		
	}

}
