package get_requests;

import base_urls.DummyRestApiUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyDataPojo;
import pojos.DummyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get17 extends DummyRestApiUrl  {

    /*
       URL: https://dummy.restapiexample.com/api/v1/employee/1
       HTTP Request Method: GET Request
       Test Case: Type by using Gherkin Language
       Given
              https://dummy.restapiexample.com/api/v1/employee/1
           When
		 		I send GET Request to the URL
		   Then
		 		Status code is 200
		 	And
                "employee_name" is "Tiger Nixon"
            And
              "employee_salary" is 320800
           And
              "employee_age" is 61
           And
             "status" is "success"
            And
              "message" is "Successfully! Record has been fetched."
       Assert: 	i) Status code is 200
               ii) "employee_name" is "Tiger Nixon"
              iii) "employee_salary" is 320800
               iv)  "employee_age" is 61
                v) "status" is "success"
               vi)  "message" is "Successfully! Record has been fetched."
     */
    /*
    {
    "status": "success",
    "data": {
        "id": 1,
        "employee_name": "Tiger Nixon",
        "employee_salary": 320800,
        "employee_age": 61,
        "profile_image": ""
    },
    "message": "Successfully! Record has been fetched."
}
     */


    @Test
    public void get01(){
        //1. Step : Set the url
        spec.pathParams("first", "employee", "second","1");

        //2. Step : Set the expected Data
        DummyDataPojo dummyDataPojo = new DummyDataPojo("Tiger Nixon",320800,61,"");
        DummyPojo expectedPojo = new DummyPojo("success",dummyDataPojo,"Successfully! Record has been fetched.");

        //3. Step : Send the get request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //4. Step :
        DummyPojo actualPojo = JsonUtil.convertJsonToJavaObject(response.asString(),DummyPojo.class);


        assertEquals(expectedPojo.getStatus(),actualPojo.getStatus());
        assertEquals(expectedPojo.getData().getEmployee_name(),actualPojo.getData().getEmployee_name());
        assertEquals(expectedPojo.getData().getEmployee_salary(),actualPojo.getData().getEmployee_salary());
        assertEquals(expectedPojo.getData().getEmployee_age(),actualPojo.getData().getEmployee_age());
        assertEquals(expectedPojo.getStatus(),actualPojo.getStatus());
        assertEquals(expectedPojo.getMessage(),actualPojo.getMessage());




    }
}
