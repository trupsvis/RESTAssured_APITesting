package UserManagement;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import util.PropertyReader;
import util.SoftAssertionUtil;
import util.jsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static util.jsonReader.getJsonArray;

public class getUsers {
    //SoftAssertionUtil softAssertion= new SoftAssertionUtil();
    @Test
    public void getUserData(){
        given().
                when().get("https://reqres.in/api/users/2").
                then().
                assertThat().statusCode(200);
    }

    @Test
    public void validateUserResponseBody(){
        RestAssured.baseURI = "https://reqres.in";

                given().when().get("/api/users/2")
                .then()
                .assertThat()
                .statusCode(StatusCode.SUCCESS.code)
                .body(not(isEmptyString()))
                .body("data.email",equalTo("janet.weaver@reqres.in"))
                .body("data.first_name",equalTo("Janet"))
                .body("data.last_name",equalTo("Weaver"));
    }

    @Test
    public void validateUserHasItem(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given().when().get("/posts")
                .then()
                .extract()
                .response();

        assertThat(response.jsonPath().getList("title"),hasItem("ut quo aut ducimus alias"));
    }

    @Test
    public void validateUserHasItems(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given().when().get("/posts")
                .then()
                .extract()
                .response();

        assertThat(response.jsonPath().getList("title"),hasItems("ut quo aut ducimus alias","qui est esse"));
    }

    @Test
    public void validateUserHasSize(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given().when().get("/todos");
        assertThat(response.jsonPath().getList(""),hasSize(200));
    }

    @Test
    public void validateUserContainsItems(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given().when().get("/users");
        List<String> expectedNames = Arrays.asList("Leanne Graham","Ervin Howell","Clementine Bauch");
        assertThat(response.jsonPath().getList("name"),hasItems(expectedNames.toArray(new String[0])));

        List<String> expectedEmailId = Arrays.asList("Sincere@april.biz","Shanna@melissa.tv","Nathan@yesenia.net","Julianne.OConner@kory.org");
        assertThat(response.jsonPath().getList("email"),hasItems(expectedEmailId.toArray(new String[0])));
    }
    @Test
    public void allDataElementsVerification(){
        RestAssured.baseURI = "https://reqres.in/api";
        Response response = given()
                .queryParam("page",2)
                .when()
                .get("/users");
//                .then()
//                .statusCode(200)
//                .extract()
//                .response();

        response.then().body("data[0].id",equalTo(7));
        response.then().body("data[0].email",equalTo("michael.lawson@reqres.in"));
        response.then().body("data[0].first_name",equalTo("Michael"));
        response.then().body("data[0].last_name",equalTo("Lawson"));
        response.then().body("data[0].avatar",equalTo("https://reqres.in/img/faces/7-image.jpg"));

        response.then().body("data[4].id",equalTo(11));
        response.then().body("data[4].email",equalTo("george.edwards@reqres.in"));
        response.then().body("data[4].first_name",equalTo("George"));
        response.then().body("data[4].last_name",equalTo("Edwards"));
        response.then().body("data[4].avatar",equalTo("https://reqres.in/img/faces/11-image.jpg"));
    }

    @Test
    public void validateGetStatusCodeGetUsers(){
        Response resp = given()
                .queryParam("page",2)
                .when().get("https://reqres.in/api/users");

        int actualStatusCode = resp.getStatusCode();
        assertEquals(actualStatusCode,200);
        System.out.println(resp.body().asString());
    }

//    @Test
//    public  void createUserWithFormParam(){
//        Response resp = given()
//                .contentType("application/x-www-form-urlencoder")
//                .formParam("name", "trupti")
//                .formParam("job","dev")
//                .when()
//                .post("https://reqres.in/api/users")
//                .then()
//                .statusCode(201)
//                .extract()
//                .response();
//    }

