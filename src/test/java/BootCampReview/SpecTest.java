package BootCampReview;

import io.restassured.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

public class SpecTest extends SpectTestBase{

    @Test
    public void test1(){
        Response response = RestAssured.given().spec(reqSpec)
                .when().get("/api/spartans/{id}")
                .then().spec(resSpec).extract().response();
    }


}
