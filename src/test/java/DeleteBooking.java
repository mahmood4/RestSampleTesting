import io.restassured.response.Response;
import org.testng.annotations.Test;
import utility.AllureLogger;
import utility.BaseTest;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class DeleteBooking extends BaseTest {

	public static Properties prop;
	@Test(description="To delete the details of the booking IDs") 
	public void getBookingIDs(){
		
		AllureLogger.logToAllure("Starting the test to delete booking details");
		/*******************************************************
		 * Send a DELETE request to /booking/{id}
		 * and check that the response has HTTP status code 200
		 ******************************************************/
		//To get the auth token
	//	String newAuthToken = AuthToken.post_CreateAuth();
	//	AllureLogger.logToAllure("Auth token is : "+newAuthToken);
	//	String cookieValue = "token="+newAuthToken;
	//
		//Created a new booking, this will be subsequently deleted
		CreateBooking createBooking = new CreateBooking();
		createBooking.createNewBooking("Jon", "114", "2018-01-05","null");

		String IDtoUpdate = createBooking.newID;
		AllureLogger.logToAllure("New Booking ID created is : "+IDtoUpdate);
		AllureLogger.logToAllure("Booking ID to be deleted is : "+IDtoUpdate);

		//Sending the GET request for a specific booking id and receiving the response
		AllureLogger.logToAllure("Sending the GET request for a specific booking id and receiving the response");
		Response response = given().
				spec(requestSpec).
				header("Content-Type", "application/json").
			//	header("Cookie", cookieValue).
				pathParam("id", IDtoUpdate).
			when().
				delete("/posts/{id}");
		
		//Verify the response code
		AllureLogger.logToAllure("Asserting the response if the status code returned is 201 as this is a Delete request");
		response.then().assertThat().statusCode(200);

		//To log the response to report
		logResponseAsString(response);	
		
	}
	@Test(enabled = true,description="To delete the details of the Exsiting booking IDs")
	public void getExistingBookingIDs() throws IOException {

		AllureLogger.logToAllure("Starting the test to delete booking details");

		prop = new Properties();

		prop.load(DeleteBooking.class.getClassLoader().getResourceAsStream("ApiParam.properties"));
		System.out.println("ID to Delete    "+prop.getProperty("IdToDel"));
		//String browserName=prop.getProperty("browserVal");

		/*******************************************************
		 * Send a DELETE request to /booking/{id}
		 * and check that the response has HTTP status code 200
		 ******************************************************/
		//To get the auth token
		//	String newAuthToken = AuthToken.post_CreateAuth();
		//	AllureLogger.logToAllure("Auth token is : "+newAuthToken);
		//	String cookieValue = "token="+newAuthToken;
		//
		//Sending the GET request for a specific booking id and receiving the response
		AllureLogger.logToAllure("Sending the GET request for a specific booking id and receiving the response");
		Response response = given().
				spec(requestSpec).
				header("Content-Type", "application/json").
				//	header("Cookie", cookieValue).
						pathParam("id", prop.getProperty("IdToDel")).
				when().
				delete("/posts/{id}");

		//Verify the response code
		AllureLogger.logToAllure("Asserting the response if the status code returned is 201 as this is a Delete request");
		response.then().assertThat().statusCode(200);

		//To log the response to report
		logResponseAsString(response);

	}
}
