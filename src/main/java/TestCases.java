import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import org.junit.jupiter.api.*;

import java.util.Map;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import lib.ApiRequests;
import lib.DataGenerator;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.provider.ValueSource;
import user.User;
import user.UserResponse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class TestCases {

    private final ApiRequests apiCoreRequests = new ApiRequests();

    String URL = "https://petstore.swagger.io";
    User[] Users = new User[3];

    public TestCases() throws InterruptedException {
        Users = InitUsers();
    }

    private User[] GetUsers()
    {
        return Users;
    }

    private static User[] InitUsers() throws InterruptedException {
        ArrayList<User> result = new ArrayList<User>();
        result.add(DataGenerator.getUserData());
        TimeUnit.MILLISECONDS.sleep(5);
        result.add(DataGenerator.getUserData());
        TimeUnit.MILLISECONDS.sleep(5);
        result.add(DataGenerator.getUserData());
        User[] arrayResult = new User[result.size()];
        result.toArray(arrayResult);
        return arrayResult;
    }
    @ParameterizedTest
    @MethodSource("GetUsers")
    @Order(1)
    public void testCreateUser(User i) {
        Map<String,String> headersData = DataGenerator.getHeaderData();
        Map<String,String> bodyData = DataGenerator.getBodyData(i);
        Response testCreateUser = apiCoreRequests.createUser(URL+"/v2/user",headersData, bodyData);

     //   System.out.println(testCreateUser.asString());
    //    System.out.println(i.getEmail());
     //   System.out.println(i.toString());
        }

    @ParameterizedTest
    @MethodSource("GetUsers")
    @Order(2)
    public void testGetUser(User user) throws InterruptedException {
        User[] users = GetUsers();

        Map<String,String> headersData = DataGenerator.getHeaderData();
        Response testGetUser = apiCoreRequests.getUser(URL+"/v2/user/user" + user.getId(), headersData);
     //   System.out.println(URL+"/v2/user/user" + user.getId());
      //  System.out.println(testGetUser.asString());


        ObjectMapper objectMapper = new ObjectMapper();
        UserResponse response = new UserResponse();
        try {
            response = objectMapper.readValue(testGetUser.asString(), UserResponse.class);

        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(testGetUser.asString());
        System.out.println(response.getId()+ " "+ response.getEmail());

    }


}
