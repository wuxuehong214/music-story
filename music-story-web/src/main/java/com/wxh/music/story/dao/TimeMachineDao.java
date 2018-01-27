package com.wxh.music.story.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.wxh.music.story.db.DatabasePool;
import com.wxh.music.story.db.NotInitializedException;
import com.wxh.music.story.entity.MusicStoryEntity;
import com.wxh.music.story.entity.TimeMachineEntity;
import com.wxh.music.story.entity.UserEntity;

/**
 * 时光机信息操作表
 * @author Administrator
 *
 */
public class TimeMachineDao {
	
	private DatabasePool pool = DatabasePool.getInstance();
	
	/**
	 * 删除指定用户的时光机
	 * 	 * 
	 * @param user_id
	 * @throws TimeoutException
	 * @throws NotInitializedException
	 * @throws SQLException
	 */
	public void deleteMusicStoryByUser(long user_id) throws TimeoutException, NotInitializedException, SQLException{
		String sql = "delete from td_ms_timemachine where storyId in (select id from td_ms_musicstory where user_id=?)";
		Connection conn = pool.getConnection();
		java.sql.PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			try {
				ps.setLong(1, user_id);
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
	
	/**
	 * 查询时光机信息
	 * @param id
	 * @param size
	 * @return
	 * @throws TimeoutException
	 * @throws NotInitializedException
	 * @throws SQLException
	 */
	public List<TimeMachineEntity> queryTimeMachines(long id, int size) throws TimeoutException, NotInitializedException, SQLException{
		List<TimeMachineEntity> list = new ArrayList<TimeMachineEntity>();
		String sql;
		if(id == 0)
			sql = "select a.*,a.created_at,b.* ,c.* from td_ms_timemachine a, td_ms_musicstory b, td_ms_user c where a.storyId=b.id and b.user_id=c.id and a.id > ? order by a.id desc limit ?";
		else
			sql = "select a.*,a.created_at,b.*,c.* from td_ms_timemachine a, td_ms_musicstory b, td_ms_user c  where a.storyId=b.id and b.user_id=c.id and a.id<? order by a.id desc limit ?";
		Connection conn = pool.getConnection();
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			try {
				ps.setLong(1, id);
				ps.setInt(2, size);
				rs = ps.executeQuery();
				try {
					while (rs.next()) {
						TimeMachineEntity tme = new TimeMachineEntity();
						MusicStoryEntity mse = new MusicStoryEntity();
						tme.setStory(mse);
						
						tme.setId(rs.getLong("a.id"));
						tme.setCreatedAt(rs.getTimestamp("created_at"));
						
						
						mse.setId(rs.getLong("b.id"));
						mse.setCreatedAt(rs.getTimestamp("created_at"));
						mse.setBackground(rs.getInt("background"));
						mse.setIrc(rs.getString("irc"));
						mse.setMotion(rs.getString("motion"));
						mse.setPlace(rs.getString("place"));
						mse.setSinger(rs.getString("singer"));
						mse.setMusic(rs.getString("music"));
						
						UserEntity user = new UserEntity();
						mse.setUser(user);
						user.setId(rs.getLong("c.id"));
						user.setName(rs.getString("name"));
//						user.setPassword(rs.getString("password"));
						user.setSex(rs.getInt("sex"));
						user.setUserId(rs.getString("userid"));
						
						list.add(tme);
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
		return list;
	}
	
	/**
	 * 新增时光机
	 * @param tme
	 * @throws NotInitializedException 
	 * @throws TimeoutException 
	 * @throws SQLException 
	 */
	public long addTimemachine(TimeMachineEntity tme) throws TimeoutException, NotInitializedException, SQLException{
		String sql = "insert into td_ms_timemachine(storyId,created_at) values(?,?)";
		Connection conn = pool.getConnection();
		java.sql.PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			try {
				ps.setLong(1, tme.getStory().getId());
				ps.setTimestamp(2, new Timestamp(new Date().getTime()));
				ps.execute();
				return queryLastInsertId(conn);
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
	
	private long queryLastInsertId(Connection conn) throws SQLException {
		if (conn != null) {
			java.sql.PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
				try {
					ResultSet rs = ps.executeQuery();
					try {
						if (rs.next())
							return rs.getShort(1);
					} finally {
						rs.close();
					}
				} finally {
					ps.close();
				}
			} catch (SQLException e) {
				throw e;
			}

		}
			return -1;
	}

}
