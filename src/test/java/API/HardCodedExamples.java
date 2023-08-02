package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.devtools.v111.fetch.model.AuthChallengeResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    //baseURI = baseURL + endpoint
    //given -preparation
    //when-hitting the endpoint
    //base URI=base URL

    String baseURI=RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";

    //value of token should be the same
    String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTA5OTM4NjMsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY5MTAzNzA2MywidXNlcklkIjoiNTYzMCJ9.3FRJGxJMvK9N_XPAL3QFcu4mS0st1b7eXaKcwRxfaus";
     static String employee_id;
    //in this method we are going to create an employee. we need header, body to prepare the request
    @Test
    public void acreateEmployee(){
        //prepare the request
      RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "  \"emp_firstname\": \"Lesia\",\n" +
                        "  \"emp_lastname\": \"Firadi\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"1987-05-20\",\n" +
                        "  \"emp_status\": \"happy\",\n" +
                        "  \"emp_job_title\": \"QA\"\n" +
                        "}");
    //hitting the endpoint
       Response response=request.when().post("/createEmployee.php");
    //verifying the response
        response.then().assertThat().statusCode(201);
    //System.out.println(response);
    //to print the response of API in console
        response.prettyPrint();

    //verify all the values and headers from response
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Lesia"));
        response.then().assertThat().body("Employee.emp_middle_name", equalTo("ms"));
        response.then().assertThat().body("Employee.emp_lastname", equalTo("Firadi"));
        response.then().assertThat().header("X-Powered-By", equalTo("PHP/7.2.18"));
        response.then().assertThat().body("Message", equalTo("Employee Created"));

        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
    }

    @Test
    public void bgetCreatedEmployee(){
        RequestSpecification request=given().header("Authorization", token).queryParam("employee_id", employee_id);
        Response response=request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        String tempEmpId=response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(employee_id, tempEmpId);
    }

    @Test
    public void cUpdateEmployee(){
        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                "        \"employee_id\": \""+employee_id+"\",\n" +
                "        \"emp_firstname\": \"LesiaSofiVer\",\n" +
                "        \"emp_middle_name\": \"yes\",\n" +
                "        \"emp_lastname\": \"Dirana\",\n" +
                "        \"emp_birthday\": \"2013-10-01\",\n" +
                "        \"emp_gender\": \"F\",\n" +
                "        \"emp_job_title\": \"Family\",\n" +
                "        \"emp_status\": \"Happy\"\n" +
                "}");

        Response response=request.when().put("/updateEmployee.php");
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("Message", equalTo("Employee record Updated"));
        response.prettyPrint();
    }

    @Test
    public void dgetUpdatedEmployee(){
        RequestSpecification request=given().header("Authorization", token).queryParam("employee_id", employee_id);
        Response response=request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        String tempEmpId=response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(employee_id, tempEmpId);
    }

    @Test
    public void ePartiallyUpdate(){
        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "        \"employee_id\": \""+employee_id+"\",\n" +
                        "        \"emp_middle_name\": \"Yushchysh\"\n" +
                        " }");
        Response response=request.when().patch("/updatePartialEmplyeesDetails.php");
        response.then().assertThat().body("Message", equalTo("Employee record updated successfully"));
        response.then().assertThat().header("Server", equalTo("Apache/2.4.39 (Win64) PHP/7.2.18"));
        String actualId=response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(employee_id, actualId);
    }

    @Test
    public void fgetPartiallyUpdatedEmployee(){
        RequestSpecification request=given().header("Authorization", token).queryParam("employee_id", employee_id);
        Response response=request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        String tempEmpId=response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(employee_id, tempEmpId);
    }
}
