package com.wxh.battery.test;

import java.util.Date;
import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alibaba.fastjson.JSONObject;

public class BatteryInfoService {
	
//	private String URL = "http://127.0.0.1:8080/api/batteryinfo";
	private String URL = "http://www.iever.cn:8080/battery/api/batteryinfo";
	
	public void addBattery(BatteryInfoEntity battery){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL);
		WebTarget postTarget = target.path("info");
		
		String r = JSONObject.toJSONString(battery);
		Response response;
		response = postTarget.request().buildPost(Entity.entity(r, MediaType.APPLICATION_JSON))
				.invoke();
		System.out.println(response.readEntity(String.class));
		response.close();
		System.out.println(response.toString());
	}
	
	public void queryAddress(String serialNum){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL);
		WebTarget postTarget = target.path("getaddress/"+serialNum);
		
		Response response;
		response = postTarget.request().buildGet()
				.invoke();
		System.out.println(response.readEntity(String.class));
		response.close();
		System.out.println(response.toString());
	}

	public static void main(String args[]){
		BatteryInfoService service = new BatteryInfoService();
		
//		BatteryInfoEntity battery = new BatteryInfoEntity();
//		battery.setBatteryChemID("7788");
//		battery.setBatteryName("绿色电池2");
//		battery.setExtInfo(new HashMap<String, Object>());
//		battery.setManufactureDate(new Date());
//		battery.setManufacturerName("生产厂商");
//		battery.setSerialNum("00110011");
//		battery.setSigleCellInfo(new HashMap<String, Object>());
//		
//		service.addBattery(battery);
		
		service.queryAddress("00110011");
	}
}
