package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {

     /*
        Given
            https://restful-booker.herokuapp.com/booking/1002
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
          {
            "firstname": "Jim",
            "lastname": "Brown",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            }
        }
     */

    @Test
    public void get01(){
        //1. Step: Set the Url

        spec.pathParams("first","booking","second","555");

        //2. Set the expected data

        //3. Step: Send the request and get the response

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4. Step: Do Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Sally"),
                        "lastname",equalTo("Brown"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                      "bookingdates.chechin",equalTo("2013-02-23"),
                        "bookingdates.checkout",equalTo("2014-10-23"));

        //2. Yol: JsonPath Class kullanılır

        JsonPath json = response.jsonPath();// responsu jsonPath formatına çevirip bir variabla atıyprum
                                            // sonrasında jsonPath araacılığı ile bir çok işlem yapabiliyorum.
        assertEquals("Sally", json.getString("firstname"));
        assertEquals("Brown", json.getString("lastname"));
        assertEquals(111, json.getInt("totalprice"));
        assertEquals(true, json.getBoolean("depositpaid"));
        assertEquals("2018-01-01", json.getString("bookingdates.checkin"));
        assertEquals("2019-01-01",json.getString("bookingdates.checkout"));

        //3. Yol: Soft Assertion
        //Soft Assertion için 3 adım izlenir;

        //1) SoftAssert Objesi oluşturulur.
        SoftAssert softAssert = new SoftAssert();

        //2) Obje aracılığı ile assert yapılır.

        softAssert.assertEquals(json.getString("firstname"), "Sally","firstname uyuşmadı");
        softAssert.assertEquals(json.getString("lastname"),"Brown","lastname uyuşmadı");
        softAssert.assertEquals(json.getInt("totalprice"),111,"totalprice uyuşmadı");
        softAssert.assertEquals(json.getBoolean("depositpaid"),true,"depositpaid uyuşmadı");
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2018-01-01","checkin uyuşmadı");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2019-01-01","checkout uyuşmadı");

        //3) assertAll() methodu kullanılır. Aksi taktirde kod her zaman pass olur.
        softAssert.assertAll();





    }
}
