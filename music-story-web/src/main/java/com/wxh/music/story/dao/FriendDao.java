package com.wxh.music.story.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import com.wxh.music.story.db.DatabasePool;
import com.wxh.music.story.db.NotInitializedException;

public class FriendDao {

	private DatabasePool pool = DatabasePool.getInstance();

	public void addFriend(int userid, int friendid) throws TimeoutException, NotInitializedException, SQLException {

		String sql = "insert into td_ms_friends(userid,friendid) values(?,?)";
		Connection conn = pool.getConnection();
		java.sql.PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			try {
				ps.setInt(1,userid);
				ps.setInt(2,friendid);
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
}
