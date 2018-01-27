import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;


public class TestService {

	
	private final String BASE_URL = "http://127.0.0.1:8080/api/test";
//	private final String BASE_URL = "http://www.iever.cn:8080/ms/api/user";
	
	public void get(int size){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);
		WebTarget postTarget = target.path("all");
		
		
		Response response;
		response = postTarget.queryParam("size", size).request().get();
		System.out.println(response.readEntity(String.class));
		response.close();
		System.out.println(response.toString());
	}
	
	
	public static void main(String args[]){
		TestService userService = new TestService();
		userService.get(90000);
	}

}
