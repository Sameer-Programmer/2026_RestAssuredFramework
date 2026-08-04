package api.test;

import api.endPoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDTests {
    User userPayload = new User();



    @Test(priority = 1,
            dataProvider = "Data",
            dataProviderClass = DataProviders.class)
    public void testPostuser(String userID,String userName,String firstName,String lastName,
                             String email, String password, String phone)

    {
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUserName(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        Response response = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(),200);
    }


    @Test(priority = 2,dataProviderClass = DataProviders.class,dataProvider = "UserNames")
    public void testDeleteUserByName(String userName){
     Response response = UserEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(),200);

    }






}
