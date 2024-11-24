package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import api.Constants.UserModuleOperationProperties;
import api.Payload.User;
import api.Utilities.DataProviders;
import io.restassured.response.Response;
import log.LoggerLoad;

public class UserApiTestDataDriven {

	
	
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String userID,String userName,String fname,String lname,String userEmail,String pwd,String ph) throws JsonProcessingException {
		User userPayload=new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(userEmail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		//Response response=UserModuleOperation.createUser(userPayload);
		Response response=UserModuleOperationProperties.createUser(userPayload);
		LoggerLoad.info("userPayload java object for post is"+userPayload);     
		ObjectMapper objmaper=new ObjectMapper();												
		String JsonObj=objmaper.writerWithDefaultPrettyPrinter().writeValueAsString(userPayload);
		System.out.println("Json Object \n"+JsonObj);
		response.then().log().body();
		
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		LoggerLoad.info("I m in post user method");
	}
	
	@Test(priority=2,dataProvider="UserName",dataProviderClass=DataProviders.class)
	public void testGetUser(String UserName)
	{
		//Response response=UserModuleOperation.getUser(UserName);
		Response response=UserModuleOperationProperties.getUser(UserName);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
//	@Test(priority=3,dataProvider="UserName",dataProviderClass=DataProviders.class)
//	public void testPutUser(String UserName,String userID,String userName,String fname,String lname,String userEmail,String pwd,String ph) throws JsonProcessingException {
//		User userPayload=new User();
//		
//		//userPayload.setUsername(userName);
//		userPayload.setFirstName("asd");
//		
//		//Response response=UserModuleOperation.updateUser(UserName,userPayload);		
//		Response response=UserModuleOperationProperties.updateUser(UserName,userPayload);
//		String user=response.jsonPath().get(userName);
//		System.out.println("userPayload java object for post is"+userPayload);     
//		ObjectMapper objmaper=new ObjectMapper();												
//		String JsonObj=objmaper.writerWithDefaultPrettyPrinter().writeValueAsString(userPayload);
//		System.out.println("Json Object \n"+JsonObj);
//		response.then().log().body();
//		AssertJUnit.assertEquals(response.getStatusCode(), 200);
//		System.out.println("I m in post user method");
//	}
	
	@Test(priority=3,dataProvider="UserName",dataProviderClass=DataProviders.class)
	public void testDeleteUser(String UserName)
	{
		//Response response=UserModuleOperation.deleteUser(UserName);
		Response response=UserModuleOperationProperties.deleteUser(UserName);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
}
