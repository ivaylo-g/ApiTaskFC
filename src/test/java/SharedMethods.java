import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.json.JSONObject;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class SharedMethods implements Constants {


    public void GetBaseURL() {

        RestAssured.baseURI = BASE_URL;
    }

    public void GetListUsers(String Corrine) {

        given().when().get(LIST_USERS).then()
                .statusCode(200)
                .body("name", hasItems(Corrine));
    }

    public void GetSpecificUser(Integer userID) {

        given().when().get(LIST_USERS + "/" + userID).then()
                .statusCode(200);
    }

    public static Integer CreateNewUser() {
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


    public void UpdateUser(Integer userID, String name) {
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

    public void AlreadyDeletedUser(Integer userID) {

        given().when().delete(LIST_USERS + "/" + userID).then()
                .statusCode(404);
    }

    public void PageLimit(int pageLimit) {
        given().when().get(LIST_USERS + "/?page=1&limit=" + pageLimit).then()
                .statusCode(200)
                .body("size()", is(pageLimit));
    }

    public void SortAndOrder(String sortedBy,String order) {
        given().when().get(LIST_USERS + "/?sortBy=" + sortedBy +"&order=" + order).then()
                .statusCode(200)
                ;
    }

}
