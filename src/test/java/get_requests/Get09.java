package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {

     /*
        Given
            https://restful-booker.herokuapp.com/booking/91
        When
            I send GET Request to the url
        Then
            Response body should be like that;
      {
        "firstname": "Sally",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {                     burada bir map daha yapacağız . En içteki Map den başlarız
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
      }
     */

    @Test
    public void get01(){
        //1.Step : set the url
        spec.pathParams("first","booking","second",3750);

        //2. step : set the expected data
        Map<String , String > bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", "2018-01-01");
        bookingDatesMap.put("checkout", "2019-01-01");

        Map<String,Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put( "firstname", "Sally");
        expectedDataMap.put("lastname", "Brown" );
        expectedDataMap.put("totalprice", 111 );
        expectedDataMap.put( "depositpaid", true);
        expectedDataMap.put( "bookingdates",bookingDatesMap );
        expectedDataMap.put( "additionalneeds", "Breakfast" );

        System.out.println(expectedDataMap);


        //3.Step : Send the request amd get the response

       Response response =  given().spec(spec).when().get("/{first}/{second}");

       Map<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        // 4. step : Do assertion

        assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        assertEquals(expectedDataMap.get("additionalneeds"), actualDataMap.get("additionalneeds"));
        assertEquals( bookingDatesMap.get("checkin"),((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals( bookingDatesMap.get("checkout"),((Map)actualDataMap.get("bookingdates")).get("checkout"));

                    // Data tiplerinin uyuşmamamsı gibi sorunların çözümü için Map yapıyoruz









    }
}
