package api.test;

import api.endPoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker = new Faker();
    User userPayload = new User();


    @BeforeClass
    public void setupData(){
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUserName(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setUserStatus(0);
    }



    @Test(priority = 1)
    public void testPostuser(){
       Response rs=  UserEndPoints.createUser(userPayload);
       rs.then().log().all();
        Assert.assertEquals(rs.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void testGetuserByName(){
       Response response = UserEndPoints.readUser(userPayload.getUserName());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 3)
    public void testUpdateUsername(){

        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        Response response = UserEndPoints.updateUser(userPayload.getUserName(),userPayload);
        Assert.assertEquals(response.getStatusCode(),200);

    }


    @Test(priority = 4)
    public void testDeleteUser(){
        Response response = UserEndPoints.deleteUser(userPayload.getUserName());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),204);

    }






}
