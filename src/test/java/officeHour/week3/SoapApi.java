package officeHour.week3;

import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.path.xml.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SoapApi {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://3.87.65.105:8000";
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.XML)
                .when().get("/api/spartans");

        System.out.println(response.statusCode());
//        response.prettyPrint();

        XmlPath xmlPath = response.xmlPath();
        System.out.println(xmlPath.getString("List.item[0].name"));
        System.out.println(xmlPath.getList("List.item.phone"));
    }


}
