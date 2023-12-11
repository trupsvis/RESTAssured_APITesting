package UserManagement;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UploadDownload {
    @Test
    public void FileUploadDownloadExample(){
        File file = new File("resources/demo.txt");
        Response response = given()
                .multiPart("file", file)
                .when()
                .post("https://demo.com/upload");
    }
}
