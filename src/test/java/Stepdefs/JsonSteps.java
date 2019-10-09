package Stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;


public class JsonSteps {
    private String requestUrl;
    private String requestEndPoint;
    Response response;

    @Given("^I have Jsonplaceholder '(.*)'$")
    public void iHaveJsonplaceholder(String baseUrl) throws Throwable {
        requestUrl = baseUrl;
    }

    @And("^I have jsonplaceholderPost '(.*)'$")
    public void iHaveJsonplaceholderPost(String endpoint) throws Throwable {
        requestEndPoint = endpoint;
    }

    @When("^I make a get post request$")
    public void iMakeAGetPostRequest() {
        response = given().get(requestUrl+requestEndPoint);
    }

    @Then("^it should return title '(.*)'$")
    public void itShouldReturnTitle(String arg0) throws Throwable {
        JsonPath jsonPath = response.jsonPath();
        String Title = jsonPath.get("title");
        assertThat(Title, CoreMatchers.containsString(arg0));
    }

}
