import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class SharedMethods implements Constants {


    public static Integer VerifyCreationOfNewUsersAndReturnsItsID() {
        Response res = given()
                .contentType("application/json")
                .when()
                .post(LIST_USERS)
                .then()
                .statusCode(201)
                .log().body().extract().response();
        System.out.println(res);
        int id = Integer.parseInt(res.jsonPath().get("id"));
        System.out.println(id);
        return id;
    }

    public void GetBaseURL() {

        RestAssured.baseURI = BASE_URL;
    }

    public void CheckListUsersHasInputParameter(String Corrine) {

        given().when().get(LIST_USERS).then()
                .statusCode(200)
                .body("name", hasItems(Corrine));
    }

    public void GetSpecificUserAndVerifyName(Integer userID ,String name) {

        given().when().get(LIST_USERS + "/" + userID).then()
                .statusCode(200)
                .body("name", equalTo(name));
    }

    public void VerifyUpdateUserWithNewName(Integer userID, String name) {
        JSONObject request = new JSONObject();
        request.put("name", name);

        given()
                .contentType("application/json")
                .body(request.toString())
                .when()
                .put(LIST_USERS + "/" + userID)
                .then()
                .statusCode(200)
                .body("name", equalTo(name));
    }

    public void DeleteUser(Integer userID) {

        given().when().delete(LIST_USERS + "/" + userID).then()
                .statusCode(200);
    }

    public void CheckStatusCodeOnAlreadyDeletedUser(Integer userID) {

        given().when().delete(LIST_USERS + "/" + userID).then()
                .statusCode(404);
    }

    public void CheckPageLimitIsEqualToBodySize(int pageLimit) {
        given().when().get(LIST_USERS + "/?page=1&limit=" + pageLimit).then()
                .statusCode(200)
                .body("size()", is(pageLimit));
    }

    @SuppressWarnings("rawtypes")
    public String GetFirstValueOnSortAndOrder(String sortedBy, String order) {

        Response res = given().when().get(LIST_USERS + "/?sortBy=" + sortedBy + "&order=" + order).then()
                .statusCode(200).log().body().extract().response();

        ArrayList sortedByValueArrList = res.jsonPath().get(sortedBy);

        return (String) sortedByValueArrList.get(0);
    }

}
