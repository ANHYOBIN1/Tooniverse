package com.tooniverse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReplLikeChk.do")
public class ReplLikeChk extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int whichReplNoPressLike = 0;
		int whichReplNoPressUnlike = 0;
		int whichReplNo = Integer.parseInt(request.getParameter("aucquseotrmf"));
		
		String id = request.getParameter("id");
		
		
		boolean result = new ReplyDAO().canPressLike(whichReplNo, id);
		
		if(result) {
			response.getWriter().print("nono");
		}else {
		
			if(request.getParameter("eotrmfqjsghtlfg")==null) {
				//좋아요버튼눌린경우
				//System.out.println("좋아요!");
				whichReplNoPressLike = Integer.parseInt(request.getParameter("eotrmfqjsghwhg"));
				int a = 1;
				new ReplyDAO().likeReply(whichReplNoPressLike, whichReplNo);
				new ReplyDAO().insertReplyEval(whichReplNo, id, a);
			}else if(request.getParameter("eotrmfqjsghwhg")==null) {
				//싫어요버튼눌린경우
				//System.out.println("싫어요!");
				whichReplNoPressUnlike = Integer.parseInt(request.getParameter("eotrmfqjsghtlfg"));
				int a = 2;
				new ReplyDAO().unlikeReply(whichReplNoPressUnlike, whichReplNo);
				new ReplyDAO().insertReplyEval(whichReplNo, id, a);
			}else {
				//에러난경우
				System.out.println("ReplLikeChk.do 처리 중 에러 발생");
			}
		
		}
		
		
		
		
		
		
	}

}
