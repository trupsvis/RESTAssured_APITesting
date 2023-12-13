package UserManagement;

import core.StatusCode;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import pojo.cityRequest;
import pojo.postRequestBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void validatePostUsingPOJO(){

        postRequestBody postRequest = new postRequestBody();
        postRequest.setJob("morphues");
        postRequest.setName("leader");

        try {
            Response response = given()
                    .header("Content-Type","application/json")
                    .body(postRequest)
                    .when()
                    .post("https://reqres.in/api/users");

            assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
            System.out.println("validatePostUsingPOJO executed Successfully");
            System.out.println(response.getBody().asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void validatePostUsingPOJOWithList(){
        List<String> listLanguage = new ArrayList<>();
        listLanguage.add("Java");
        listLanguage.add("Python");
        listLanguage.add("SQL");

        postRequestBody postRequest = new postRequestBody();
        postRequest.setJob("morphues");
        postRequest.setName("leader");
        postRequest.setLanguages(listLanguage);
        try {
            Response response = given()
                    .header("Content-Type","application/json")
                    .body(postRequest)
                    .when()
                    .post("https://reqres.in/api/users");

            assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
            System.out.println("Validate Post Using POJO With List executed Successfully");
            System.out.println(response.getBody().asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void validatePUTUsingPOJO(){
        postRequestBody putRequest = new postRequestBody();
        putRequest.setJob("Leader");
        putRequest.setName("Trupti");

        try {
            Response response = given()
                    .header("Content-Type","application/json")
                    .body(putRequest)
                    .when()
                    .post("https://reqres.in/api/users/2");

            assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
            System.out.println("Validate PUT Using POJO executed Successfully");
            System.out.println(response.getBody().asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void validatePatchWithStringUsingPOJO(){
        postRequestBody patchRequest = new postRequestBody();
        patchRequest.setName("Trupti");

        try {
            Response response = given()
                    .header("Content-Type","application/json")
                    .body(patchRequest)
                    .when()
                    .patch("https://reqres.in/api/users/4");

            assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
            System.out.println("validate Patch using POJO executed Successfully");
            System.out.println(response.getBody().asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void validatePostUsingPOJOListObject(){
        List<String> listLanguage = new ArrayList<>();
        listLanguage.add("Java");
        listLanguage.add("Python");
        listLanguage.add("SQL");

        cityRequest cityRequest1 = new cityRequest();
        cityRequest1.setName("Banglore");
        cityRequest1.setTemperature("35");
        cityRequest cityRequests2 = new cityRequest();
        cityRequests2.setName("Delhi");
        cityRequests2.setTemperature("40");
        List<cityRequest> cityRequests = new ArrayList<>();
        cityRequests.add(cityRequest1);
        cityRequests.add(cityRequests2);

        postRequestBody postRequest = new postRequestBody();
        postRequest.setJob("morphues");
        postRequest.setName("leader");
        postRequest.setLanguages(listLanguage);
        postRequest.setCityRequestBody(cityRequests);


        try {
            Response response = given()
                    .header("Content-Type","application/json")
                    .body(postRequest)
                    .when()
                    .post("https://reqres.in/api/users");

            assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
            System.out.println("Validate Post using POJOListObject executed Successfully");
            System.out.println(response.getBody().asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void validatePatchWithResponsePOJO(){
        String job="Leader";
        postRequestBody patchRequest = new postRequestBody();
        patchRequest.setJob(job);

        try {
            Response response = given()
                    .header("Content-Type","application/json")
                    .body(patchRequest)
                    .when()
                    .patch("https://reqres.in/api/users/4");

            postRequestBody responseBody = response.as(postRequestBody.class);
            System.out.println(responseBody.getJob());
            assertEquals(responseBody.getJob(),job);
            assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
            System.out.println("Validate Patch with Response POJO executed Successfully");
            System.out.println(response.getBody().asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void validatePostWithResponsePOJO(){
        String name = "Banglore";
        String temp = "35";
        List<String> listLanguage = new ArrayList<>();
        listLanguage.add("Java");
        listLanguage.add("Python");
        listLanguage.add("SQL");

        cityRequest cityRequest1 = new cityRequest();
        cityRequest1.setName(name);
        cityRequest1.setTemperature(temp);
        cityRequest cityRequests2 = new cityRequest();
        cityRequests2.setName("Delhi");
        cityRequests2.setTemperature("40");
        List<cityRequest> cityRequests = new ArrayList<>();
        cityRequests.add(cityRequest1);
        cityRequests.add(cityRequests2);

        postRequestBody postRequest = new postRequestBody();
        postRequest.setJob("morphues");
        postRequest.setName("leader");
        postRequest.setLanguages(listLanguage);
        postRequest.setCityRequestBody(cityRequests);


        try {
            Response response = given()
                    .header("Content-Type","application/json")
                    .body(postRequest)
                    .when()
                    .post("https://reqres.in/api/users");

            postRequestBody responseBody = response.as(postRequestBody.class);
            System.out.println(responseBody.getCityRequestBody().get(0).getName());
            System.out.println(responseBody.getCityRequestBody().get(0).getTemperature());
            System.out.println(responseBody.getLanguages());
            assertEquals(responseBody.getCityRequestBody().get(0).getName(),name);
            assertEquals(responseBody.getCityRequestBody().get(0).getTemperature(),temp);
            assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
            System.out.println("Validate Post using POJOListObject executed Successfully");
            System.out.println(response.getBody().asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
