package UserManagement;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class builderPatternImplementation {

    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;


    @Test
    public void testRestAssuredApproach(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        given()
                .contentType(ContentType.JSON)
                .queryParam("userId","2")
                .when()
                .get("/posts")
                .then()
                .assertThat()
                .statusCode(200);

    }

    @Test
    public void testRestAssuredBuilderPattern(){
        requestSpec = getRequestSpecificationBuilder("1","ContentType.JSON");

            given()
                    .spec(requestSpec)
                    .when()
                    .get("/posts")
                    .then()
                    .spec(setResponseSpecificationBuilder(200,"application/json"));
    }

    private RequestSpecification getRequestSpecificationBuilder(String queryparam, String contentType){
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .setContentType(contentType)
                .addQueryParam("userId",queryparam)
                .build();

        return requestSpec;
    }

    private ResponseSpecification setResponseSpecificationBuilder(int statusCode, String contentType){
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectContentType(contentType)
                .build();
        return responseSpec;
    }

}
