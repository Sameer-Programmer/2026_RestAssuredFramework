package api.test;

import api.endPoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {

    User userPayload = new User();

    @Test(
            priority = 1,
            dataProvider = "Data",
            dataProviderClass = DataProviders.class
    )
    public void testPostUser(String userID,
                             String userName,
                             String firstName,
                             String lastName,
                             String email,
                             String password,
                             String phone) {

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        Response response = UserEndPoints.createUser(userPayload);

        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(
            priority = 2,
            dataProvider = "UserNames",
            dataProviderClass = DataProviders.class
    )
    public void testDeleteUserByName(String userName) {

        Response response = UserEndPoints.deleteUser(userName);

        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}