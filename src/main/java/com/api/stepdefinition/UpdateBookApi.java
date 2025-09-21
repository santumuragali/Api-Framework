package com.api.stepdefinition;
import com.api.model.PostBook;
import com.api.model.PostBookingDate;
import com.api.utils.TestContext;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
public class UpdateBookApi {
	private TestContext context;
	private static final Logger LOG = LogManager.getLogger(UpdateBookApi.class);

	public UpdateBookApi(TestContext context) {
		this.context = context;
	}
	    int id = PostBookStepDefination.id;
		PostBook updateload = new PostBook();
		 Response response;
		 PostBook patchpayload;
		@Given("provide upadted payload request")
		public void provide_upadted_payload_request() {
			LOG.info("id stored from the update class"+id);
		   updateload.setFirstname("Annappa");
		   updateload.setLastname("soni");
		   updateload.setTotalprice(100);
		   updateload.setDepositpaid(false);
		   PostBookingDate bookingdates = new PostBookingDate();
		   bookingdates.setCheckin("2024-04-20");
		   bookingdates.setCheckOut("2025-03-30");
		   updateload.setBookingdates(bookingdates);
		   updateload.setAdditionalneeds("lunch");
		}

		@When("Hit the put type of request")
		public void hit_the_put_type_of_request() {
			response = given()
			        .pathParam("id", id)
			        .body(updateload)
			        .when()
			        .put("https://restful-booker.herokuapp.com/booking/{id}");	
		   LOG.info("rsponse update user "+response.asString());
		}
			
		@Then("validate status code and repsonse")
		public void validate_status_code_and_repsonse() {
			LOG.info("complete");
		 response.then().body("firstname",equalTo("Annappa"));
		}
		
		
		@Given("Provide small update payload")
		public void provid_small_update_payloda() {
			 patchpayload = new PostBook();
			patchpayload.setFirstname("Hare");
			patchpayload.setLastname("Krishna");
			}
		@When("hit the patch request")
		public void hit_the_patch_request() {
			response= given().header("Content-Type","application/json").pathParam("id",id).body(patchpayload)
					.when().patch("https://restful-booker.herokuapp.com/booking/{id}");
			LOG.info("	hit rewueste "+response.asString());
		}
		@Then("validate repsonse thorugh desrilization")
		public void validate_response_through_deserilization() {
			PostBook responsepost = response.as(PostBook.class);
			Assert.assertEquals(responsepost.getFirstname(),patchpayload.getFirstname());
			Assert.assertEquals(responsepost.getLastname(), patchpayload.getLastname());
			LOG.info("	e "+response);
		}
	}


