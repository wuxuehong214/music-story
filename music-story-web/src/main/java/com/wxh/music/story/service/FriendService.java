package com.wxh.music.story.service;

import org.apache.log4j.Logger;

import com.wxh.music.story.dao.FriendDao;

public class FriendService {
	
	private Logger logger = Logger.getLogger(FriendService.class);
	private FriendDao dao = new FriendDao();
	
	/**
	 * 新增朋友关系
	 * @param userid
	 * @param friendid
	 * @return
	 */
	public boolean addFriend(int userid, int friendid){
		logger.info("Add new friend for:"+userid+"  the friend is :"+friendid);
		try {
			dao.addFriend(userid, friendid);
			dao.addFriend(friendid, userid);
			return true;
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return false;
	}

}
