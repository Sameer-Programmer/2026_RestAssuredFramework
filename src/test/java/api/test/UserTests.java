package api.test;

import api.endPoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserTests {
    private static final Logger logger = LogManager.getLogger(UserTests.class);
    Faker faker = new Faker();
    User userPayload = new User();



    @BeforeClass
    public void setup() {

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

        logger.info("********Create user***************");

        System.out.println(userPayload.getUsername());

        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("******** User Created***************");
    }

    @Test(priority = 2)
    public void testGetUserByName() {


        logger.info("********Read user***************");
        System.out.println("Inside testGetUserByName()");
        System.out.println(userPayload.getUsername());

        Response response = UserEndPoints.readUser(userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("******** userRead info didplayed***************");
    }

     @Test(priority = 3)
    public void testUpdateUser() {

         logger.info("********Updating  user***************");


         System.out.println("Inside testUpdateUserMethod");
         System.out.println(userPayload.getUsername());

        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(userPayload.getUsername(), userPayload);

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

         logger.info("********  User is Updated***************");
    }

     @Test(priority = 4)
    public void testDeleteUser() {

         logger.info("********User is Deleting***************");

         System.out.println("Inside testDeleteUser");
         System.out.println(userPayload.getUsername());
        Response response = UserEndPoints.deleteUser(userPayload.getUsername());

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

         logger.info("********  Deleted User***************");
    }
}