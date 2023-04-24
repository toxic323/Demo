import files.payloads;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LibraryAPIuzywajactestNG {
    @Test(dataProvider = "booksData")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all().header("Content-Type", "application/json")
                .body(payloads.addbook(isbn, aisle))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath jsRes = new JsonPath(response);
        String id = jsRes.get("id");
    }

    @DataProvider(name = "booksData")
    private Object[][] getdata() {
        return new Object[][]{{"xssa", "1213"}, {"baas", "2123"}, {"cxcx", "41123"}};
    }
}
