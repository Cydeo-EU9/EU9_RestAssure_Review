package officeHour.week1;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class APIReview {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://3.87.65.105:8000";
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .when().get("http://3.87.65.105:8000/api/spartans");

//        response.prettyPrint();
        System.out.println(response.statusCode());
        System.out.println(response.contentType());
    }


    // path params
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when().get("/api/spartans/{id}");

        response.prettyPrint();
    }

    // query params
    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("nameContains","ma")
                .when().get("/api/spartans/search");

        response.prettyPrint();
    }

    // two different query params together
    @Test
    public void test4(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("nameContains","ma")
                .and().queryParam("gender","Female")
                .when().get("/api/spartans/search");

        response.prettyPrint();
    }

    // provide query params in map format
    @Test
    public void test5(){

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("nameContains","ma");
        queryParams.put("gender","Female");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryParams)
                .when().get("/api/spartans/search");

        response.prettyPrint();
        System.out.println(response.statusCode()); // 200
        System.out.println(response.header("Content-Type")); // content-type --> value
        System.out.println(response.header("Date")); // content-type --> value
    }

    // get data from body


}
