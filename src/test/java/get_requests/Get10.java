package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestBaseUrl {

     /*
        Given
            https://gorest.co.in/public/v1/users/2986
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
        {
         "meta": null,
         "data": {
             "id": 2986,
             "name": "Anunay Deshpande",
             "email": "anunay_deshpande@bogisich-mccullough.net",
             "gender": "male",
             "status": "active"
              }
        }
     */

    @Test
    public void get01() {
        //1. step : set the url
        spec.pathParams("first", "users", "second", 2986);

        //2. step : set the expected data
        Map<String, String> dataKeyMap = new HashMap<>();
        dataKeyMap.put("name", "Anunay Deshpande");
        dataKeyMap.put("email", "anunay_deshpande@bogisich-mccullough.net");
        dataKeyMap.put("gender", "male");
        dataKeyMap.put("status", "active");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("meta", null);
        expectedData.put("data", dataKeyMap);


        // 3. Step : Send the request and get the response

        Response response = given().spec(spec).when().get("/{first}/{second}");
        // de selerizisions
        Map<String, Object> actualDataMap = response.as(HashMap.class);

        // 4. step : Do assertion

        assertEquals(expectedData.get("meta"), actualDataMap.get("meta"));
        assertEquals(expectedData.get("name"), ((Map) actualDataMap.get("data")).get("name"));
        assertEquals(expectedData.get("email"), ((Map) actualDataMap.get("data")).get("email"));
        assertEquals(expectedData.get("gender"), ((Map) actualDataMap.get("data")).get("gender"));
        assertEquals(expectedData.get("status"), ((Map) actualDataMap.get("data")).get("status"));


    }

    @Test
    public void get02() {
        // 1. step : set the url

        spec.pathParams("first", "users", "second", 2986);

        // 2. Step : Set the expected data
        //inner Map
        GoRestTestData dataKey = new GoRestTestData();

        Map<String, String> dataKeyMap1 = dataKey.dataKeyMap("Anunay Deshpande", "anunay_deshpande@bogisich-mccullough.net", "male", "active");
        Map<String, Object> expectedData1 = dataKey.expectedDataMap(null, dataKeyMap1);

        // 3. Step : Send the request and get the response

        Response response = given().spec(spec).when().get("/{first}/{second}");
        // de selerizisions

        Map<String, Object> actualData = response.as(HashMap.class);

        // 4.Step : Do Assertion

        response.then().assertThat().statusCode(200);

        assertEquals(expectedData1.get("meta"),actualData.get("meta"));
        assertEquals(dataKeyMap1.get("name"),((Map)actualData.get("data")).get("name"));// once data elementine ulaşıp buradab aldığım objeyi map formatına cast yapılır ve çağrılır
        assertEquals(dataKeyMap1.get("email"),((Map)actualData.get("data")).get("email"));
        assertEquals(dataKeyMap1.get("gender"),((Map)actualData.get("data")).get("gender"));
        assertEquals(dataKeyMap1.get("status"),((Map)actualData.get("data")).get("status"));


    }
}


