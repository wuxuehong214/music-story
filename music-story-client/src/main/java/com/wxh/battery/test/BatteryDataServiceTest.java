package com.wxh.battery.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxh.battery.ResponseEntity;

public class BatteryDataServiceTest {

//	private String URL = "http://127.0.0.1:8080/api/batterycache";
	private String URL = "http://124.232.154.54:8080/battery/api/batterycache";

	public void addBatteryDatas(List<BatteryDataEntity> datas) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL);
		WebTarget postTarget = target.path("cache");

		String r = JSONArray.toJSONString(datas);
		System.out.println("Datas:" + r);
		Response response;
		response = postTarget.request()
				.buildPost(Entity.entity(r, MediaType.APPLICATION_JSON))
				.invoke();
		System.out.println(response.readEntity(String.class));
		response.close();
		System.out.println(response.toString());
	}
	
	public void queryBatteryDatas(String serialNum,int size){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL);
		WebTarget postTarget = target.path(serialNum);
		Response response = postTarget.queryParam("size", size).request().buildGet().invoke();
//		System.out.println();
		String result = response.readEntity(String.class);
		ResponseEntity<?> resp =  JSONObject.parseObject(result,ResponseEntity.class);
		List<BatteryDataEntity> datas = JSONArray.parseArray(resp.getData().toString(), BatteryDataEntity.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(BatteryDataEntity d:datas){
//			System.out.println(JSONObject.toJSONString(d));
			byte[] b = d.getData();
			System.out.println();
			System.out.println("##########:"+b.length);
			System.out.println(sdf.format(d.getServerReadTime()));
//			System.out.println(Arrays.toString(d.getData()));
			System.out.println(HexByteStringUtil.byte2HexStr(b, b.length));
//			String validate = org.apache.commons.codec.binary.Base64.encodeBase64String(b);
//			System.out.println("validate1:"+validate);
//			String validate2 = Base64Util.getBase64(b);
//			System.out.println("validate2:"+validate2);
//			byte[] bs = Base64Util.getFromBase64(validate2);
//			System.out.println(HexByteStringUtil.byte2HexStr(bs, bs.length));
		}
		response.close();
		System.out.println(response.toString());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BatteryDataServiceTest service = new BatteryDataServiceTest();
//		List<BatteryDataEntity> datas = new ArrayList<BatteryDataEntity>();
//		Random r = new Random();
//		long recordCurcor = 9011;
//		for (int j = 0; j < 100; j++) {
//			datas.clear();
//			System.out.println("========:"+j);
//			for (int i = 0; i < 100; i++) {
//				BatteryDataEntity data = new BatteryDataEntity();
//				data.setClientReadTime(new Date());
//				byte b = (byte) r.nextInt(100);
//				data.setData(new byte[] { 0x01, 0x02, 0x03, 0x04, b,
//						(byte) (i + j) });
//				data.setDataLength(6);
//				data.setRecordCurcor(recordCurcor + i);
//				data.setRecordTime(new Date().getTime() + i);
//				data.setSerialNum("00110011");
//				datas.add(data);
//			}
//			service.addBatteryDatas(datas);
//		}
		
		service.queryBatteryDatas("688D727GP1", 10);
		
//		String str = "BQEBFBAAAFI+AAC2AQAAAAG3D7cPtw8uAC4AvQ++D8IPxA+9GgAAAKAAAABQDAAgpRkAABoAAAC/CAAg8AYAIA==";
//		byte[] b = Base64Util.getFromBase64(str);
//		System.out.println(HexByteStringUtil.byte2HexStr(b, b.length));
//		System.out.println(b.length);
//		
//		System.out.println("BQEBFBAAAFI+AAC2AQAAAAG3D7cPtw8uAC4AvQ++D8IPxA+9GgAAAKAAAABQDAAgpRkAABoAAAC/CAAg8AYAIA==".length());

	}

}
