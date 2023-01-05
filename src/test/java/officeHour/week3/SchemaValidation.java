package officeHour.week3;

import io.restassured.http.*;
import io.restassured.module.jsv.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SchemaValidation {


    @BeforeAll
    public static void setUp(){
        baseURI = "http://3.87.65.105:8000";
    }


    @Test
    public void test1(){



        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/20")
                .then().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("singleSpartanSchema.json"))
                .extract().response();

        response.prettyPrint();


    }

}