    @Test
    public void testMultipleHeader(){
        given()
                .header("Content-Type","application/json")
                .header("Authorization","bearer 0b8a0290-233b-4cd3-8748-766511386168")
                .when().get("https://reqres.in/api/user/2")
                .then()
                .statusCode(200);
        System.out.println("testMultipleHeaders executed successfullygit ");
    }
    @Test
    public void testMultipleHeaderUsingMap(){
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Authorization","bearer 0b8a0290-233b-4cd3-8748-766511386168");
        Response response = given()
                .headers(headers)
                .when().get("https://reqres.in/api/user/2")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Headers headersReturned = response.getHeaders();

        for(Header h :headersReturned){
//            if(h.getName().contains("Server")){
                System.out.println(h.getName() + " : " + h.getValue());
//                assertEquals(h.getValue(),"cloudflare");
//            }
        }
    }

    @Test
    public void fetchCookiesToValidate(){
        Response resp = given()
                .when().get("https://reqres.in/api/user/2")
                .then().statusCode(200)
                .extract()
                .response();

        Map<String, String> cookies = resp.getCookies();
    }

    @Test
    public void validateResponseBodyBasicAuth(){
        Response resp = given()
                .auth().basic("postman","password")
                .when().get("https://postman-echo.com/basic-auth")
                .then().statusCode(200)
                .extract().response();
        System.out.println(resp.body().asString());
    }

    @Test
    public void validateDataFromPropertiesFile() {
        try {
            String serverAddress = PropertyReader.propertyReader("config.properties","serverAddress");
            System.out.println("Server address is =>"+serverAddress);
            Response resp = given()
                    .queryParam("page",2)
                    .when().get(serverAddress);
            int actualStatusCode = resp.getStatusCode();
            assertEquals(actualStatusCode,200);
            System.out.println(resp.body().asString());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = {"SmokeSuite","RegressionSuite"})
    public void verifyStatusCodeDelete(){
        Response resp = given().delete("https://reqres.in/api/users/2");
        assertEquals(resp.getStatusCode(), StatusCode.NO_CONTENT.code);
        System.out.println("verifyStatusCodeDelete executed successfully");
    }

    @Test(groups= "RegressionSuite")
    public void validateDataFromProperties_TestData() {
        try {
            String serverAddress = PropertyReader.propertyReader("config.properties","server");
            String endpoint = jsonReader.getTestData("endpoint");
            String URL = serverAddress+endpoint;
            System.out.println("Server address is =>"+serverAddress);
            System.out.println("URL  " + URL);
            Response resp = given()
                    .queryParam("page",2)
                    .when().get(URL);
            int actualStatusCode = resp.getStatusCode();
            assertEquals(actualStatusCode,200);
            System.out.println(resp.body().asString());

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void softAssertion(){
        System.out.println("softAssert");
        SoftAssertionUtil.assertTrue(true,"");
        SoftAssertionUtil.assertTrue(false, "this is false");
        SoftAssertionUtil.assertAll();
    }

    @Test
    public void hardAssertion(){
        System.out.println("hardAssert");
        assertTrue(false);
        System.out.println("hardAssert");
    }

    @Test
    public void validateWithSoftAssertionUtil(){
        RestAssured.baseURI = "https://reqres.in/api/";
        Response response = given().queryParam("page", 2)
                .when()
                .get("users")
                .then().statusCode(200).extract().response();

        response.then().body("data",hasSize(6));
        SoftAssertionUtil.assertEquals(response.getStatusCode(),StatusCode.SUCCESS.code,"Status code is not 200");
        SoftAssertionUtil.assertAll();
        System.out.println("validateWithSoftAssertionUtil executed successfully");
    }

    @DataProvider(name = "testdata")
    public Object[][] testdata(){
        return new Object[][]{
                {"1", "John"},
                {"2", "Mike"},
                {"3", "Bob"}
        };
    }

    @Test(dataProvider = "testdata")
    @Parameters({"id","name"})
    public void testendpoint(String id, String name){
        given()
                .queryParam("id",id)
                .queryParam("name", name)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200);

    }

    @Test
    public void Test() throws IOException, ParseException {
        jsonReader.getJsonArrayData("technology",2);
        JSONArray jsonArray = getJsonArray("circuitDetails");
        Iterator<String> iterator = jsonArray.iterator();
        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }
}
