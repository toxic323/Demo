import files.FileReader;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PayloadFromFile {
    @Test
    public void addBook() throws IOException {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(FileReader.get("C:\\Users\\zx00\\IdeaProjects\\restAssured\\payloadfiles\\locNO1.json"))
                .when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
    }
}
