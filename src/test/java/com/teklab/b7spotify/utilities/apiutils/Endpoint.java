package com.teklab.b7spotify.utilities.apiutils;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;

import com.teklab.b7spotify.utilities.reportManagers.Log4jManager;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseOptions;
import io.restassured.specification.RequestSpecification;


public class Endpoint {

	
	
	private RequestSpecification requestSpecification;
	private Response response;
	private String endpointUrl;
	
	public enum RequstMethod{
		GET,POST,PUT,DELETE
	}
	
	public Endpoint() {
		requestSpecification =  given().contentType(ContentType.JSON);
	}
	
	public RequestSpecification setHeader() {
		//String token = "<your api key goes here>";
		//String tokenKey = "X-Ahth-Token;
		//Header header = new Header(tokenKey,token);
		
		return requestSpecification;
	}
	
	public RequestSpecification setRequestBody(JSONObject requestParams) {
		return requestSpecification.body(requestParams.toJSONString());
	}
	
	public RequestSpecification sendRequest(String endpointUrl,RequstMethod requestMethod) {
		try {
			String baseAPIUrl = "https://reqres.in";
			this.endpointUrl = String.format("%s$s", baseAPIUrl,endpointUrl);
			URL endpointAPIUrl = null;
			
			endpointAPIUrl = new URL(this.endpointUrl);
			
			switch(requestMethod) {
			case GET:
				response = requestSpecification.when().get(endpointAPIUrl);
				break;
			case POST:
				response = requestSpecification.when().post(endpointAPIUrl);
				break;
			case PUT:
				response =requestSpecification.when().put(endpointAPIUrl);
				break;
			case DELETE:
				response = requestSpecification.when().delete(endpointAPIUrl);
				break;
			}
		}catch(MalformedURLException e) {
			Log4jManager.error("Error occured when sending api request");
			e.printStackTrace();
		}
		return requestSpecification;
	}
	
	public ValidatableResponse readResponse() {
		return response.then();
	}
	public Response getResponse() {
		return response;
	}
	public String getEndpointUrl() {
		return endpointUrl;
	}
	//Assertion methods
	public void verifySatusCode(int code) {
		readResponse()
		.assertThat()
		.statusCode(code);
	}
}
