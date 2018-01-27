package com.wxh.music.story.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("test")
public class TextResource {
	
	@GET
	@Path("all")
	@Produces(MediaType.TEXT_PLAIN)
	public String clearResource(@QueryParam("size") int size){
		System.out.println("Request size:"+size);
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<size;i++){
			sb.append("这里就可以理解为什么大都把文件上传和业务接口分开来提供了吧。如果你的文件上传服务和业务接口是同一个机器的话，那么就说明你"+i);
			sb.append("\n");
		}
		float size2 = (float)(sb.toString().getBytes().length)/(1024*1024);
		System.out.println("size:"+size2+" MB");
		return sb.toString();
	}

}
