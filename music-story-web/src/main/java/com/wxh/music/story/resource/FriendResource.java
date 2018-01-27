package com.wxh.music.story.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.wxh.music.story.entity.UserEntity;
import com.wxh.music.story.service.UserService;


/**
 * 他们  朋友
 * @author Administrator
 *
 */
@Path("friends")
public class FriendResource {
	
	private Logger logger = Logger.getLogger(FriendResource.class);
	private UserService userService = new UserService();
	
	@GET
	@Path("{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String queryFriends(@PathParam("userid") int userid) {
		logger.info("Query firends:" + userid);
		List<UserEntity> users = userService.queryFriends(userid);
		return JSONArray.toJSONString(users);
	}

}
