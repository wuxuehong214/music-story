package com.wxh.music.story.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.wxh.music.story.db.DatabasePool;
import com.wxh.music.story.db.NotInitializedException;
import com.wxh.music.story.entity.HIEntity;

public class HIDao {
	
	private DatabasePool pool = DatabasePool.getInstance();
	
	
	public HIEntity getHi(int id) throws SQLException, TimeoutException, NotInitializedException{
		String sql = "select * from td_ms_hi where id=?";
		Connection conn = pool.getConnection();
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			try {
				ps.setInt(1, id);
				rs = ps.executeQuery();
				try {
					if (rs.next()) {
						HIEntity hi = new HIEntity();
						hi.setId(rs.getInt("id"));
						hi.setUseridFrom(rs.getInt("userid_from"));
						hi.setUseridTo(rs.getInt("userid_to"));
						hi.setMsIds(new ArrayList<Integer>());
						
						int ms1 = rs.getInt("ms1");
						if(ms1 != -1)hi.getMsIds().add(ms1);
						int ms2 = rs.getInt("ms2");
						if(ms2 != -1)hi.getMsIds().add(ms2);
						int ms3 = rs.getInt("ms3");
						if(ms3 != -1)hi.getMsIds().add(ms3);
						int ms4 = rs.getInt("ms4");
						if(ms4 != -1)hi.getMsIds().add(ms4);
						int ms5 = rs.getInt("ms5");
						if(ms5 != -1)hi.getMsIds().add(ms5);
						
						return hi;
					}
				} finally {
					rs.close();
				}
			} finally {
				ps.close();
			}
		} catch (SQLException e) {
			conn.close();
			throw e;
		} finally {
			pool.release(conn);
		}
		return null;
	}
	
	public void delHi(int id) throws SQLException, TimeoutException, NotInitializedException{
		String sql = "delete from td_ms_hi where id=?";
		Connection conn = pool.getConnection();
		java.sql.PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			try {
				ps.setInt(1, id);
				ps.execute();
			} finally {
				ps.close();
			}
		} catch (SQLException e) {
			conn.close();
			throw e;
		} finally {
			pool.release(conn);
		}
	}

	public void addHi(HIEntity hi) throws TimeoutException, NotInitializedException, SQLException{
		
		String sql = "insert into td_ms_hi(userid_to,userid_from,ms1,ms2,ms3,ms4,ms5) values"
				+ "(?,?,?,?,?,?,?)";
		Connection conn = pool.getConnection();
		java.sql.PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			try {
				ps.setInt(1, hi.getUseridTo());
				ps.setInt(2, hi.getUseridFrom());
				ps.setInt(3, (hi.getMsIds().size()>=1)?hi.getMsIds().get(0):-1);
				ps.setInt(4, (hi.getMsIds().size()>=2)?hi.getMsIds().get(1):-1);
				ps.setInt(5, (hi.getMsIds().size()>=3)?hi.getMsIds().get(2):-1);
				ps.setInt(6, (hi.getMsIds().size()>=4)?hi.getMsIds().get(3):-1);
				ps.setInt(7, (hi.getMsIds().size()>=5)?hi.getMsIds().get(4):-1);
				ps.execute();
			} finally {
				ps.close();
			}
		} catch (SQLException e) {
			conn.close();
			throw e;
		} finally {
			pool.release(conn);
		}
	}
	
	public List<HIEntity> query(int userid) throws SQLException, TimeoutException, NotInitializedException{
		List<HIEntity> his = new ArrayList<HIEntity>();
		String sql = "select * from td_ms_hi where userid_to=?";
		Connection conn = pool.getConnection();
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			try {
				ps.setInt(1, userid);
				rs = ps.executeQuery();
				try {
					while (rs.next()) {
						HIEntity hi = new HIEntity();
						hi.setId(rs.getInt("id"));
						hi.setUseridFrom(rs.getInt("userid_from"));
						hi.setUseridTo(rs.getInt("userid_to"));
						hi.setMsIds(new ArrayList<Integer>());
						
						int ms1 = rs.getInt("ms1");
						if(ms1 != -1)hi.getMsIds().add(ms1);
						int ms2 = rs.getInt("ms2");
						if(ms2 != -1)hi.getMsIds().add(ms2);
						int ms3 = rs.getInt("ms3");
						if(ms3 != -1)hi.getMsIds().add(ms3);
						int ms4 = rs.getInt("ms4");
						if(ms4 != -1)hi.getMsIds().add(ms4);
						int ms5 = rs.getInt("ms5");
						if(ms5 != -1)hi.getMsIds().add(ms5);
						
						his.add(hi);
					}
				} finally {
					rs.close();
				}
			} finally {
				ps.close();
			}
		} catch (SQLException e) {
			conn.close();
			throw e;
		} finally {
			pool.release(conn);
		}
		return his;
	}
	
	public static void main(String args[]) throws SQLException, TimeoutException, NotInitializedException{
//		MusicStoryDao dao = new MusicStoryDao();
//		List<Integer> ids = new ArrayList<Integer>();
//		ids.add(1);
//		ids.add(3);
//		System.out.println("########");
//		List<MusicStoryEntity> his;
//		try {
//			his = dao.queryStories(ids);
//			System.out.println("===========");
//			System.out.println(his.size());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		HIDao d = new HIDao();
		 List<HIEntity> his  =  d.query(2);
		 System.out.println(his.size());
	}
	
}
