package put_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */

    @Test
    public void put01(){
        //1.Step : set the url
        spec.pathParams("first","todos","second",198);

        //2. step : set the expected data
        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();
        Map<String ,Object> expectedDataMap =  expectedData.expectedDataWithAllKeys(21,"Wash the dishes",false);

        //3.step : send put request and get the response
        Response response =  given().spec(spec).contentType(ContentType.JSON). body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();


        //4. step : Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedDataMap.get("userId"),actualData.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualData.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualData.get("completed"));


    }



}
