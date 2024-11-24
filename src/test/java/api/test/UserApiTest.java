package api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.Constants.UserModuleOperation;
import api.Payload.User;
import io.restassured.response.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class UserApiTest {
	User userPayload;
	Faker faker;
	
	@BeforeTest
	public void setData()
	{
		
	//using Java Faker 	to create a payload(request body) using fake data 
	 faker =new Faker();
	userPayload=new User();
	
	userPayload.setId(faker.idNumber().hashCode());
	userPayload.setUsername(faker.name().username());
	userPayload.setFirstName(faker.name().firstName());
	userPayload.setLastName(faker.name().lastName());
	userPayload.setEmail(faker.internet().safeEmailAddress());
	userPayload.setPassword(faker.internet().password(5,10));
	userPayload.setPhone(faker.phoneNumber().cellPhone());
	userPayload.setUserStatus(0);
	
	
	}
	
	
	@Test(priority=1)
	public void testPostUser() throws JsonProcessingException
	{
		System.out.println("I m in post user method");
		
		Response response=UserModuleOperation.createUser(userPayload);
		//userPayload is POJO means a java object so it print as a string format.
		//If we want this userPayload as Json object then use serialization method using ObjectMapper
		//which convert string to Json object.
		System.out.println("userPayload java object for post is"+userPayload);     
		ObjectMapper objmaper=new ObjectMapper();												
		String JsonObj=objmaper.writerWithDefaultPrettyPrinter().writeValueAsString(userPayload);
		System.out.println("Json Object \n"+JsonObj);
		response.then().log().body();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		System.out.println("I m in post user method");
	}
	
	
	@Test(priority=2)
	public void testgetUser()
	{
		System.out.println("I m in get user method");
		
		Response response=UserModuleOperation.getUser(this.userPayload.getUsername());
		response.then().log().body();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		System.out.println("I m in get user method");
	}
	
	@Test(priority=3)
	public void testPutUser() throws JsonProcessingException
	{
		System.out.println("I m in put user method");
		
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		System.out.println("Update userPayload java object is "+userPayload);
		ObjectMapper objmaper=new ObjectMapper();
		String JsonObj=objmaper.writerWithDefaultPrettyPrinter().writeValueAsString(userPayload);
		System.out.println("Json Object \n"+JsonObj);
		Response response=UserModuleOperation.updateUser(this.userPayload.getUsername(),userPayload);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		response.then().log().body().toString();
		System.out.println("I m in put user method");
		
		System.out.println("I m in get user method");
		Response response1=UserModuleOperation.getUser(this.userPayload.getUsername());
		response1.then().log().body();
		AssertJUnit.assertEquals(response1.getStatusCode(), 200);
		System.out.println("I m in get user method");
		
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		System.out.println("I m in delete user method");
		Response response=UserModuleOperation.getUser(this.userPayload.getUsername());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		System.out.println("I m in delete user method");
	}
	
}
