package com.wxh.music.story.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.wxh.music.story.dao.MusicStoryDao;
import com.wxh.music.story.dao.TimeMachineDao;
import com.wxh.music.story.entity.TimeMachineEntity;

public class TimeMachineService {
	
	private Logger logger = Logger.getLogger(TimeMachineService.class);
	private TimeMachineDao dao = new TimeMachineDao();
	private MusicStoryDao storyDao = new MusicStoryDao();
	
	/**
	 * 查询时光机
	 * @param id
	 * @param size
	 * @return
	 */
	public List<TimeMachineEntity> queryTimeMachines(long id, int size){
		logger.debug("Request for time machines: id["+id+"]\tsize["+size+"]");
		try {
			return dao.queryTimeMachines(id, size);
		} catch (Exception e) {
			logger.warn(e.getMessage(),e);
		}
		return new ArrayList<>();
	}
	
	/**
	 * 新增时光机
	 * @param tme
	 * @return
	 */
	public long addTimeMachine(TimeMachineEntity tme){
		logger.info("Add a new time machine!"+tme.getId());
		if(tme == null || tme.getStory() == null || tme.getStory().getId() == 0) return -1;
		
		try {
			storyDao.updateMusicStory(tme.getStory().getId(), tme.getStory().getMusic(), tme.getStory().getSinger());
			long r = dao.addTimemachine(tme);
			return r;
		} catch (Exception e) {
			logger.warn(e.getMessage(),e);
			return -1;
		}
	}
	
	public static void main(String args[]){
		TimeMachineService service = new TimeMachineService();
		List<TimeMachineEntity> list = service.queryTimeMachines(0, 10);
		System.out.println(list.size());
		System.out.println(JSONArray.toJSONString(list));
	}

}
