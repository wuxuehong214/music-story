package com.wxh.music.story.resource;

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
import com.wxh.music.story.entity.TimeMachineEntity;
import com.wxh.music.story.service.TimeMachineService;

/**
 * 时光机
 * @author Administrator
 *
 */
@Path("tms")
public class TimeMachineResource {
	
	private Logger logger = Logger.getLogger(TimeMachineResource.class);
	private TimeMachineService tmService;
	
	public TimeMachineResource(){
		tmService = new TimeMachineService();
	}
	
	@Path("list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String queryTimeMachine(@DefaultValue("0") @QueryParam("id") long id,
			@DefaultValue("5") @QueryParam("size") int size){
		
		logger.debug("请求时光机起始ID:"+id+"\t每页数:"+size);
		List<TimeMachineEntity> tms = tmService.queryTimeMachines(id, size);
		
		return JSONArray.toJSONString(tms);
	}
	
	@Path("add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addTimeMachine(String entity){
		logger.debug("请求发布时光机:"+entity);
		long r = -1;
		try{
			TimeMachineEntity tme = JSONObject.parseObject(entity, TimeMachineEntity.class);
			r = tmService.addTimeMachine(tme);
		}catch(Exception e){
			logger.warn(e.getMessage(),e);
		}
		return "{\"result\":"+(r==-1?0:1)+",\"id\":"+r+"}";
	}

}
