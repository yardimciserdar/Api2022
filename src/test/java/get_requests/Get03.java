package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get03 extends HerOkuAppBaseUrl {

    /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
		And
		    Response format should be “application/json”
		And
		    “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
		And
		    “completed” is false
		And
		    “userId” is 2
     */

    @Test
    public void get01(){
        //1. Step: Set the Url
        // String url = "https://jsonplaceholder.typicode.com/todos/23"; //Önerilmiyor.
        spec.pathParams("first","todos","second", 1005);

        //2. Step: Set the expected data (post ve putta yapılacak)

        //3. Step: Send the Request and get the Response

        Response response = given().spec(spec).when().get("/{first}/{second}");

        //4. Step : Do Assertion

        // 1. yol

        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed",equalTo("false")).
                body("userId",equalTo(2));

        // 2. yol tek body içinde yazılan kodşar daha temiz oluyor.

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false),
                        "userId", equalTo(2));


    }
    /*
    NOT 1 : Assertion yaparken java calışmayı durduğunda hata sonrası kodlar calışmaz
            boylece hata sonrası asseertion lar hakkında bilgi sahibi olamayız
            fakat hata oncesi assertrion lar geçmiştir

    NOT 2 :  Eğer kodumuzu hata noktasında duracak sekilde yazarsak hard assertion yapmış oluyoruz

    NOT 3 :  Eger kodumuz hata sonrasında durmayacak şekilde yazarsak soft assertion yapmış oluyoruz

    NOT 4 :  Eğer coklu body method içinde yaparsak hard assertion yapmış oluyoruz
             ama tek body method içinde yaparsakta soft assertion yapmış oluruz.
     */

}
