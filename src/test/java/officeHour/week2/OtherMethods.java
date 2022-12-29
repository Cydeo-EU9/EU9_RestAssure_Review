package officeHour.week2;

import io.restassured.http.*;
import io.restassured.response.*;
import officeHour.week2.PojoClasses.*;
import org.junit.jupiter.api.*;

import java.awt.image.*;
import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class OtherMethods {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://3.87.65.105:8000";
    }


    //post method
    // when we provide spartan body in post method, we have three ways,
    // string, map and object
    @Test
    public void test1(){
        String body = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Zulpikar\",\n" +
                "  \"phone\": 4569871235\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/spartans");

        response.prettyPrint();
    }


    // provide post body as a map
    @Test
    public void test2(){
        Map<String,Object> body = new HashMap<>();
        body.put("name","Zulpikar");
        body.put("gender","Male");
        body.put("phone",4569871235l);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/spartans");

        response.prettyPrint();

    }

    // provide post body as a class object
    @Test
    public void test3(){
        Spartan newSpartan = new Spartan();
        newSpartan.setName("Zulpikar");
        newSpartan.setGender("Male");
        newSpartan.setPhone(1236547895l);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(newSpartan)
                .when().post("/api/spartans");

        response.prettyPrint();
    }

    // delete a spartan
    @Test
    public void test4(){
        Response response = given().accept(ContentType.JSON)
                .when().delete("/api/spartans/587");

        System.out.println(response.statusCode());
        response.prettyPrint();
    }
}
