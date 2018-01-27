package com.wxh.music.story.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.wxh.music.story.db.DatabasePool;
import com.wxh.music.story.db.NotInitializedException;
import com.wxh.music.story.entity.UserEntity;

public class UserDao {
	
	private DatabasePool pool = DatabasePool.getInstance();
	
	/**
	 * 新增用户
	 * @param user
	 * @throws TimeoutException
	 * @throws NotInitializedException
	 * @throws SQLException
	 */
	public void addUser(UserEntity user) throws TimeoutException, NotInitializedException, SQLException{
		String sql = "insert into td_ms_user(userid, password, name, sex) values(?,?,?,?)";
		Connection conn = pool.getConnection();
		java.sql.PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			try {
				ps.setString(1, user.getUserId());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getName());
				ps.setInt(4, user.getSex());
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
	 * 根据用户名查询
	 * @param userid
	 * @return
	 * @throws TimeoutException
	 * @throws NotInitializedException
	 * @throws SQLException
	 */
	public UserEntity getUser(String userid) throws TimeoutException, NotInitializedException, SQLException{
		String sql = "select * from td_ms_user where userid=?";
		Connection conn = pool.getConnection();
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			try {
				ps.setString(1, userid);
				rs = ps.executeQuery();
				try {
					if (rs.next()) {
						UserEntity user = new UserEntity();
						user.setId(rs.getLong("id"));
						user.setUserId(rs.getString("userid"));
						user.setPassword(rs.getString("password"));
						user.setName(rs.getString("name"));
						user.setSex(rs.getInt("sex"));
						return user;
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
	
	public UserEntity getUser(int id) throws TimeoutException, NotInitializedException, SQLException{
		String sql = "select * from td_ms_user where id=?";
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
						UserEntity user = new UserEntity();
						user.setId(rs.getLong("id"));
						user.setUserId(rs.getString("userid"));
						user.setPassword(rs.getString("password"));
						user.setName(rs.getString("name"));
						user.setSex(rs.getInt("sex"));
						return user;
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
	
	public List<UserEntity> queryFriendUsers(int userid) throws TimeoutException, NotInitializedException, SQLException{
		List<UserEntity> users = new ArrayList<UserEntity>();
		String sql = "select * from td_ms_user where id in(select friendid from td_ms_friends where userid=?)";
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
						UserEntity user = new UserEntity();
						user.setId(rs.getLong("id"));
						user.setUserId(rs.getString("userid"));
//						user.setPassword(rs.getString("password"));
						user.setName(rs.getString("name"));
						user.setSex(rs.getInt("sex"));
						users.add(user);
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
		
		return users;
	}

}
