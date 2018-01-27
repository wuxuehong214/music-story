package com.wxh.music.story.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.wxh.music.story.entity.UserEntity;
import com.wxh.music.story.service.UserService;

/**
 * 
 * 
 * @author Administrator
 *
 */

@Path("user")
public class UserResource {
	
	private Logger logger  = Logger.getLogger(UserResource.class);
	private UserService userService = new UserService();
	
	@DELETE
	@Path("clear/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String clearResource(@PathParam("id") long id){
		logger.debug("请求清除用户歌事/时光机信息:"+id);
		boolean r = userService.clearResource(id);
		return "{\"result\":"+(r?1:0)+"}";
	}
	
	@POST
	@Path("register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String register(String entity){
		logger.debug("请求注册用户:"+entity);
		boolean r = false;
		String msg = null;
		UserEntity user = null;
		try{
			user = JSONObject.parseObject(entity, UserEntity.class);
		}catch (Exception e){
			msg = "请求注册信息无效!";
			r = false;
			user = null;
		}
		
		if(user != null){
			if(user.getUserId() == null || "".equals(user.getUserId())){
				r = false;
				msg = "用户名为空!";
				return "{\"result\":"+(r?1:0)+",\"reason\":\""+msg+"\"}";
			}
			
			UserEntity ue = userService.queryUser(user.getUserId());
			if(ue != null){
				r = false;
				msg = "该账号已经注册过!";
				return "{\"result\":"+(r?1:0)+",\"reason\":\""+msg+"\"}";
			}
			
			if(user.getPassword() == null || "".equals(user.getPassword())){
				r = false;
				msg = "密码为空!";
				return "{\"result\":"+(r?1:0)+",\"reason\":\""+msg+"\"}";
			}
			
			r = userService.registerUser(user);
			return "{\"result\":"+(r?1:0)+",\"reason\":\""+msg+"\"}";
		}else{
			return "{\"result\":"+(r?1:0)+",\"reason\":\""+msg+"\"}";
		}
	}
	
	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String login(String entity){
		logger.debug("请求登录:"+entity);
		UserEntity user = null;
		Response r = new Response();
		r.setResult(0);
		r.setUser(null);
		r.setReason("");
		try{
			user = JSONObject.parseObject(entity, UserEntity.class);
		}catch (Exception e){
			user = null;
			r.setResult(0);
			r.setReason("请求登录信息无效!");
		}
		
		if(user != null){
			if(user.getUserId() == null || "".equals(user.getUserId()) || user.getPassword() == null || "".equals(user.getPassword())){
				r.setResult(0);
				r.setReason("用户名或密码为空!");
				
			}else{
				UserEntity ue = userService.queryUser(user.getUserId());
				if(ue != null){
					if(ue.getPassword().equals(user.getPassword())){
						r.setResult(1);
						r.setReason("登录成功!");
						r.setUser(ue);
					}else{
						r.setResult(0);
						r.setReason("用户名或密码错误!");
					}
				}else{
					r.setResult(0);
					r.setReason("当前账号不存在!");
				}
			}
			
		}
			
		return JSONObject.toJSONString(r);
	}
	
	class Response{
		private int result;
		private String reason;
		private UserEntity user;
		public int getResult() {
			return result;
		}
		public void setResult(int result) {
			this.result = result;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public UserEntity getUser() {
			return user;
		}
		public void setUser(UserEntity user) {
			this.user = user;
		}
	}

}
