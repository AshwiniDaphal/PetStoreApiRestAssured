package api.Constants;
import static io.restassured.RestAssured.given;
import api.Payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserModuleOperation {

	
	public static Response createUser(User payload)
	{
		Response response=given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body(payload)
						.when()
						.post(Constants.CREATEUSER_ENDPOINT);
		return response;
	}
	
	public static Response getUser(String userName)
	{
		Response response=given()
							.pathParam("username", userName)
						.when()
						.get(Constants.GETUSER_ENDPOINT);
		return response;
	}
	public static Response updateUser(String userName,User payload)
	{
		Response response=given()
							.pathParam("username", userName)
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body(payload)
						.when()
						.put(Constants.UPDATEUSER_ENDPOINT);
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response=given()
							.pathParam("username", userName)
						.when()
						.delete(Constants.DELETEUSER_ENDPOINT);
		return response;
	}
	
	
}
