package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04Pojo extends HerOkuAppBaseUrl {

    /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast with white tea, Dragon juice"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                                     }
                                  }
     */

    @Test
    public void postPojo01(){


            // 1. Step : set the url
        spec.pathParam("first","booking");

        //2. step : set the expected data
            BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2021-09-21","2021-12-21");
            BookingPojo bookingPojo = new BookingPojo("Ali","Can",999,true,bookingDatesPojo,"Breakfast with white tea, Dragon juice");

            // 3. step : send the post request and get the response

        Response response = given().spec(spec).contentType(ContentType.JSON).body(bookingPojo).when().post("/{first}");
        response.prettyPrint();

            //4. step : Do assertion

        BookingResponseBodyPojo actualPojo = response.as(BookingResponseBodyPojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(bookingPojo.getFirstname(),actualPojo.getBooking().getFirstname());
        assertEquals(bookingPojo.getLastname(),actualPojo.getBooking().getLastname());
        assertEquals(bookingPojo.getTotalprice(),actualPojo.getBooking().getTotalprice());
        assertEquals(bookingPojo.getDepositpaid(),actualPojo.getBooking().getDepositpaid());

        //1.yol
        assertEquals(bookingPojo.getBookingdates().getCheckin(),actualPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingPojo.getBookingdates().getCheckout(),actualPojo.getBooking().getBookingdates().getCheckout());

        assertEquals(bookingPojo.getAdditionalneeds(),actualPojo.getBooking().getAdditionalneeds());


        //2. yol
        assertEquals(bookingDatesPojo.getCheckin(),actualPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualPojo.getBooking().getBookingdates().getCheckout());



        }

    }



