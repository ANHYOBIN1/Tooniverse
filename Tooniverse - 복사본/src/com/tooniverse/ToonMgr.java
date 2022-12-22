package com.tooniverse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ToonMgr {
	
	private DBConnectionMgr pool;
	
	private static final String SAVEFOLDER = "D:\\JspWorkspace\\Tooniverse\\Webcontent\\fileServer";
	private static final String ENCTYPE = "UTF-8";
	private static final int MAXSIZE = 15 * 1024 * 1024; // 15
	
	public ToonMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public ToonBean readToon(int num) {
		
		ToonBean bean = new ToonBean();
		Connection con = null;
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = pool.getConnection();
			sql = "SELECT BT2.* FROM (SELECT ROWNUM R1, BT1.* FROM(SELECT * FROM toon join series using (series_no) ORDER BY TOON_DATE DESC)BT1)BT2  WHERE R1 = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			rs.next();
			bean.setToon_toon(rs.getString("TOON_TOON"));
			bean.setArtist_id(rs.getString("ARTIST_ID"));
			bean.setSeries_no(rs.getInt("SERIES_NO"));
			bean.setSeries_title(rs.getString("SERIES_TITLE"));
			bean.setToon_content(rs.getString("TOON_CONTENT"));
			bean.setToon_date(rs.getString("TOON_DATE"));
			bean.setToon_id(rs.getInt("TOON_ID"));
			bean.setToon_like(rs.getInt("TOON_LIKE"));
			bean.setSeries_name(rs.getString(11));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return bean;
	}
	
	public ToonBean readToonToToonNum(int toonNum) {
		
		ToonBean bean = new ToonBean();
		Connection con = null;
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = pool.getConnection();
			sql = "SELECT * FROM TOON JOIN SERIES S USING(SERIES_NO) WHERE TOON_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, toonNum);
			rs = pstmt.executeQuery();
			rs.next();
			bean.setToon_toon(rs.getString("TOON_TOON"));
			bean.setArtist_id(rs.getString("ARTIST_ID"));
			bean.setSeries_no(rs.getInt("SERIES_NO"));
			bean.setSeries_title(rs.getString("SERIES_TITLE"));
			bean.setToon_content(rs.getString("TOON_CONTENT"));
			bean.setToon_date(rs.getString("TOON_DATE"));
			bean.setToon_id(rs.getInt("TOON_ID"));
			bean.setToon_like(rs.getInt("TOON_LIKE"));
			bean.setSeries_name(rs.getString(10));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return bean;
	}
		
		
	
	public ArrayList<ToonBean> getAllToonList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;

		ArrayList<ToonBean> tlist = new ArrayList<ToonBean>();
		try {
			con = pool.getConnection();
			sql = "SELECT * FROM toon join series using (series_no) ORDER BY toon_date DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ToonBean bean = new ToonBean();
				bean.setToon_toon(rs.getString("TOON_TOON"));
				bean.setArtist_id(rs.getString("ARTIST_ID"));
				bean.setSeries_no(rs.getInt("SERIES_NO"));
				bean.setSeries_title(rs.getString("SERIES_TITLE"));
				bean.setToon_content(rs.getString("TOON_CONTENT"));
				bean.setToon_date(rs.getString("TOON_DATE"));
				bean.setToon_id(rs.getInt("TOON_ID"));
				bean.setToon_like(rs.getInt("TOON_LIKE"));
				bean.setSeries_name(rs.getString(10));
				tlist.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return tlist;
	}
	
	
	
	
	public int[] calPtoP(int toonNum){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		int[] result = {0, 0}; //툰넘, 넘

		try {
			
			
			con = pool.getConnection();
			sql = "SELECT * FROM TOON WHERE SERIES_NO = (SELECT SERIES_NO FROM TOON WHERE TOON_ID = ?) AND SUBSTR(SERIES_TITLE,0,1) = (SELECT TO_NUMBER(SUBSTR(SERIES_TITLE,0,1))-1 FROM TOON WHERE TOON_ID = ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, toonNum);
			pstmt.setInt(2, toonNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result[0] = rs.getInt(1);
			}else {
				result[0] = 0;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return result;
	}
	
	
	
	
	
	
	public int[] calP(int toonNum){
		Connection con2 = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		ResultSet rs2 = null;
		int[] result = calPtoP(toonNum); //툰넘, 넘

		try {
			
			con2 = pool.getConnection();
			sql = "SELECT BT2.* FROM (SELECT ROWNUM R1, BT1.* FROM(SELECT * FROM toon join series using (series_no) ORDER BY TOON_DATE DESC)BT1)BT2 WHERE TOON_ID = ?";
			pstmt2 = con2.prepareStatement(sql);
			pstmt2.setInt(1, result[0]);
			rs2 = pstmt2.executeQuery();
			if(rs2.next()) {
				result[1] = rs2.getInt(1);
			}else {
				result[1] = 0;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con2, pstmt2, rs2);
		}
		return result;
	}
	
	
	
	public int[] calNtoN(int toonNum){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		int[] result = {0, 0}; //툰넘, 넘

		try {
			
			
			con = pool.getConnection();
			sql = "SELECT * FROM TOON WHERE SERIES_NO = (SELECT SERIES_NO FROM TOON WHERE TOON_ID = ?) AND SUBSTR(SERIES_TITLE,0,1) = (SELECT TO_NUMBER(SUBSTR(SERIES_TITLE,0,1))+1 FROM TOON WHERE TOON_ID = ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, toonNum);
			pstmt.setInt(2, toonNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result[0] = rs.getInt(1);
			}else {
				result[0] = 0;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return result;
	}
	
	
	
	
	
	
	
	public int[] calN(int toonNum){
		Connection con2 = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		ResultSet rs2 = null;
		int[] result = calNtoN(toonNum); //툰넘, 넘

		try {
			
			con2 = pool.getConnection();
			sql = "SELECT BT2.* FROM (SELECT ROWNUM R1, BT1.* FROM(SELECT * FROM toon join series using (series_no) ORDER BY TOON_DATE DESC)BT1)BT2 WHERE TOON_ID = ?";
			pstmt2 = con2.prepareStatement(sql);
			pstmt2.setInt(1, result[0]);
			rs2 = pstmt2.executeQuery();
			if(rs2.next()) {
				result[1] = rs2.getInt(1);
			}else {
				result[1] = 0;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con2, pstmt2, rs2);
		}
		return result;
	}
	
	
	public boolean likeChk(String userId, int toonNum) {
		
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try {
			con = pool.getConnection();
			sql = "SELECT * FROM toon_like WHERE like_user = ? and like_ref = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, toonNum);
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
	
	
	public boolean likeIt(String userId, int toonNum) {
		//좋아요 데이터 insert
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try {
			con = pool.getConnection();
			sql = "INSERT INTO toon_like VALUES(TOON_LIKE_SEQ.NEXTVAL, ?, ?, SYSDATE)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, toonNum);
			pstmt.setString(2, userId);
			int a = pstmt.executeUpdate();
			if(a > 0) {
				result = true;
				increaseToonLike(toonNum);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return result;
	}
	
	
	
	public boolean unLikeIt(String userId, int toonNum) {
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try {
			con = pool.getConnection();
			sql = "DELETE FROM toon_like WHERE like_ref = ? and like_user = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, toonNum);
			pstmt.setString(2, userId);
			int a = pstmt.executeUpdate();
			if(a > 0) {
				result = true;
				decreaseToonLike(toonNum);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return result;
	}
	
	
	
	public void increaseToonLike(int toonNum) {
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		
		try {
			con = pool.getConnection();
			sql = "UPDATE toon SET toon_like = toon_like+1 where toon_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, toonNum);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		
	}
	
	public void decreaseToonLike(int toonNum) {
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		
		try {
			con = pool.getConnection();
			sql = "UPDATE toon SET toon_like = toon_like-1 where toon_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, toonNum);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		
	}
	
	public void deleteToon(int toonNum) {
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		
		try {
			con = pool.getConnection();
			sql = "DELETE FROM TOON WHERE TOON_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, toonNum);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	
	//작품명
	public ArrayList<ToonBean> getSearchResult_1(String searchWord){
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		ArrayList<ToonBean> tlist_1 = new ArrayList<ToonBean>();
		ResultSet rs = null;
		try {
			con = pool.getConnection();
			sql = "SELECT * FROM TOON JOIN SERIES S USING(SERIES_NO) WHERE S.SERIES_TITLE like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+searchWord+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ToonBean bean = new ToonBean();
				bean.setToon_toon(rs.getString("TOON_TOON"));
				bean.setArtist_id(rs.getString("ARTIST_ID"));
				bean.setSeries_no(rs.getInt("SERIES_NO"));
				bean.setSeries_title(rs.getString("SERIES_TITLE"));
				bean.setToon_content(rs.getString("TOON_CONTENT"));
				bean.setToon_date(rs.getString("TOON_DATE"));
				bean.setToon_id(rs.getInt("TOON_ID"));
				bean.setToon_like(rs.getInt("TOON_LIKE"));
				bean.setSeries_name(rs.getString(10));
				tlist_1.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return tlist_1;
	}
	
	
	//작가명
	public ArrayList<ToonBean> getSearchResult_2(String searchWord){
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		ArrayList<ToonBean> tlist_2 = new ArrayList<ToonBean>();
		ResultSet rs = null;
		try {
			con = pool.getConnection();
			sql = "SELECT * FROM TOON JOIN SERIES S USING(SERIES_NO) WHERE ARTIST_ID like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+searchWord+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ToonBean bean = new ToonBean();
				bean.setToon_toon(rs.getString("TOON_TOON"));
				bean.setArtist_id(rs.getString("ARTIST_ID"));
				bean.setSeries_no(rs.getInt("SERIES_NO"));
				bean.setSeries_title(rs.getString("SERIES_TITLE"));
				bean.setToon_content(rs.getString("TOON_CONTENT"));
				bean.setToon_date(rs.getString("TOON_DATE"));
				bean.setToon_id(rs.getInt("TOON_ID"));
				bean.setToon_like(rs.getInt("TOON_LIKE"));
				bean.setSeries_name(rs.getString(10));
				tlist_2.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return tlist_2;
	}
	
	
}
