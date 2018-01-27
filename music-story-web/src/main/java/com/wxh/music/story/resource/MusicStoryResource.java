package com.wxh.music.story.resource;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxh.music.story.entity.MusicStoryEntity;
import com.wxh.music.story.service.MusicStoryService;


/**
 * 歌事资源管理
 * @author Administrator
 *
 */
@Path("ms")
public class MusicStoryResource {
	
	private Logger logger  = Logger.getLogger(MusicStoryResource.class);
	private MusicStoryService msService = new MusicStoryService();
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String postMusicStory(String entity){
		logger.debug("postMusicStory:"+entity);
		long r = -1;
		try{
			MusicStoryEntity mse = JSONObject.parseObject(entity, MusicStoryEntity.class);
			mse.setCreatedAt(new Date());
			r = msService.addMusicStory(mse);
		}catch(Exception e){
			logger.warn(e.getMessage(),e);
		}
		return "{\"result\":"+(r==-1?0:1)+",\"id\":"+r+"}";
	}
	
	@Path("list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String queryMusicStory(@DefaultValue("0") @QueryParam("id") long id,
			@DefaultValue("5") @QueryParam("size") int size,@QueryParam("user_id") long user_id){
		
		logger.info("请求歌事列表起始ID:"+id+"\t每页数:"+size);
		List<MusicStoryEntity> tms = msService.queryMusicStories(user_id,id, size);
		return JSONArray.toJSONString(tms);
	}
	

}
