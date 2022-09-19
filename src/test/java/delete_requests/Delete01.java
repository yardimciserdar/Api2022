package delete_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */


    @Test

    public void delete01() {
        // 1. step : set the url
        spec.pathParams("first","todos","second",198);

        //2. step : set the expected data
        Map<String,Object> expectedDataMap = new HashMap<>();

        //3. step : send DELETE request and get the response

        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        //4. step : Do Assertion

        //1.yol
        Map<String,Object> actualMap =  response .as(HashMap.class);

        response.then().assertThat().statusCode(200);
        assertEquals(actualMap,expectedDataMap);

        //2.yol
        assertTrue(actualMap.size()==0);
        assertTrue(actualMap.isEmpty()); // tavsiye edilen yöntem

        //Delete request yapmadan once "Post Request" yapıp bu  datayı silmeliyiz
        // cunku başakalarının bilgilerini silmiş oluruz

    }


}
