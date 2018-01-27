package com.wxh.battery;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.BSONObject;
import org.bson.Document;

import com.alibaba.fastjson.JSONObject;

public class BatteryService {
	
	private final String BASE_URL = "http://127.0.0.1:8080/api/battery";
	//http://www.iever.cn:8080/ms/api/tms
//	private final String BASE_URL = "http://www.iever.cn:8080/ms/api/ms";
	
	public void addBattery(){
		BatteryEntity entity = new BatteryEntity();
		entity.setSerialNum("battery4");
		entity.setBatteryChemId("222222");
		entity.setBatteryName("电池");
		Map<String,Object> extInfo = new HashMap<String, Object>();
		extInfo.put("BatteryStatus", "warn");
		extInfo.put("BatterySOH", 8);
		extInfo.put("DesignCapacity", "10000008");
		extInfo.put("Address", 57841);
		extInfo.put("BatterySOC", 98);
		extInfo.put("dates", new Date());
		entity.setExtInfo(extInfo);
		entity.setManuFactureDate(new Date());
		entity.setManuFactureName("厂商名称");
		Map<String,Object> sigleCellInfo = new HashMap<String, Object>();
		entity.setSigleCellInfo(sigleCellInfo);
		
		
		String r = JSONObject.toJSONString(entity);
		
		System.out.println(r);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);
		WebTarget postTarget = target.path("put");
		
		Response response;
		response = postTarget.request().buildPost(Entity.entity(r, MediaType.APPLICATION_JSON))
				.invoke();
		System.out.println(response.readEntity(String.class));
		response.close();
		System.out.println(response.toString());
	}
	
	public void queryBattery(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);
		WebTarget listTraget = target.path("battery4");
		Response response = listTraget.request().get();
		String str = response.readEntity(String.class);
		System.out.println("##############:"+str);
		response.close();
	}
	
	public void test(){
		BatteryEntity entity = new BatteryEntity();
		entity.setSerialNum("battery3");
		entity.setBatteryChemId("222222");
		entity.setBatteryName("电池");
		Map<String,Object> extInfo = new HashMap<String, Object>();
		extInfo.put("BatteryStatus", "warn");
		extInfo.put("BatterySOH", 8);
		extInfo.put("DesignCapacity", "10000008");
		extInfo.put("Address", 57841);
		extInfo.put("BatterySOC", 98);
		entity.setExtInfo(extInfo);
		entity.setManuFactureDate(new Date());
		entity.setManuFactureName("厂商名称");
		Map<String,Object> sigleCellInfo = new HashMap<String, Object>();
		entity.setSigleCellInfo(sigleCellInfo);
		
		
		String json = JSONObject.toJSONString(entity);
		System.out.println(json);
		
		Document d = Document.parse(json);
		System.out.println(d.toJson());
	}

	
	public static void main(String args[]){
		BatteryService service = new BatteryService();
//		service.addBattery();
		service.queryBattery();
//		service.test();
	}

}
