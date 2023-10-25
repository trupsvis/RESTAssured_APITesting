package UserManagement;

import core.StatusCode;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class postUsers {
    @Test
    public void validatePostWithString(){

        try {
            Response response = given()
                    .header("Content-Type","application/json")
                    .body("{\"email\":\"truptij@reqres.in\",\"first_name\":\"trups\",\"last_name\":\"kirdat\"}")
                    .when()
                    .post("https://reqres.in/api/users");

            assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
            System.out.println("validatePostWithString executed Successfully");
            System.out.println(response.getBody().asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void validatePutUsingString(){

        try {
            Response response = given()
                    .header("Content-Type","application/json")
                    .body("{\"email\":\"vishal@reqres.in\",\"first_name\":\"vishal\",\"last_name\":\"kirdat\"}")
                    .when()
                    .put("https://reqres.in/api/users/3");

            assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
            System.out.println("validatePostWithString executed Successfully");
            System.out.println(response.getBody().asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void validatePatchWithString(){

        try {
            Response response = given()
                    .header("Content-Type","application/json")
                    .body("{\"email\":\"truptij@reqres.in\",\"first_name\":\"trups\",\"last_name\":\"jirange\"}")
                    .when()
                    .patch("https://reqres.in/api/users/4");

            assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
            System.out.println("validatePostWithString executed Successfully");
            System.out.println(response.getBody().asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
