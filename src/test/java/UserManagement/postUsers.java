package UserManagement;

import core.StatusCode;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class postUsers {

    private static FileInputStream fileInputStreamMethod(String requestBodyFileName){
        FileInputStream fileInputStream;
        try{
            fileInputStream = new FileInputStream(new File(System.getProperty("user.dir")+
                    "/resources/TestData/"+ requestBodyFileName));
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        return fileInputStream;
    }
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

    @Test
    public void validatePostWithStringUsingJSON(){

        try {
            Response response = given()
                    .header("Content-Type","application/json")
                    .body(IOUtils.toString((new FileInputStream(new File(System.getProperty("user.dir")+
                            "/Resources/TestData/postRequestBody.json")))))
                    .when()
                    .post("https://reqres.in/api/users");

            assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
            System.out.println("validatePost Using JSON executed Successfully");
            System.out.println(response.getBody().asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void validatePutUsingStringUsingJSON(){

        try {
            Response response = given()
                    .header("Content-Type","application/json")
                    .body(IOUtils.toString(new FileInputStream(new File(System.getProperty("user.dir")+
                            "/Resources/TestData/putRequestBody.json"))))
                    .when()
                    .put("https://reqres.in/api/users/3");

            assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
            System.out.println("validatePost Using JSON executed Successfully");
            System.out.println(response.getBody().asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void validatePatchWithStringUsingJSON(){

        try {
            Response response = given()
                    .header("Content-Type","application/json")
                    .body(IOUtils.toString(fileInputStreamMethod("patchRequestBody.json")))
                    .when()
                    .patch("https://reqres.in/api/users/4");

            assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
            System.out.println("validate Patch using Private method executed Successfully");
            System.out.println(response.getBody().asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
