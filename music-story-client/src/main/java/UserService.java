import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alibaba.fastjson.JSON;



public class UserService {
	
//	private final String BASE_URL = "http://127.0.0.1:8080/api/user";
	private final String BASE_URL = "http://www.iever.cn:8080/ms/api/user";
	
	public void clear(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);
		WebTarget postTarget = target.path("clear/2");
		
		
		Response response;
		response = postTarget.request().buildDelete()
				.invoke();
		System.out.println(response.readEntity(String.class));
		response.close();
		System.out.println(response.toString());
	}
	
	public void addUser(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);
		WebTarget postTarget = target.path("register");
		
		UserEntity u = new UserEntity();
		u.setUserId("15802540365");
		u.setPassword("123456");
		u.setSex(1);
		Response response;
		response = postTarget.request().buildPost(Entity.entity(JSON.toJSONString(u), MediaType.APPLICATION_JSON))
				.invoke();
		System.out.println(response.readEntity(String.class));
		response.close();
		System.out.println(response.toString());
	}
	
	public void login(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);
		WebTarget postTarget = target.path("login");
		
		UserEntity u = new UserEntity();
		u.setUserId("15802540365");
		u.setPassword("123456");
		Response response;
		response = postTarget.request().buildPost(Entity.entity(JSON.toJSONString(u), MediaType.APPLICATION_JSON))
				.invoke();
		System.out.println(response.readEntity(String.class));
		response.close();
		System.out.println(response.toString());
	}
	
	public static void main(String args[]){
		UserService userService = new UserService();
//		userService.clear();
//		userService.addUser();
		userService.login();
	}

}
