package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {

    /*
    1)postman manuel API testi icin kullanilir
    2)API  otomasyon testi icin Rest-Assured library kullanıyoruz.
    3) otomasyon kodalrının yazımı icin şu adımalrı izliyoruz
       a)Gereksinimleri anlama
       b)Testcase'i yazma
         i)Test case yazımı için 'Gherkin Language' kullanıyoruz
            x) Given : ön koşullar icin
            y) When : Aksiyonlar
            z) Then : Dönütler --> dogrulama,response, . . .
            t) And : çoklu
       c)Testing kodunun yazımı
           i) set the Url --> url eklenmesi
           ii) set the expected data (Post-Put-Patch)
           iii) Type code to send request
           iv) Do assertion
     */

    /* test caseler burada aşağıda
    Given
            https://restful-booker.herokuapp.com/booking/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void get01(){

        // i) set the Url --> url eklenmesi
        String url = "https://restful-booker.herokuapp.com/booking/55";

        // ii) set the expected data (Post-Put-Patch)
                // get request yapacağımız için bu adımı atlıyoruz

        // iii) Type code to send request
        Response response = given().when().get(url);

        response.prettyPrint();

        // iv) Do assertion
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        //Status code nasıl yazdırılır

        System.out.println("status code : " + response.statusCode());

        //Content type nasıl yazdırılır
        System.out.println("content type : " + response.contentType());

        //Status line nasıl yazdırılır
        System.out.println("status line : " + response.statusLine());

        // Header nasıl yazdırlır

        System.out.println(response.header("Server"));

        System.out.println("Headers : \n" + response.headers());

        //Time naısl yazdırlır

        System.out.println(" Time " + response.getTime());

    }



}
