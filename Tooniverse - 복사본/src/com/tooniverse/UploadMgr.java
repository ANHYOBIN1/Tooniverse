package com.tooniverse;

import java.io.File;
import java.sql.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tooniverse.DBConnectionMgr;

public class UploadMgr {

	private DBConnectionMgr pool;
	private static final String SAVEFOLDER = "D:/JspWorkspace/Tooniverse/Webcontent/fileServer";
	private static final String ENCTYPE = "UTF-8";
	private static final int MAXSIZE = 15 * 1024 * 1024; // 15MB

	public UploadMgr() {
		pool = DBConnectionMgr.getInstance();
	}

	public static int searchSeriesNo(String name) {
		Connection con2 = null;
		String sql0 = null;
		PreparedStatement pstmt0 = null;
		ResultSet rs = null;
		int series_no = 0;
		DBConnectionMgr pool2 = DBConnectionMgr.getInstance();
		try {
			con2 = pool2.getConnection();
			String series_name = name;

			sql0 = "select * from series where series_title = ?";
			pstmt0 = con2.prepareStatement(sql0);
			pstmt0.setString(1, series_name);
			rs = pstmt0.executeQuery();
			if (rs.next()) {
				series_no = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool2.freeConnection(con2, pstmt0, rs);
		}

		return series_no;
	}

	public void uploadToon(HttpServletRequest req) {

		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		MultipartRequest multi = null;
		String filename = "";

		try {
			con = pool.getConnection();
			multi = new MultipartRequest(req, SAVEFOLDER, MAXSIZE, ENCTYPE, new DefaultFileRenamePolicy());
			
			Enumeration files = multi.getFileNames();
			//System.out.println(files);
		

			while (files.hasMoreElements()) {
				// 파일요소
				String name = (String) files.nextElement();
				// 파일이름(서버에올라온이름)
				String fileName = multi.getFilesystemName(name);
				// 파일이름(원래 클라이언트가 올린 이름)
				String originalFileName = multi.getOriginalFileName(name);

				String type = multi.getContentType(name);

				File f = multi.getFile(name);
				

				/* System.out.println("filename : " + fileName); */

				if (!filename.equals("")) {
					if(filename=="null"||filename==null){continue;}
					else{filename += "ahb6920" + fileName;}
				} else {
					filename += fileName;
				}

			}

			int series_no = UploadMgr.searchSeriesNo(multi.getParameter("series_name"));

			sql = "INSERT INTO toon VALUES(toon_seq.nextval, ?, ?, default, sysdate, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, filename);
			pstmt.setString(2, multi.getParameter("toon_content"));
			pstmt.setString(3, multi.getParameter("id"));
			pstmt.setInt(4, series_no);
			pstmt.setString(5, multi.getParameter("series_title") + "화");

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}

	}

	public ArrayList<String> getMyToomNameList(String id) {
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> myToonNameList = new ArrayList<String>();
		try {
			con = pool.getConnection();
			sql = "SELECT * FROM toon join series using (series_no) where series_artist = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (myToonNameList.contains(rs.getString(10))) {
					continue;
				}
				myToonNameList.add(rs.getString(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return myToonNameList;
	}

	public boolean searchNewSeries(String newSeriesName) {
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			con = pool.getConnection();
			sql = "select * from series where series_title = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newSeriesName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return result;
	}

	public void insertNewSeries(String newSeriesName, String id) {
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = pool.getConnection();
			sql = "insert into series values(series_seq.nextval, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, newSeriesName);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

	
	
	public int calSeries(String series_name) {
		
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			con = pool.getConnection();
			sql = "SELECT COUNT(TOON_ID) FROM (SELECT TOON_ID, S.SERIES_TITLE FROM TOON JOIN SERIES S using (series_no)) WHERE SERIES_TITLE = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, series_name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	
		return result;
	}
	
	//시리즈로 연재된게 0개면 클리너돌리게
	public void emptySeriesClean(String newSeriesName) {
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			boolean result = thisSeriesZero(newSeriesName);
			if(!result) {
				//한개도 없으면 지워야함
				con = pool.getConnection();
				sql = "DELETE FROM SERIES WHERE SERIES_TITLE = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, newSeriesName);
				pstmt.executeUpdate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	
	public boolean thisSeriesZero(String newSeriesName) {
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isZero = false;
		
		try {
			con = pool.getConnection();
			sql = "SELECT TOON_ID FROM TOON WHERE SERIES_NO = (SELECT SERIES_NO FROM SERIES WHERE SERIES_TITLE = ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newSeriesName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isZero = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return isZero;
	}
	
	
	
}
