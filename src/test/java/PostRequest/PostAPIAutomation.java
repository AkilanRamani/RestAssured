package PostRequest;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostAPIAutomation {
	
	@Test
public void postTierDetails() {
		
		RestAssured.baseURI = "http://hqlinuxtest5:8181/RayMedi_HQ/rest/";
		
		
	 //creation of Request Object using RequestSpecification class 
		//given is a static method of RestAssured class which returns RequestSpecification object 
		RequestSpecification reqspec = RestAssured.given();
		
		//Creation of JSON Payloader which has to be passed along with the POST Request 
		JSONObject js = new JSONObject();
		js.put("tierStatus" , "A");
		js.put("tierName", "arunaaaaaakilan");
		js.put("tierId", 0);
		js.put("remarks", "testing");
		
		
		reqspec.header("Content-Type" ,"application/json");
	    reqspec.sessionId("JSESSIONID","265700E6A40FE21B8AFEC0F4A1132E10.jvm1" );
	
		reqspec.body(js.toJSONString());
		
		Response response = reqspec.request(Method.POST , "tier-master/save");
				
		String responsebody = response.getBody().asString();
		System.out.println("Response Body is :" +responsebody);
		
		 
		//status Code validation 
		int statuscoderesponse = response.getStatusCode();
		System.out.println("Status Code is :" + statuscoderesponse);
		Assert.assertEquals(200, statuscoderesponse);
		
		String successcode =response.jsonPath().get("status");
		Assert.assertEquals("success", successcode);
	}
	
				
				
	}
	





