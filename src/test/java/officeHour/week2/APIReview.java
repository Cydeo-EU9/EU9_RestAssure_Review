package officeHour.week2;

import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;



import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class APIReview {


    @BeforeAll
    public static void setUp(){
        baseURI = "http://3.87.65.105:8000";
    }


    // get data from response body
    // two ways to get data from body --> path and jsonpath
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/15");

        response.prettyPrint();
        System.out.println(response.path("id").toString());
        String name = response.path("name").toString();
        System.out.println("name = " + name);
    }
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/15");

        JsonPath jsonPath = response.jsonPath();
        String gender = jsonPath.getString("gender");
        Long phone = jsonPath.getLong("phone");
        System.out.println("Gender is " + gender + " and phone is " + phone);

    }

    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender","Female")
                .when().get("/api/spartans/search");

        JsonPath jsonPath = response.jsonPath();
//        response.prettyPrint();
        List<Map<String, Object>> femaleSpartans = jsonPath.getList("content");
        System.out.println(femaleSpartans);
        System.out.println(femaleSpartans.get(1));
        Long phone = (Long) femaleSpartans.get(1).get("phone");
        System.out.println("phone = " + phone);
    }

    @Test
    public void test4(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("nameContains","da")
                .when().get("/api/spartans/search");

        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        List<String> names = jsonPath.getList("content.name");
        System.out.println(names);
    }

    //hamcrest matchers
    @Test
    public void test5(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/15")
                .then().statusCode(200)
                .and().body("name",is("'Asya"),
                        "gender",equalTo("Female"),
                        "id",is(greaterThan(5)))
                .extract().response();

        response.prettyPrint();
    }

    // validate list using hamcrest mathcers
    @Test
    public void test6(){
        List<String> names = given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().statusCode(200)
                .extract().jsonPath().getList("name");

        System.out.println(names);
        assertThat(names,hasSize(greaterThan(20)));
        assertThat(names,hasItem("Hershel"));
        assertThat(names,everyItem(not(nullValue())));



    }


}
