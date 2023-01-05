package officeHour.week3;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ParametrizedTest {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://3.87.65.105:8000";
    }

    /*
    Four ways to provide data
    1. value source
    2. Method source
    3. CSV source
    4. CSV file source
     */

    // value source
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    public void test3(int id) {
        System.out.println(given().accept(ContentType.JSON)
                .and()
                .pathParam("id", id)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .extract().response().path("name").toString());
    }

    // method source
    public static List<Integer> randomNums() {
        Random random = new Random();
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            nums.add(random.nextInt(100));
        }
        return nums;
    }

    @ParameterizedTest
    @MethodSource("randomNums")
    public void test1(int id) {
        given().accept(ContentType.JSON)
                .pathParam("id", id)
                .when().get("api/spartans/{id}")
                .then()
                .statusCode(200);
    }

    //csv source test
    @ParameterizedTest
    @CsvSource({"20,Lothario",
            "21, Mark",
            "22, Koenraad"})
    public void nameId(String id, String name) {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", id)
                .when().get("/api/spartans/{id}");

        Assertions.assertEquals(name, response.path("name"));
    }

    //csv file source
    @ParameterizedTest
    @CsvFileSource(resources = "/SpartanDataPOST.csv", numLinesToSkip = 1)
    public void addSpartanTest(String name, String gender, Long phone) {

        Map<String, Object> spartanMap = new HashMap<>();
        spartanMap.put("name", name);
        spartanMap.put("gender", gender);
        spartanMap.put("phone", phone);

        Response response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartanMap)
                .when().post("/api/spartans")
                .then().statusCode(201)
                .extract().response();

        Assertions.assertEquals("A Spartan is Born!", response.path("success"));
    }
}
