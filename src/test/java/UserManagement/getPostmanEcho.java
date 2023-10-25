package UserManagement;

import core.StatusCode;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import util.jsonReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class getPostmanEcho {
    @Test(groups = "SmokeSuite")
    public void validateResponseWithTestDataFromJson() throws IOException {
        try {
            String username = jsonReader.getTestData("username");
            String password = jsonReader.getTestData("password");
            Response resp = given()
                    .auth().basic(username,password)
                    .when().get("https://postman-echo.com/basic-auth")
                    .then()
                    .extract()
                    .response();

            Response response = given()
                    .auth().basic(jsonReader.getTestData("username"),jsonReader.getTestData(password))
                    .when().get("https://postman-echo.com/basic-auth")
                    .then().statusCode(StatusCode.SUCCESS.code)
                    .extract()
                    .response();

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

}
