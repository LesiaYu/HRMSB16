package APIStepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class   APIWorkFlowSteps {
    RequestSpecification request;
    Response response;
    public static String employee_id;
    public static String employee_Name;

    @Given("a request is prepared for creating an employee")
    public void a_request_is_prepared_for_creating_an_employee() {
        /* request = given().header("Content-Type", "application/json").
                header("Authorization", GenerateTokenStep.token).body("{\n" +
                        "  \"emp_firstname\": \"Lesia\",\n" +
                        "  \"emp_lastname\": \"Firadi\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"1987-05-20\",\n" +
                        "  \"emp_status\": \"happy\",\n" +
                        "  \"emp_job_title\": \"QA\"\n" +
                        "}");*/

        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token).
                body(APIPayloadConstants.createEmployeePayload());
    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);
        response.prettyPrint();
    }

    @Then("the status code for creating qn employee is {int}")
    public void the_status_code_for_creating_qn_employee_is(int statusCod) {
        response.then().assertThat().statusCode(statusCod);
    }

    @Then("the employee created contains key {string} and value {string}")
    public void the_employee_created_contains_key_and_value(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }

    @Then("the employee id {string} is stored as a global variable")
    public void the_employee_id_is_stored_as_a_global_variable(String empID) {
        employee_id = response.jsonPath().getString(empID);
        System.out.println(employee_id);
    }

    //-----------------------------------------------------------------------------------
    @Given("a request is prepared for retrieving an employee")
    public void a_request_is_prepared_for_retrieving_an_employee() {
        request = given().header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token).
                queryParam("employee_id", employee_id);
    }

    @When("a Get call is made to retrieve the employee")
    public void a_get_call_is_made_to_retrieve_the_employee() {
        response = request.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);
    }

    @Then("the status code for this employee is {int}")
    public void the_status_code_for_this_employee_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee id {string} must match with globally stored employee id")
    public void the_employee_id_must_match_with_globally_stored_employee_id(String empID) {
        String temporaryEmpId = response.jsonPath().getString(empID);
        Assert.assertEquals(employee_id, temporaryEmpId);
    }

    @Then("this employee data at {string} object matches with the data used to create the employee")
    public void this_employee_data_at_object_matches_with_the_data_used_to_create_an_employee(String employeeObject, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        List<Map<String, String>> expectedData = dataTable.asMaps();  // from feature file
        Map<String, String> actualData = response.body().jsonPath().get(employeeObject); //get method to get from response

        for (Map<String, String> map : expectedData) {
            //to keep the order and to avoid duplicate
            Set<String> keys = map.keySet();
            for (String key : keys) {
                //from the key we will get value
                String expectedValue = map.get(key);
                String actualValue = actualData.get(key);
                Assert.assertEquals(expectedValue, actualValue);
            }
        }
    }

    @Given("a request is prepared for creating an employee using json payload")
    public void a_request_is_prepared_for_creating_an_employee_using_json_payload() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY,
                        APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token).
                body(APIPayloadConstants.createEmployeeJsonPayload());
    }

    @Given("a request is prepared for creating an employee with data {string}, {string},{string},{string},{string},{string},{string}")
    public void a_request_is_prepared_for_creating_an_employee_with_data
            (String fn, String ln,
             String mn, String gender,
             String dob, String status,
             String jobTitle) {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY,
                        APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token).
                body(APIPayloadConstants.createEmployeeJsonPayloadDynamic(fn, ln, mn, gender, dob, status, jobTitle));
    }
    //--------------------------------------------------------------

    @Given("a request is prepared for updating employee")
    public void a_request_is_prepared_for_updating_employee() {
        request = given().header(APIConstants.HEADER_AUTHORIZATION_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token).body(APIPayloadConstants.updateEmployeePayload(employee_id));
    }

    @When("a PUT call is made to update an employee")
    public void a_put_call_is_made_to_update_an_employee() {
        response = request.when().put(APIConstants.UPDATE_EMPLOYEE_URI);
        response.prettyPrint();
    }

    @Then("the status code for updating an employee is {int}")
    public void the_status_code_for_updating_an_employee_is(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("employee updated contains key {string} and value {string}")
    public void employee_updated_contains_key_and_value(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }

    @Given("a request is prepared for updating employee using json payload")
    public void a_request_is_prepared_for_updating_employee_using_json_payload() {
        request = given().header(APIConstants.HEADER_AUTHORIZATION_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token).body(APIPayloadConstants.updateEmployeeJsonPayload(employee_id));
    }

    @Given("a request is prepared for updating employee with data {string},{string},{string},{string},{string},{string},{string},{string}")
    public void a_request_is_prepared_for_updating_employee_with_data(String eId, String fn, String mn, String ln, String eb, String gender, String jT, String status) {
        request = given().header(APIConstants.HEADER_AUTHORIZATION_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token).body(APIPayloadConstants.updateEmployeeJsonPayloadDynamic(employee_id, fn, mn,ln,eb,gender,jT, status));
    }

    @Then("the employee name {string} is stored as a global variable")
    public void the_employee_name_is_stored_as_a_global_variable(String empName) {
        employee_Name = response.jsonPath().getString(empName);
        System.out.println(employee_Name);
    }

  }
