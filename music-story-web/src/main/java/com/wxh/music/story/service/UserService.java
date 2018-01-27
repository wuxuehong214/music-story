package com.wxh.music.story.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

import com.wxh.music.story.dao.MusicStoryDao;
import com.wxh.music.story.dao.TimeMachineDao;
import com.wxh.music.story.dao.UserDao;
import com.wxh.music.story.db.NotInitializedException;
import com.wxh.music.story.entity.UserEntity;

public class UserService {
	
	private Logger logger = Logger.getLogger(UserService.class);
	private MusicStoryDao msDao = new MusicStoryDao();
	private TimeMachineDao tmDao = new TimeMachineDao();
	private UserDao userDao = new UserDao();
	
	/**
	 * 清空指定用户 的 所有资源信息
	 * @param id
	 */
	public boolean clearResource(long id){
		try {
			logger.debug("Clear user's music story and timemachin: "+id);
			tmDao.deleteMusicStoryByUser(id);
			msDao.deleteMusicStoryByUser(id);
			return true;
		} catch (Exception e) {
			logger.warn(e.getMessage(),e);
			return false;
		}
	}
	
	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	public boolean registerUser(UserEntity user){
		if("".equals(user.getName()) || user.getName() == null) user.setName(user.getUserId());
		try {
			userDao.addUser(user);
		} catch (Exception e) {
			logger.warn(e.getMessage(),e);
			return false;
		}
		return true;
	}
	
	/**
	 * 根据用户名查询
	 * @param userid
	 * @return
	 */
	public UserEntity queryUser(String userid){
		try {
			return userDao.getUser(userid);
		} catch (Exception e) {
			logger.warn(e.getMessage(),e);
		}
		return null;
	}

	/**
	 * 查询朋友
	 * @param userid
	 * @return
	 */
	public List<UserEntity> queryFriends(int userid){
		try {
			return userDao.queryFriendUsers(userid);
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return new ArrayList<UserEntity>();
	}
}
