import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alibaba.fastjson.JSONObject;


public class MusicStoryService {
	
	private final String BASE_URL = "http://127.0.0.1:8080/api/ms";
	//http://www.iever.cn:8080/ms/api/tms
//	private final String BASE_URL = "http://www.iever.cn:8080/ms/api/ms";
	
	public void addMusicStory(){
		MusicStoryEntity mse = new MusicStoryEntity();
		mse.setBackground(123);
		mse.setCreatedAt(new Date());
		mse.setIrc("山下的花儿ddddddddd不再开");
		mse.setMotion("无感");
		mse.setPlace("长沙");
		mse.setUser(new UserEntity());
		mse.getUser().setId(2);
		
		String r = JSONObject.toJSONString(mse);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);
		WebTarget postTarget = target.path("add");
		
		Response response;
		response = postTarget.request().buildPost(Entity.entity(r, MediaType.APPLICATION_JSON))
				.invoke();
		System.out.println(response.readEntity(String.class));
		response.close();
		System.out.println(response.toString());
	}
	
	public void queryBypage(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);
		WebTarget listTraget = target.path("list");
		Response response = listTraget.queryParam("id", "0").queryParam("size", "2").queryParam("user_id", 1).request().get();
		String str = response.readEntity(String.class);
		System.out.println(str);
		response.close();
	}

	
	public static void main(String args[]){
		MusicStoryService service = new MusicStoryService();
//		service.addMusicStory();
		service.queryBypage();
	}

}
