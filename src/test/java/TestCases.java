import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestCases {

    RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://automationexercise.com")
            .build();
    String product;
    static String category;
    JsonPath js;

    @Test
    public void cases(){

        RequestSpecification reqLogin = given().log().all().spec(req);
        String productResponse = reqLogin.when().get("/api/productsList").then().log().all().statusCode(200).extract().response().asString();
        //System.out.println(productResponse);
        js = new JsonPath(productResponse);
        category = js.get("products[0].category.category");
        System.out.println(category);
    }

    @Test
    public void AllProductListPosT(){
        RequestSpecification reqLogin = given().log().all().spec(req);
        String productResponse = reqLogin.when().post("/api/productsList").then().log().all().statusCode(200).extract().response().asString();
        js = new JsonPath(productResponse);
        String message = js.get("message");
        String statusCode = String.valueOf(js.getInt("responseCode"));
        Assert.assertEquals(message, "This request method is not supported.");
        System.out.println(statusCode);
    }

    @Test
    public void getAllBrandsList(){
        RequestSpecification reqLogin = given().log().all().spec(req);
        String productResponse = reqLogin.when().get("/api/brandsList").then().log().all().statusCode(200).extract().response().asString();
        System.out.println(productResponse);
    }

    @Test
    public void ToSearchProduct(){
        RequestSpecification reqLogin = given().log().all().spec(req).param("search_product", "top");
        String productResponse = reqLogin.when().post("/api/searchProduct").then().log().all().statusCode(200).extract().response().asString();
        System.out.println(productResponse);
    }

}
