package api.endPoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class UserEndPoints {

    public static Response createUser(User payload) {
        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);
        response.then().log().all();

        return response;
    }

    public static Response readUser(String usernamekey) {
        Response response = given().pathParam("username", usernamekey)
                .when()
                .get(Routes.get_url);
        return response;
    }

    /*
    {username} → Placeholder in the URL.
"username" → First argument of pathParam() (must match the placeholder).
username → Java variable containing the value.
     */

    public static Response updateUser(String usernamekey, User payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", usernamekey)
                .body(payload)
                .when()
                .put(Routes.update_url);
        return response;
    }

    public static Response deleteUser(String usernamekey) {
        Response response = given().pathParam("username", usernamekey)
                .when()
                .delete(Routes.delete_url);
        return response;
    }


}
