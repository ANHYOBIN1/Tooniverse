package com.tooniverse;

import java.sql.*;
import java.util.*;

public class AdminDAO {
	
	private DBConnectionMgr pool;

	public AdminDAO() {
		pool = DBConnectionMgr.getInstance();
	}

	public ArrayList<AdminBean> getAllAdminList(){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<AdminBean> alist = new ArrayList<AdminBean>();
		try {
			con = pool.getConnection();
			sql = "SELECT * FROM ADMIN_MEM WHERE MEM_AUT = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AdminBean Abean = new AdminBean();
				Abean.setMem_id(rs.getString(1));
				Abean.setMem_aut(rs.getInt(2));
				alist.add(Abean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return alist;
	}
	
	
	public ArrayList<MemberBean> getNoAdminList(){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<MemberBean> mlist = new ArrayList<MemberBean>();
		try {
			con = pool.getConnection();
			sql = "SELECT ID FROM MEMBER WHERE ID NOT IN (SELECT MEM_ID FROM ADMIN_MEM)";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberBean Abean = new MemberBean();
				Abean.setId(rs.getString(1));
				mlist.add(Abean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return mlist;
	}
	
	
	public void registAdmin(String rId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "INSERT INTO ADMIN_MEM VALUES(?, 1)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rId);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	public void deleteAdmin(String dId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "DELETE FROM ADMIN_MEM WHERE MEM_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dId);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	
}
