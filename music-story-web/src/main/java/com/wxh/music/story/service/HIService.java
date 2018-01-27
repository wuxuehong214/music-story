package com.wxh.music.story.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.wxh.music.story.dao.HIDao;
import com.wxh.music.story.dao.MusicStoryDao;
import com.wxh.music.story.dao.UserDao;
import com.wxh.music.story.entity.HIEntity;

public class HIService {
	
	private Logger logger = Logger.getLogger(HIService.class);
	private HIDao dao = null;
	private MusicStoryDao msDao;
	private UserDao userDao;
	
	public HIService(){
		dao = new HIDao();
		msDao = new MusicStoryDao();
		userDao = new UserDao();
	}
	
	public HIEntity queryHI(int id){
		try {
			return dao.getHi(id);
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return null;
	}
	
	public boolean delHI(int id){
		logger.info("Del the hi:"+id);
		try {
			dao.delHi(id);
			return true;
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return false;
	}
	
	public boolean addHI(HIEntity hi){
		logger.info("say hi:"+JSONObject.toJSONString(hi));
		try {
			dao.addHi(hi);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
		}
		return false;
	}
	
	public List<HIEntity> queryHIs(int userid){
		logger.info("Query HIs:"+userid);
		try {
			 List<HIEntity>  his = dao.query(userid);
			 for(HIEntity hi:his){
				hi.setStories(msDao.queryStories(hi.getMsIds()));
				
				hi.setUser(userDao.getUser(hi.getUseridFrom()));
				if(hi.getUser()!=null)
					hi.getUser().setPassword(null);
			 }
			 return his;
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return new ArrayList<HIEntity>();
	}

}
