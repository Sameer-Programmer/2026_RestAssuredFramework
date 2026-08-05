package api.endPoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.groovy.control.io.ReaderSource;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
/*
post_url=https://petstore.swagger.io/v2/user
get_url=https://petstore.swagger.io/v2/user/{username}
update_url=https://petstore.swagger.io/v2/user/{username}
delete_url=https://petstore.swagger.io/v2/user/{username}
 */


public class UserEndPoints2 {

    // Aditional Method getting urls from propertiesfile
    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes"); // No need of file path it directly catch from test/resource folder
        return routes;
    }


    public static Response createUser(User payload)

    {
      String post_url =  getURL().getString("post_url");
        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);
        response.then().log().all();

        return response;
    }

    public static Response readUser(String usernamekey)
    {
        String get_url =  getURL().getString("get_url");
        Response response = given().pathParam("username", usernamekey)
                .when()
                .get(get_url);
        return response;
    }

    /*
    {username} → Placeholder in the URL.
"username" → First argument of pathParam() (must match the placeholder).
username → Java variable containing the value.
     */

    public static Response updateUser(String usernamekey, User payload)
    {
        String update_url =  getURL().getString("update_url");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", usernamekey)
                .body(payload)
                .when()
                .put(update_url);
        return response;
    }

    public static Response deleteUser(String usernamekey)
    {
        String delete_url =  getURL().getString("delete_url");
        Response response = given().pathParam("username", usernamekey)
                .when()
                .delete(delete_url);
        return response;
    }


}
