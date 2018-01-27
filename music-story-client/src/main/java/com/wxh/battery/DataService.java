package com.wxh.battery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

class DataService {
	
	private final String BASE_URL = "http://127.0.0.1:8080/api/data";
	//http://www.iever.cn:8080/ms/api/tms
//	private final String BASE_URL = "http://www.iever.cn:8080/ms/api/ms";
	
	public void addData(){
		
		List<DataEntity> datas = new ArrayList<DataEntity>();
		for(int i=0;i<100;i++){
			DataEntity data = new DataEntity();
			data.setCacheAddress(i+20000);
			data.setClientReadTime(new Date());
			data.setData(new byte[]{0x02,0x02,0x03,0x04,0x05,0x06,(byte)i});
			data.setDataLength(7);
			data.setRemark1("备注一222:"+i);
			data.setRemark2("备注二222:"+i);
			data.setSerialNum("battery4");
			data.setServerReadTime(new Date());
			datas.add(data);
		}
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);
		WebTarget postTarget = target.path("put");
		String arr = JSONArray.toJSONString(datas);
		
		Response response;
		response = postTarget.request().buildPost(Entity.entity(arr, MediaType.APPLICATION_JSON))
				.invoke();
		System.out.println(response.readEntity(String.class));
		response.close();
		System.out.println(response.toString());
	}

	public void query(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);
		WebTarget postTarget = target.path("battery4");
		Response response;
		response = postTarget.request().buildGet()
				.invoke();
		
		String s  = response.readEntity(String.class);
		response.close();
		System.out.println(s);
		ResponseEntity<?> resp =  JSONObject.parseObject(s,ResponseEntity.class);
		System.out.println(resp.getData());
		List<DataEntity> datas = JSONArray.parseArray(resp.getData().toString(), DataEntity.class);
		System.out.println(datas.size());
//		System.out.println(resp.getData().size());
		for(DataEntity data:datas){
			System.out.println(Arrays.toString(data.getData()));
			System.out.println(JSONObject.toJSONString(data));
			System.out.println();
		}
	}
	
	public static void main(String args[]){
		DataService service = new DataService();
		
//		service.addData();
		service.query();
	}
}
