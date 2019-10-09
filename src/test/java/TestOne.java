import ResponseClass.Booking;
import ResponseClass.BookingDates;
import ResponseClass.BookingHeader;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static io.restassured.RestAssured.given;

public class TestOne {

    private String baseUrl = "https://jsonplaceholder.typicode.com/";
    private Response response;
    public String id = "id", userid = "userId";

    private String path = ("src/test/resources/payload.json");
    private File file = new File(path);
    private String api = "authorization", key = "Basic YWRtaW46cGFzc3dvcmQxMjM=";
    private String url = "http://hotel-test.equalexperts.io";
    private String createEndPoint = "/booking";

    @Test
    public void testHeader() {

        Headers headers = given().when().get(baseUrl + "posts").getHeaders();
        int headerCount = headers.size();
        String listHeaders = headers.toString();

        System.out.println("Number of headers returned = " + headerCount + "\n"
                + "List of Headers as below" + "\n"
                + "========================" + "\n"
                + listHeaders);
    }

    @Test
    public void testCountKeys() {

        response = given().when().get(baseUrl + "posts");
        JsonPath jsonPath = response.jsonPath();
        ArrayList listOfUsers = jsonPath.get(userid);
        ArrayList listOfIds = jsonPath.get(id);
        System.out.println("Array list of Ids = " + listOfIds + "\n"
        +"Array number of Users " + listOfUsers.size());
    }


    @Test
    public void deleteRecord() throws IOException {

        Response response = given().
                body(file).when().
                headers(api, key).contentType("application/json").
                post(url+createEndPoint);

        System.out.println(response.body().asString());

        Response rest = given().when().headers(api, key).get(url+createEndPoint);
        System.out.println("GET Response = "+rest.body().asString());
        String bookingId = rest.body().asString().substring(14, 20);
        System.out.println("First booking id = "+bookingId);
        Assert.assertEquals(200, response.statusCode());

        given().when().headers(api, key).delete(url+createEndPoint+"/"+bookingId);
    }

    @Test
    public void createBookingByClass() throws InterruptedException {
        Booking booking = new Booking();
        BookingDates bookingDates = new BookingDates();
        BookingHeader bookingHeader = new BookingHeader();

        BookingHeader response = given()
                .body(file)
                .when()
                .contentType("application/json")
                .header(api, key)
                .post(url+createEndPoint).then().extract().body().as(BookingHeader.class);

        int bookingId = response.getBookingid();
        Thread.sleep(5000);
        // Delete post record via endpoint
        given().when().header(api, key).delete(url+createEndPoint+"/"+bookingId);
    }

}

