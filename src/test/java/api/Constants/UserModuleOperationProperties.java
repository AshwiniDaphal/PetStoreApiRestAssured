package api.Constants;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.Payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserModuleOperationProperties {

	//getting url from properties file
	static ResourceBundle getURL(){
		ResourceBundle prop=ResourceBundle.getBundle("appurl");		//appurl is property file name
		return prop;
	}
	
	public static Response createUser(User payload)
	{
		String post_url=getURL().getString("CREATEUSER_URL");
		Response response=given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body(payload)
						.when()
						.post(post_url);
		return response;
	}
	
	public static Response getUser(String userName)
	{
		String get_url=getURL().getString("GETUSER_URL");
		Response response=given()
							.pathParam("username", userName)
						.when()
						.get(get_url);
		return response;
	}
//	public static Response updateUser(String userName,User payload)
//	{
//		String put_url=getURL().getString("UPDATEUSER_URL");
//		Response response=given()
//							.pathParam("username", userName)
//							.contentType(ContentType.JSON)
//							.accept(ContentType.JSON)
//							.body(payload)
//						.when()
//						.put(put_url);
//		return response;
//	}
	
	public static Response deleteUser(String userName)
	{
		String delete_url=getURL().getString("DELETEUSER_URL");
		Response response=given()
							.pathParam("username", userName)
						.when()
						.delete(delete_url);
		return response;
	}
	
	
}
