import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alibaba.fastjson.JSONObject;


public class HIService {
	
	private final String BASE_URL = "http://127.0.0.1:8080/api/hi";
//	private final String BASE_URL = "http://www.iever.cn:8080/ms/api/hi";
	
	public void putHi(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);
		WebTarget listTraget = target.path("add");
		
		HIEntity hi = new HIEntity();
		hi.setUseridFrom(1);
		hi.setUseridTo(2);
		hi.setMsIds(new ArrayList<Integer>());
		hi.getMsIds().add(1);
		hi.getMsIds().add(2);
		
		String r = JSONObject.toJSONString(hi);
		System.out.println(r);
		Response response = listTraget.request().buildPost(Entity.entity(r, MediaType.APPLICATION_JSON))
				.invoke();
		String str = response.readEntity(String.class);
		System.out.println(str);
		response.close();
	}
	
	public void query(int userid){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);
		WebTarget listTraget = target.path(""+userid);
		
		Response response = listTraget.request().buildGet().invoke();
		String str = response.readEntity(String.class);
		System.out.println(str);
		response.close();
	}
	
	public void deal(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);
		WebTarget listTraget = target.path("deal");
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("id", 1);
		map.put("accept", 1);
		
		String r = JSONObject.toJSONString(map);
		System.out.println(r);
		Response response = listTraget.request().buildPost(Entity.entity(r, MediaType.APPLICATION_JSON))
				.invoke();
		String str = response.readEntity(String.class);
		System.out.println(str);
		response.close();
	}
	
	public static void main(String args[]){
		HIService service = new HIService();
//		service.putHi();
//		service.query(2);
		service.deal();
	}

}
