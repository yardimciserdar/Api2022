package get_requests;

import base_urls.DummyRestApiUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get16 extends DummyRestApiUrl {
/*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Given
              https://dummy.restapiexample.com/api/v1/employees
           When
		 		I send GET Request to the URL
		   Then
		 		Status code is 200
		 	And
                There are 24 employees
            And
              "Tiger Nixon" and "Garrett Winters" are among the employees
           And
              The greatest age is 66
           And
             The name of the lowest age is "Tatyana Fitzpatrick"
            And
              Total salary of all employees is 6,644,770
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */

    @Test
    public void get01(){
        //1.Step sewt the url
        spec.pathParam("first","employees");

        //2.Step :set the expected data
        //3.Step : send the get request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).when().get("/{first}");
        response.prettyPrint();
        //4.Step : Do Assertion
        response.then().assertThat().
                statusCode(200).//i) Status code is 200
                body("data.id",hasSize(24),// ii) There are 24 employees
                "data.employee_name",
                hasItems("Tiger Nixon","Garrett Winters"));//iii) "Tiger Nixon" and "Garrett Winters" are among the employees



// iv) The greatest age is 66
        JsonPath json = response.jsonPath();

        List<Integer> ageList=json.getList("data.findAll{it.employee_age>0}.employee_age");//Groovy Language = Java temelli bir proglamlama dili
        System.out.println(ageList);
        Collections.sort(ageList);
        assertEquals(66,(int)ageList.get(ageList.size() - 1));//v) The greatest age is 66



        //  v) The name of the lowest age is "Tatyana Fitzpatrick"
        List<String> nameList=json.getList("data.findAll{it.employee_age=="+ageList.get(0)+"}.employee_name");
        System.out.println(nameList);
        assertEquals("Tatyana Fitzpatrick",nameList.get(0));



        //vi) Total salary of all employees is 6,644,770
        List<Integer> salaryList=json.getList("data.findAll{it.employee_age>0}.employee_salary");

       // 1. Yol
        int toplam=0;
        for (Integer w:salaryList) {
            toplam+=w;
        }
        System.out.println(toplam);

        assertEquals(6644770,toplam);

        // 2. Yol
       int sumLambda =  salaryList.stream().reduce(0,(t,u)->t+u);
        assertEquals(6644770,sumLambda);

        // 3. Yol
        int sumLambda2 = salaryList.stream().reduce(0,Math::addExact);
        assertEquals(6644770,sumLambda2);

    }





}
