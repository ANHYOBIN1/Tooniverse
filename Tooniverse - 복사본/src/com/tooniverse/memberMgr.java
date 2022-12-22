package com.tooniverse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class memberMgr {
	
	private DBConnectionMgr pool;
	
	private static final String SAVEFOLDER = "D:\\JspWorkspace\\Tooniverse\\Webcontent\\fileServer";
	private static final String ENCTYPE = "UTF-8";
	private static final int MAXSIZE = 15 * 1024 * 1024; // 15
	
	public memberMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	
	// 일치하는 아이디가 있으면 true반환
	public boolean searchId(String id) {
		boolean result = false;
		Connection con = null;
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			sql = "SELECT * FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
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
	
	
	public boolean insertMember(MemberBean bean) {
		boolean result = false;
		Connection con = null;
		String sql = "";
		PreparedStatement pstmt = null;
		
		try {
			sql = "INSERT INTO member VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPwd());
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getGender());
			pstmt.setString(5, bean.getBirthday());
			pstmt.setString(6, bean.getEmail());
			pstmt.setString(7, bean.getZipcode());
			pstmt.setString(8, bean.getAddress());
			pstmt.setString(9, bean.getDetailaddress());
			String hobby[] = bean.getHobby();
			
			char hb[] = {'0', '0', '0', '0', '0'};
			
			String[] hb_list = {"인터넷", "여행", "게임", "영화", "운동"};
			
			for(int i = 0; i < hobby.length; i++) {
				for(int t = 0; t < hb_list.length; t++) {
					if(hobby[i].equals(hb_list[t])) {
						hb[t] = '1';
						break;
					}
				}
			}
			
			pstmt.setString(10, new String(hb));
			pstmt.setString(11, bean.getJob());
			
			//업데이트가 잘 된것
			if(pstmt.executeUpdate() == 1) {
				result = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		
		return result;
	}
	
	
	public boolean loginId(String id, String pwd) {
		boolean result = false;
		Connection con = null;
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			sql = "SELECT * FROM member WHERE id = ? and pwd = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
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
	
	
	//마이페이지에서 로그인한 유저 정보 찾기
	public MemberBean searchInfo(String id) {
		MemberBean bean = new MemberBean();
		Connection con = null;
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = pool.getConnection();
			sql = "SELECT * FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			bean.setId(rs.getString("id"));
			bean.setPwd(rs.getString("pwd"));
			bean.setName(rs.getString("name"));
			bean.setAddress(rs.getString("address"));
			bean.setDetailaddress(rs.getString("detailaddress"));
			bean.setBirthday(rs.getString("birthday"));
			bean.setZipcode(rs.getString("zipcode"));
			bean.setEmail(rs.getString("email"));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return bean;
	}
	
	
	public boolean updateMember(MemberBean bean) {
		boolean result = false;
		Connection con = null;
		String sql = "";
		PreparedStatement pstmt = null;
		
		try {
			sql = "UPDATE member SET pwd = ?, name = ?, gender = ?, birthday = ?, email = ?, zipcode = ?, address = ?, detailaddress = ? WHERE id = ?";
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getPwd());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getGender());
			pstmt.setString(4, bean.getBirthday());
			pstmt.setString(5, bean.getEmail());
			pstmt.setString(6, bean.getZipcode());
			pstmt.setString(7, bean.getAddress());
			pstmt.setString(8, bean.getDetailaddress());
			pstmt.setString(9, bean.getId());
			
			//업데이트가 잘 된것
			if(pstmt.executeUpdate() == 1) {
				result = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		
		return result;
	}
	
	
	public int isAdmin(String id) {
		
		int result = 0;
		Connection con = null;
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			sql="SELECT * FROM ADMIN_MEM WHERE MEM_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(2);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return result;
	}
	
	
}
