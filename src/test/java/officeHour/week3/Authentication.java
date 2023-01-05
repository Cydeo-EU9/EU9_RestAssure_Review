package officeHour.week3;

import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

public class Authentication {

    @Test
    public void spartanAuth() {
        String url = "http://3.87.65.105:7000/api/spartans";

        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("user", "user")
                .when().get(url);

        System.out.println(response.statusCode());
    }

    @Test
    public void bearerToken() {

        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTUxNiIsImF1ZCI6InRlYWNoZXIifQ.saFcTsRyMJQj1e8jhya1zpxngBggh5fC3lGsGyBCrQs";

        Response response = given().header("Authorization", token)
                .and().accept(ContentType.JSON)
                .when().get("http://api.qa.bookit.cydeo.com/api/campuses");

        System.out.println(response.statusCode());
        response.prettyPrint();
    }
}
