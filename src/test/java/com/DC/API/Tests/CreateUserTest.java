package com.DC.API.Tests;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.DC.API.base.TestBase;
import com.DC.API.base.TestData;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;





public class CreateUserTest {
	

	
	@Test(enabled = true)
	public void createNewUser() throws Exception {

		Response response = expect().statusCode(200).given().header("authorization", "Bearer " + TestBase.bearerToken)
				.accept(ContentType.JSON).contentType(ContentType.JSON).and().body(TestData.createUser).when()
				.post(TestBase.post_baseURI);

		String responseBody = response.body().asString();
		System.out.println(responseBody);
		JsonPath jsonPath = new JsonPath(responseBody);

		int id = jsonPath.getInt("data.id");
		System.out.println(id);
		String nameInResponse = jsonPath.get("data.name");
		System.out.println(nameInResponse);
		TestBase.jsonFileWriter(responseBody);
		JSONObject userObject = TestBase.jsonFileReader();

		String ub = userObject.toJSONString();
		System.out.println(userObject.toJSONString());
		JsonPath jPath = new JsonPath(ub);

		int userid = jPath.getInt("data.id");
		System.out.println(userid);
		String nameInJsonFile = jPath.get("data.name");

		System.out.println(nameInJsonFile);
		Assert.assertEquals(nameInJsonFile, nameInResponse);

	}

@Test(dependsOnMethods = "createNewUser",enabled = true)
public void getNewUser() throws Exception {
	
	JSONObject userObject=    TestBase.jsonFileReader();
    
    String ub=userObject.toJSONString();
    System.out.println(userObject.toJSONString());
    JsonPath jPath = new JsonPath(ub);
    int  userid = jPath.getInt("data.id");
    System.out.println(userid);
    String  userName=jPath.get("data.name");

	Response response =expect().statusCode(200).given().header("authorization", "Bearer " + TestBase.bearerToken).when()
			.get(TestBase.baseURI+"/"+userid);
	String responseBody = response.body().asString();
	System.out.println(responseBody);
	JsonPath jsonPath = new JsonPath(responseBody);
	 String nameInResponse=jsonPath.get("name");
	 Assert.assertEquals(userName, nameInResponse);

}

@Test(dependsOnMethods = "getNewUser",enabled = true)
public void updateUser() throws Exception {
	JSONObject userObject=    TestBase.jsonFileReader();
	String ub=userObject.toJSONString();
    System.out.println(userObject.toJSONString());
    JsonPath jPath = new JsonPath(ub);
    int  userid = jPath.getInt("data.id");
    System.out.println(userid);
    String  userName=jPath.get("data.name");
    System.out.println(userName);

	Response response=expect().given().header("authorization", "Bearer " + TestBase.bearerToken).accept(ContentType.JSON)
	.contentType(ContentType.JSON).and().body(TestData.updateUser).when()
	.patch(TestBase.baseURI+"/"+userid);

	String responseBody = response.body().asString();
	System.out.println(responseBody);
	JsonPath jsonPath = new JsonPath(responseBody);
	 String nameInResponse=jsonPath.get("name");
	 Assert.assertNotSame(userName, nameInResponse);


}


@Test(dependsOnMethods = "updateUser",enabled = true)
public void deleteUser() throws Exception {
	
	JSONObject userObject=    TestBase.jsonFileReader();
	String ub=userObject.toJSONString();
    System.out.println(userObject.toJSONString());
    JsonPath jPath = new JsonPath(ub);
    int  userid = jPath.getInt("data.id");
    System.out.println(userid);
  

	given().header("authorization", "Bearer " + TestBase.bearerToken).when()
	.delete(TestBase.baseURI+"/"+userid).then().statusCode(204);
	



}
	

}
