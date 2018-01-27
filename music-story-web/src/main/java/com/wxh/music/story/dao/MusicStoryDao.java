package com.wxh.music.story.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.wxh.music.story.db.DatabasePool;
import com.wxh.music.story.db.NotInitializedException;
import com.wxh.music.story.entity.MusicStoryEntity;
import com.wxh.music.story.entity.UserEntity;

/**
 * 歌事 信息表 操作
 * 
 * @author Administrator
 * 
 */
public class MusicStoryDao {

	private DatabasePool pool = DatabasePool.getInstance();
	private final String c_id = "a.id";
	private final String c_irc = "irc";
	private final String c_motion = "motion";
	private final String c_createdAt = "created_at";
	private final String c_place = "place";
	private final String c_background = "background";
	private final String c_music = "music";
	private final String c_singer = "singer";
	
	/**
	 * 删除歌事
	 * 
	 * @param user_id
	 * @throws TimeoutException
	 * @throws NotInitializedException
	 * @throws SQLException
	 */
	public void deleteMusicStoryByUser(long user_id) throws TimeoutException, NotInitializedException, SQLException{
		String sql = "delete from td_ms_musicstory where user_id=?";
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
	 * 查询歌事
	 * 
	 * @param id
	 * @param size
	 * @return
	 * @throws TimeoutException
	 * @throws NotInitializedException
	 * @throws SQLException
	 */
	public List<MusicStoryEntity> queryMusicStory(long userid, long id, int size)
			throws TimeoutException, NotInitializedException, SQLException {
		List<MusicStoryEntity> list = new ArrayList<MusicStoryEntity>();
		String sql;
		if (id == 0)
			sql = "select a.*,b.* from td_ms_musicstory a, td_ms_user b where a.id>? and a.user_id=b.id and a.user_id=? order by a.id desc limit ?";
		else
			sql = "select a.*,b.* from td_ms_musicstory a, td_ms_user b where a.id<? and a.user_id=b.id and a.user_id=? order by a.id desc limit ?";
		Connection conn = pool.getConnection();
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			try {
				ps.setLong(1, id);
				ps.setLong(2, userid);
				ps.setInt(3, size);
				rs = ps.executeQuery();
				try {
					while (rs.next()) {
						MusicStoryEntity mse = new MusicStoryEntity();
						mse.setId(rs.getLong(c_id));
						mse.setCreatedAt(rs.getTimestamp(c_createdAt));
						mse.setBackground(rs.getInt(c_background));
						mse.setIrc(rs.getString(c_irc));
						mse.setMotion(rs.getString(c_motion));
						mse.setPlace(rs.getString(c_place));
						mse.setMusic(rs.getString(c_music));
						mse.setSinger(rs.getString(c_singer));

						UserEntity user = new UserEntity();
						mse.setUser(user);
						user.setId(rs.getLong("b.id"));
						user.setName(rs.getString("name"));
						// user.setPassword(rs.getString("password"));
						user.setSex(rs.getInt("sex"));
						user.setUserId(rs.getString("userid"));

						list.add(mse);
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
	 * 新增歌事
	 * 
	 * @param story
	 * @throws TimeoutException
	 * @throws NotInitializedException
	 * @throws SQLException
	 */
	public long addMusicStory(MusicStoryEntity story) throws TimeoutException,
			NotInitializedException, SQLException {
		String sql = "insert into td_ms_musicstory(user_id,motion,irc,created_at,place,background) values"
				+ "(?,?,?,?,?,?)";
		Connection conn = pool.getConnection();
		java.sql.PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			try {
				ps.setLong(1, story.getUser().getId());
				ps.setString(2, story.getMotion());
				ps.setString(3, story.getIrc());
				ps.setTimestamp(4,
						new Timestamp(story.getCreatedAt().getTime()));
				ps.setString(5, story.getPlace());
				ps.setInt(6, story.getBackground());
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
	
	public List<MusicStoryEntity> queryStories(List<Integer> ids) throws TimeoutException, NotInitializedException, SQLException{
		List<MusicStoryEntity> list = new ArrayList<MusicStoryEntity>();
		StringBuffer sb = new StringBuffer();
		for(Integer i:ids){
			sb.append(","+i);
		}
		if(sb.length()>0)sb.delete(0, 1);
		String	sql = "select * from td_ms_musicstory where id in("+sb.toString()+")";
		Connection conn = pool.getConnection();
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			try {
				rs = ps.executeQuery();
				try {
					while (rs.next()) {
						MusicStoryEntity mse = new MusicStoryEntity();
						mse.setId(rs.getLong("id"));
						mse.setCreatedAt(rs.getTimestamp(c_createdAt));
						mse.setBackground(rs.getInt(c_background));
						mse.setIrc(rs.getString(c_irc));
						mse.setMotion(rs.getString(c_motion));
						mse.setPlace(rs.getString(c_place));
						mse.setMusic(rs.getString(c_music));
						mse.setSinger(rs.getString(c_singer));
//						UserEntity user = new UserEntity();
//						mse.setUser(user);
//						user.setId(rs.getLong("user_id"));
//						user.setName(rs.getString("name"));
//						user.setSex(rs.getInt("sex"));
//						user.setUserId(rs.getString("userid"));

						list.add(mse);
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

	/**
	 * 更新歌事 歌手 歌名
	 * 
	 * @param id
	 * @param music
	 * @param singer
	 * @throws TimeoutException
	 * @throws NotInitializedException
	 * @throws SQLException
	 */
	public void updateMusicStory(long id, String music, String singer)
			throws TimeoutException, NotInitializedException, SQLException {
		String sql = "update td_ms_musicstory set music=?,singer=? where id=?";
		Connection conn = pool.getConnection();
		java.sql.PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			try {
				ps.setString(1, music);
				ps.setString(2, singer);
				ps.setLong(3, id);
				;
				ps.executeUpdate();
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

}
