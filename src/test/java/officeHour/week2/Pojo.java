package officeHour.week2;


import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import officeHour.week2.PojoClasses.*;
import org.junit.jupiter.api.*;



import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Pojo {

    // json --> java object( list, map,  class) -- deserialization
    // java object --> json -- serialization

    @BeforeAll
    public static void setUp(){
        baseURI = "http://3.87.65.105:8000";
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/20");

        response.prettyPrint();

//        response.as(List.class);
//        response.as(Map.class);
        Spartan spartan20 = response.as(Spartan.class);
        System.out.println(spartan20);
    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("nameContains","da")
                .when().get("/api/spartans/search");

        response.prettyPrint();

        Search search1 = response.as(Search.class);
        System.out.println(search1);

        JsonPath jsonPath = response.jsonPath();
        Spartan spartan57 = jsonPath.getObject("content[0]", Spartan.class);
        System.out.println(spartan57);
    }

}
