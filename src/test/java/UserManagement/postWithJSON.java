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

public class postWithJSON {

    private static FileInputStream fileInputStreamMethod(String requestBodyFileName){
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(System.getProperty("user.dir")+"/resources/TestData/"+requestBodyFileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return fileInputStream;
    }

    @Test
    public void validatePostWithJSONFile(){

        try {
            Response response = given()
                    .header("Content-Type","application/json")
                    .body(IOUtils.toString(fileInputStreamMethod("postRequestBody.json")))
                    .when()
                    .post("https://reqres.in/api/users");

            assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
            System.out.println("validatePostWithJSONFile executed Successfully");
            System.out.println(response.getBody().asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void validatePutWithJSONFile(){

        try {
            Response response = given()
                    .header("Content-Type","application/json")
                    .body(IOUtils.toString(fileInputStreamMethod("putRequestBody.json")))
                    .when()
                    .put("https://reqres.in/api/users/2");

            assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
            System.out.println("validatePutWithJSONFile executed Successfully");
            System.out.println(response.getBody().asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void validatePatchWithJSONFile(){

        try {
            Response response = given()
                    .header("Content-Type","application/json")
                    .body(IOUtils.toString(fileInputStreamMethod("patchRequestBody.json")))
                    .when()
                    .patch("https://reqres.in/api/users/3");

            assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
            System.out.println("validatePatchWithJSONFile executed Successfully");
            System.out.println(response.getBody().asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
