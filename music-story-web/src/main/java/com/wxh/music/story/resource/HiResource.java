package com.wxh.music.story.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxh.music.story.entity.HIEntity;
import com.wxh.music.story.entity.ResultEntity;
import com.wxh.music.story.service.FriendService;
import com.wxh.music.story.service.HIService;

@Path("hi")
public class HiResource {

	private HIService service = new HIService();
	private FriendService friendService = new FriendService();
	private Logger logger = Logger.getLogger(HiResource.class);

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String put(String entity) {
		logger.info("Put hi entity:" + entity);
		HIEntity hi = null;
		try {
			hi = JSONObject.parseObject(entity, HIEntity.class);
		} catch (Exception e) {
		}

		ResultEntity result = new ResultEntity();
		if (hi != null) {
			boolean r = service.addHI(hi);
			result.setResult(r ? 1 : 0);
			result.setReason(r ? "" : "hi失败!");
		} else {
			result.setResult(0);
			result.setReason("HI请求协议解析失败!");
		}
		return JSONObject.toJSONString(result);
	}

	@GET
	@Path("{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String queryHIs(@PathParam("userid") int userid) {
		logger.info("Query user's his:" + userid);
		List<HIEntity> list = service.queryHIs(userid);
		return JSONArray.toJSONString(list);
	}

	@POST
	@Path("deal")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deal(String entity) {
		logger.info("Deal the hi!" + entity);

		JSONObject obj = JSONObject.parseObject(entity);
		int id = obj.getIntValue("id");
		int accept = obj.getIntValue("accept");

		ResultEntity r = new ResultEntity();
		r.setResult(1);
		HIEntity hi = service.queryHI(id);
		if (hi != null) {

			if (accept == 1) {
				// 保存朋友关系
				boolean rr = friendService.addFriend(hi.getUseridTo(),
						hi.getUseridFrom());
				if (!rr) {
					r.setReason("保存朋友关系失败!");
					r.setResult(0);
				} else {
					// 删除Hi
					boolean d = service.delHI(id);
					if (!d) {
						r.setReason("删除HI失败!");
						r.setResult(0);
					}
				}
			} else {
				boolean d = service.delHI(id);
				if (!d) {
					r.setReason("删除HI失败!");
					r.setResult(0);
				}
			}

		} else {
			r.setResult(0);
			r.setReason("当前HI不存在!");
		}
		return JSONObject.toJSONString(r);
	}

}
