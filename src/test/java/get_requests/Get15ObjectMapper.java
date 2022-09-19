package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15ObjectMapper  extends HerOkuAppBaseUrl {

    /*
    Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
               {
    "firstname": "Oliver",
    "lastname": "Smith",
    "totalprice": 100,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-07-18",
        "checkout": "2022-07-19"
    },
    "additionalneeds": "Breakfast"
}
     */


@Test
    public void get01(){
    // 1. step : set the url
    spec.pathParams("first","booking","second",22);


    // 2. step : set the expected data
    String expectedData = "{\n" +
            "    \"firstname\": \"Oliver\",\n" +
            "    \"lastname\": \"Smith\",\n" +
            "    \"totalprice\": 100,\n" +
            "    \"depositpaid\": true,\n" +
            "    \"bookingdates\": {\n" +
            "        \"checkin\": \"2022-07-18\",\n" +
            "        \"checkout\": \"2022-07-19\"\n" +
            "    },\n" +
            "    \"additionalneeds\": \"Breakfast\"\n" +
            "}";

    BookingPojo expectedDataPojo = JsonUtil.convertJsonToJavaObject(expectedData,BookingPojo.class);

    // 3. step : sent the get request and get the response
    Response response = given().spec(spec).when().get("/{first}/{second}");
    response.prettyPrint();

    // 4 . step : Do assertion
    BookingPojo actualDataPojo = JsonUtil.convertJsonToJavaObject(response.asString(),BookingPojo.class);

    assertEquals(200,response.getStatusCode());
    assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname());
    assertEquals(expectedDataPojo.getLastname(),actualDataPojo.getLastname());
    assertEquals(expectedDataPojo.getTotalprice(),actualDataPojo.getTotalprice());
    assertEquals(expectedDataPojo.getDepositpaid(),actualDataPojo.getDepositpaid());
    assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds());
    assertEquals(expectedDataPojo.getBookingdates().getCheckin(),actualDataPojo.getBookingdates().getCheckin());
    assertEquals(expectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout());


    // soft assert
    //1.Adım Softassert objesi oluşturma

    SoftAssert softAssert=new SoftAssert();

    //2.assertion yap
    softAssert.assertEquals(200,response.getStatusCode());
    softAssert.assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname());
    softAssert.assertEquals(expectedDataPojo.getLastname(),actualDataPojo.getLastname());
    softAssert.assertEquals(expectedDataPojo.getTotalprice(),actualDataPojo.getTotalprice());
    softAssert.assertEquals(expectedDataPojo.getDepositpaid(),actualDataPojo.getDepositpaid());
    softAssert.assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds());
    softAssert.assertEquals(expectedDataPojo.getBookingdates().getCheckin(),actualDataPojo.getBookingdates().getCheckin());
    softAssert.assertEquals(expectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout());

    //3.SoftassertAll methodunu çalıştır
    softAssert.assertAll();

}
}
