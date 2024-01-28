package lib;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiRequests {
    @Step("Make a POST-request CreateUser")
    public Response createUser(String url, Map<String,String> headersData, Map<String,String> bodyData){
        return given()
                .filter(new AllureRestAssured())
                .headers(headersData)
                .body(bodyData)
                .post(url)
                .andReturn();
    }

    @Step("Make a GET-request User info")
    public Response getUser(String url, Map<String,String> headersData){
        return given()
                .filter(new AllureRestAssured())
                .headers(headersData)
                .get(url)
                .andReturn();
    }

    @Step("Make a GET-request Login")
    public Response login(String url, Map<String,String> headersData){
        return given()
                .filter(new AllureRestAssured())
                .headers(headersData)
                .get(url)
                .andReturn();
    }

    @Step("Make a GET-request Logout")
    public Response logout(String url, Map<String,String> headersData){
        return given()
                .filter(new AllureRestAssured())
                .headers(headersData)
                .get(url)
                .andReturn();
    }




}
