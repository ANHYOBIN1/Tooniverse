package com.tooniverse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PagingDAO {
	
	
	
	private DBConnectionMgr pool;

	public PagingDAO() {
		pool = DBConnectionMgr.getInstance();
	}
	
	
	
	public ArrayList<ToonBean> getAllToonWithPaging(int endnum){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;

		ArrayList<ToonBean> tlist = new ArrayList<ToonBean>();
		try {
			con = pool.getConnection();
			sql = "SELECT t2.* from (SELECT ROWNUM r1, t1.* from(SELECT * FROM toon join series using (series_no) ORDER BY toon_date DESC)t1)t2 where r1 between ? and ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, endnum+1);
			pstmt.setInt(2, endnum+5);
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
				bean.setSeries_name(rs.getString(11));
				tlist.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return tlist;
	}
	
	
	
	
	
}
