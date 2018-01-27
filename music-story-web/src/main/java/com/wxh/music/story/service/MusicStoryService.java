package com.wxh.music.story.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.wxh.music.story.dao.MusicStoryDao;
import com.wxh.music.story.entity.MusicStoryEntity;
import com.wxh.music.story.entity.UserEntity;

public class MusicStoryService {
	
	private Logger logger = Logger.getLogger(MusicStoryService.class);
	private MusicStoryDao dao = new MusicStoryDao();
	
	/**
	 * 分页查询歌事
	 * @param id
	 * @param size
	 * @return
	 */
	public List<MusicStoryEntity> queryMusicStories(long userid,long id, int size){
		logger.debug("Query music stories from id:"+id+" limit :"+size);
		try {
			return dao.queryMusicStory(userid,id, size);
		} catch (Exception e) {
			logger.warn(e.getMessage(),e);
		}
		return new ArrayList<MusicStoryEntity>();
	}
	
	/**
	 * 新增歌事
	 * @param mse
	 * @return
	 */
	public long addMusicStory(MusicStoryEntity mse){
		if(mse == null || mse.getUser() == null || mse.getUser().getId() == 0){
			logger.warn("The user id is 0 or not provided!");
			return -1;
		}
		try {
			long a = dao.addMusicStory(mse);
			return a;
		} catch (Exception e) {
			logger.warn(e.getMessage(),e);
		};
		return -1;
		
	}
	
	
	public static void main(String args[]){
		MusicStoryService service = new MusicStoryService();
		MusicStoryEntity mse = new MusicStoryEntity();
		mse.setUser(new UserEntity());
		mse.getUser().setId(1);
		mse.setMotion("dddd");
		mse.setCreatedAt(new Date());
		service.addMusicStory(mse);
//		List<MusicStoryEntity> stories = service.queryMusicStories(1,0, 1);
//		System.out.println(stories.size());
//		System.out.println(JSONArray.toJSONString(stories));
//		
//		String m = "{\"user_id\":\"123\"}";
//		MusicStoryEntity mse = JSONObject.parseObject(m, MusicStoryEntity.class);
//		System.out.println(mse.getId());
	}

}
