package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Aalok Acharya DDS", "Agastya Somayaji", "Acharyasuta Chattopadhyay DC" are among the users
        And
            The female users are more than or equals to male users
     */

    @Test
    public void get01(){
        //1.step: set teh url
        spec.pathParams("first", "users");

        //2. step : set the expected data

        // 3. step : send the request get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4. step : Do assertion


        //1. yol : body() methodu ile

        response.
                then().
                assertThat().
                statusCode(200).
                body("meta.pegination.limit",equalTo(10),
                        "meta.pegination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data.id",hasSize(10),
                        "data.status",hasItem("active"),
                        "data.name",hasItems("Deeksha Devar", "Deeptiman Khan", "Dharitri Nambeesan LLD"));


        // bayan ve erkek sayısını karşılaştıralım
        // 1 yol tum cinsiyetleri çekip bayan saysısı ile karşılaştırıldı

        JsonPath json = response.jsonPath();
        List<String> genders = json.getList("data.gender");
        System.out.println(genders);


        int numofFemales=0;
        int numofMales=0;
        for (String w : genders
             ) {
            if (w.equalsIgnoreCase("female")){
                numofFemales++;
            }else numofMales++;

            
        }

        System.out.println(numofFemales+" "+numofMales);
        assertTrue(numofFemales>=numofMales);


        //2 .YOL: tum bayan ve bay ları ayrı ayrı groovy ile çekelim
        List<String> femaleList =  json.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println(femaleList);

        List<String>maleList=json.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println(maleList);

        assertTrue(femaleList.size()>=maleList.size());

    }

}
