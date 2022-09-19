package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
     /*
        Given
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
        https://gorest.co.in/public/v1/users/2226
            Response body should be like
        {
    "meta": null,
    "data": {
        "id": 2226,
        "name": "Kartik Mehrotra",
        "email": "mehrotra_kartik@dietrich.com",
        "gender": "female",
        "status": "inactive"
    }
}
    */

    @Test
    public void getPojo01(){
        //1. step : set the url
        spec.pathParams("first","users","second",2226);

        //2. step : set the expected data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo(2226,"Kartik Mehrotra","mehrotra_kartik@dietrich.com","female","inactive");
        GoRestResponseBodyPojo goRestResponseBodyPojo = new GoRestResponseBodyPojo(null,goRestDataPojo);

        //3.Step send the get request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //4.Step :Do Assertion
        GoRestResponseBodyPojo actualPojo = response.as(GoRestResponseBodyPojo.class);

        assertEquals(200,response.getStatusCode());

        assertEquals(goRestResponseBodyPojo.getMeta(),actualPojo.getMeta());
        assertEquals(goRestResponseBodyPojo.getData().getId(),actualPojo.getData().getId());
        assertEquals(goRestResponseBodyPojo.getData().getName(),actualPojo.getData().getName());
        assertEquals(goRestResponseBodyPojo.getData().getEmail(),actualPojo.getData().getEmail());
        assertEquals(goRestResponseBodyPojo.getData().getGender(),actualPojo.getData().getGender());
        assertEquals(goRestResponseBodyPojo.getData().getStatus(),actualPojo.getData().getStatus());


    }


}
