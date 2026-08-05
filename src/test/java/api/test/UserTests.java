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
    public void setupData() {

        System.out.println("Inside BeforeClass");

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setUserStatus(0);

        System.out.println("Generated Username: " + userPayload.getUsername());
    }

    @Test(priority = 1)
    public void testPostUser() {

        System.out.println(userPayload.getUsername());

        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testGetUserByName() {

        System.out.println("Inside testGetUserByName()");
        System.out.println(userPayload.getUsername());

        Response response = UserEndPoints.readUser(userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

     @Test(priority = 3)
    public void testUpdateUser() {

         System.out.println("Inside testUpdateUserMethod");
         System.out.println(userPayload.getUsername());

        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(userPayload.getUsername(), userPayload);

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

     @Test(priority = 4)
    public void testDeleteUser() {


         System.out.println("Inside testDeleteUser");
         System.out.println(userPayload.getUsername());
        Response response = UserEndPoints.deleteUser(userPayload.getUsername());

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}