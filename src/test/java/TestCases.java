import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestCases {

    RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://automationexercise.com")
            .build();

    @Test
    public void cases(){

        RequestSpecification reqLogin = given().log().all().spec(req);
        String productResponse = reqLogin.when().get("/api/productsList").then().log().all().statusCode(200).extract().response().asString();
        //System.out.println(productResponse);
    }

    @Test
    public void AllProductListPosT(){
        RequestSpecification reqLogin = given().log().all().spec(req);
        String productResponse = reqLogin.when().post("/api/productsList").then().log().all().statusCode(200).extract().response().asString();
        JsonPath js = new JsonPath(productResponse);
        String message = js.get("message");
        String statusCode = String.valueOf(js.getInt("responseCode"));
        Assert.assertEquals(message, "This request method is not supported.");
        System.out.println(statusCode);
    }

}
