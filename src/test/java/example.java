import files.payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class example {

    public static void main(String[] args) {
        payloads location1 = new payloads();

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(location1.addplace())
                .when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("Server", equalTo("Apache/2.4.41 (Ubuntu)")).extract().response().asString();
        System.out.println("============================================================================");
        System.out.println(response);

        JsonPath js = new JsonPath(response);
        String placeId = js.getString("place_id");
        System.out.println("============================================================================");
        System.out.println(placeId);
        System.out.println("============================================================================");

        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeId + "\",\n" +
                        "\"address\":\"Gdzies Tu w Krakowie\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n")
                .when().put("/maps/api/place/update/json").then().log().all()
                .body("msg", equalTo("Address successfully updated"));
        System.out.println("============================================================================");
        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
                .when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        System.out.println("xxxxxxx============================================================================");
        js = new JsonPath(getPlaceResponse);
        String actualAdress = js.getString("address");
        Assert.assertEquals(actualAdress, "Gdzies Tu w Krakowie");
    }
}
