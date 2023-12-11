package UserManagement;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class getErgast {

    @Test(description ="Validate the status code for GET users endpoint", groups="RegressionSuite")
    public void validateResponseBodyGetPathParam(){
        Response resp = given()
                .pathParam("raceSeason", 2016)
                .when()
                .get("https://ergast.com/api/f1/{raceSeason}/circuits.json");
        int statusCode = resp.getStatusCode();
        assertEquals(statusCode, 201);
        System.out.println(resp.body().asString());
    }

}
