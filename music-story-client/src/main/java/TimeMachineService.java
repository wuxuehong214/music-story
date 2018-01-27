import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alibaba.fastjson.JSONObject;


/**
 * 
 * @author Administrator
 *
 */
public class TimeMachineService {
	
	private final String BASE_URL = "http://127.0.0.1:8080/api/tms";
//	private final String BASE_URL = "http://www.iever.cn:8080/ms/api/tms";
	
	public TimeMachineService(){
		
	}
	
	public void queryBypage(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);
		WebTarget listTraget = target.path("list");
		Response response = listTraget.queryParam("id", "0").queryParam("size", "2").request().get();
		String str = response.readEntity(String.class);
		System.out.println(str);
		response.close();
	}
	
	public void addTimeMachine(){
		TimeMachineEntity tme = new TimeMachineEntity();
		
		tme.setCreatedAt(new Date());
		
		tme.setStory(new MusicStoryEntity());
		tme.getStory().setId(15);
		tme.getStory().setMusic("Çà»¨´É");
		tme.getStory().setSinger("ÖÜ½ÜÂ×");
		String r = JSONObject.toJSONString(tme);
		System.out.println("=======:"+r);
		
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
	
	
	public static void main(String args[]){
		TimeMachineService service = new TimeMachineService();
//		service.queryBypage();
		service.addTimeMachine();
	}
}
