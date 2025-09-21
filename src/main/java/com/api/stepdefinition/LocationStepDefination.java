package com.api.stepdefinition;
import com.api.model.MockApi;
import com.api.utils.TestContext;
import com.aventstack.extentreports.model.Log;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
public class LocationStepDefination {
	private TestContext context;
	private static final Logger LOG = LogManager.getLogger(CreateBookingStepdefinition.class);

	public LocationStepDefination(TestContext context) {
		this.context = context;
	}
	public static Response response;
	MockApi mockAPI;
	@Given("add place pay load")
	public void add_payload() {
		mockAPI = new MockApi("Annappa","Automation");			
	}
	@When("create user with post request")
	public void user_call_with_http_request() {
		RestAssured.baseURI="https://4c4c0f1b-46cf-4938-a158-656ffd4751fb.mock.pstmn.io";
		response=given().header("Content-Type","application/json").body(mockAPI).when().post();
	}
	@Then("validate api request is sucessfully created or not")
	public void validate_api_request() {
		response.then().statusCode(201).log().all();
		String id = response.jsonPath().getString("id");
		System.out.println("-------------------------------------------------");
		System.out.println(id);
	}
	@Then("validate schema response is matching")
	public void SchemaValidation() {
		response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/LoactionSchema.json"));
		System.out.println("succesfull complted ");
//		LOG.info("succesfull schmea validation is don");
	}
	@When("I send GET request to {string}")
	public void i_sent_request(String url) {
		response =given().when().get("http://localhost:8080"+url);
	}
	@Then("the response status code should be {int}")
	public void status_code(int code) {
		response.then().assertThat().statusCode(code);
		LOG.info("status code validated succesfully "+code);
	}
	  @Then("the response should contain {string}")
	    public void validate_response_body(String value) {
	       response.then().body(containsString(value));
	       LOG.info("The give value is Present "+value);
	    }
}
