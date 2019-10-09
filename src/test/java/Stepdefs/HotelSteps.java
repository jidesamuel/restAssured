package Stepdefs;

import ResponseClass.BookingHeader;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class HotelSteps {

    private String request;
    private String create;
    private String secureKey;
    private String secureApi;
    BookingHeader response;
    private String api = "authorization", key = "Basic YWRtaW46cGFzc3dvcmQxMjM=";
    File file = new File("src/test/resources/payload.json");

    @Given("I am on booking site (.*)")
    public void iAmOnBookingSiteBaseUrl(String baseUrl) {
        request = baseUrl;
    }

    @When("I supply the (.*), (.*)")
    public void iSupplyTheApiKey(String api, String key) {
        secureApi = api;
        secureKey = key;
    }

    @And("I (.*) a booking")
    public void iCreateABooking(String createEndPoint) {
        create = createEndPoint;
        response = given().body(file).when().
                contentType("application/json").
                header(secureApi,secureKey).
                post(request +"/"+ createEndPoint).then().
        extract().body().as(BookingHeader.class);
    }

    @Then("I am able to delete the booking")
    public void iAmAbleToDeleteTheBooking() {
        int bookingId = response.getBookingid();
        given().when().header(api, key).delete(request +"/"+ create+"/"+bookingId);
    }

}
