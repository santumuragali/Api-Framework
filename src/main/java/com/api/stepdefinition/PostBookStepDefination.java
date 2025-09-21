package com.api.stepdefinition;
import static io.restassured.RestAssured.*;
import io.restassured.*;
import com.api.model.PostBook;
import com.api.model.PostBookingDate;
import com.api.utils.TestContext;

import io.restassured.response.*;
import static org.hamcrest.Matchers.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import io.cucumber.java.en.*;
public class PostBookStepDefination {
	
	private TestContext context;
	private static final Logger LOG = LogManager.getLogger(PostBookStepDefination.class);

	public PostBookStepDefination(TestContext context) {
		this.context = context;
	}
	public static int id;
	PostBook payload ;
	PostBookingDate bookingdates = new PostBookingDate();
	public Response response;
	@Given("Provide pay load for create user")
	public void requestBody() {
	payload = new PostBook();
	payload.setFirstname("santu");
	payload.setLastname("muragali");
	payload.setTotalprice(200);
	payload.setDepositpaid(true);
	bookingdates.setCheckin("2025-09-20");
	bookingdates.setCheckOut("2025-09-30");
	payload.setBookingdates(bookingdates);
	payload.setAdditionalneeds("breakfast");
	
	LOG.info("body request is"+payload);
	}
	
	@When("Hit the post request")
	public void Hit_request() {
	String url = "https://restful-booker.herokuapp.com/booking";
	response = given().header("Content-Type","application/json").body(payload).when().post(url);
	}
	
	@Then("Validate the statuscode {int}")
	public void validate_statucode(int statuscode) {
	response.then().assertThat().statusCode(statuscode).log().all();
	System.out.println("validation step --");
	}
	
	@Then("Validate all response body")
	public void validateallresponseBody() {
		id = response.jsonPath().getInt("bookingid");
		LOG.info("id stored from created book  "+id);
		response.then().body("booking.firstname",equalTo("santu"))
		.body("booking.lastname", equalTo("muragali"))
		.body("booking.totalprice",equalTo(200))
		.body("booking.depositpaid", equalTo(true))
		.body("booking.bookingdates.checkin", equalTo("2025-09-20"))
		.body("booking.bookingdates.checkout", equalTo("2025-09-30"))
		.body("booking.additionalneeds", equalTo("breakfast")).log().all();
		System.out.println("validation step -- complted");
	}
}
