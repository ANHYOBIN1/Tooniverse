package com.tooniverse;

import java.sql.*;
import java.util.*;


public class ReplyDAO {
	
	private DBConnectionMgr pool;

	public ReplyDAO() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public ArrayList<ReplyBean> getAllReply(int toonNum){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<ReplyBean> rlist = new ArrayList<ReplyBean>();
		
		try {
			con = pool.getConnection();
			sql = "SELECT * FROM reply WHERE ref = ? ORDER BY no DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, toonNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReplyBean rBean = new ReplyBean(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6));
				rlist.add(rBean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return rlist;
	}
	
	
	
	public ArrayList<ReplyBean> InsertReply(String nick, int toonNum, String content) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ArrayList<ReplyBean> repl = new ArrayList<ReplyBean>();
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			sql = "INSERT INTO reply values(SEQ_REPLY.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, toonNum);
			pstmt.setString(3, nick);
			int a = pstmt.executeUpdate();
			
			sql = "SELECT * FROM reply WHERE ref = ? ORDER BY no DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, toonNum);
			rs = pstmt.executeQuery();
			

			while(rs.next()) {
				
				ReplyBean kong = new ReplyBean(
						rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6)
						);
				
				
				repl.add(kong);
			}
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {pool.freeConnection(con, pstmt, rs);}
		
		return repl;
	}
	
	//unlikeReply
	public void likeReply(int a, int b) {
		//첫번쨰숫자는 보이는댓글중 몇번쨰인지(1부터시작)-근데사실상 좋아욘지 싫어욘지 판별하는기능, 두번째숫자는댓글자체의 no
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = pool.getConnection();
			sql = "UPDATE reply SET re_like = re_like + 1 WHERE no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		
	}
	
	public void unlikeReply(int a, int b) {
		//첫번쨰숫자는 보이는댓글중 몇번쨰인지(1부터시작)-근데사실상 좋아욘지 싫어욘지 판별하는기능, 두번째숫자는댓글자체의 no
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = pool.getConnection();
			sql = "UPDATE reply SET re_like = re_like - 1 WHERE no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		
	}
	
	
	public void insertReplyEval(int a, String id, int b) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		//System.out.println("바꿀댓글번호"+a);
		//System.out.println("바꾸려는사람아이디"+id);
		
		try {
			con = pool.getConnection();
			sql = "INSERT INTO reply_like VALUES(REPLY_LIKE_SEQ.NEXTVAL, ?, ?, SYSDATE, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, a);
			pstmt.setString(2, id);
			pstmt.setInt(3, b);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	
	
	public boolean canPressLike(int a, String id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = false;
		
		try {
			con = pool.getConnection();
			sql = "SELECT * FROM REPLY_LIKE WHERE R_LIKE_REF = ? AND R_LIKE_USER = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, a);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
	
		return result;
	}
	
	
	public ArrayList<RorBean> getRor(int v){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ArrayList<RorBean> rlist = new ArrayList<RorBean>();
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			sql = "SELECT * FROM REPLY_OF_REPLY WHERE ROR_REF = ? ORDER BY ROR_REDATE";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, v);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RorBean r = new RorBean();
				r.setRor_ref(rs.getInt(1));
				r.setRor_content(rs.getString(2));
				r.setRor_redate(rs.getString(3));
				r.setRor_name(rs.getString(4));
				r.setSup_ref(rs.getInt(5));
				rlist.add(r);
			}
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {pool.freeConnection(con, pstmt, rs);}
		
		return rlist;
	}
	
	public void insertRor(int t, int toonNum, String cont, String id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		sql = "";
		
		try {
			con = pool.getConnection();
			sql = "INSERT INTO reply_of_reply VALUES(?, ?, sysdate, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, t);
			pstmt.setString(2, cont);
			pstmt.setString(3, id);
			pstmt.setInt(4, toonNum);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	
	
}
