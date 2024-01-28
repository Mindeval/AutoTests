import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lib.ApiRequests;
import lib.DataGenerator;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import user.User;

import java.util.Map;

@TestMethodOrder(OrderAnnotation.class)
public class TestCasesUser {
    private final ApiRequests apiCoreRequests = new ApiRequests();
    String URL = "https://petstore.swagger.io";
    User[] user = new User[3];


    @ParameterizedTest
    @ValueSource(ints ={0,1,2})
    @Order(1)
    public void testCreateUser(int i) {
       // for (i = 0; i < 2; i++) {
            user[i] = new User();
            Map<String, String> headersData = DataGenerator.getHeaderData();
            Map<String,String> bodyData = DataGenerator.getBodyData(user[i]);

            //Map<String, String> bodyDatas = DataGenerator.getBodyData();
            Response testCreateUser = apiCoreRequests.createUser(URL + "/v2/user", headersData, bodyData);
            System.out.println(testCreateUser.asString());
        System.out.println(user[i].getEmail());
       // }
    }
/*
    @ParameterizedTest
    @MethodSource("GetUsers")
    @Order(2)
    public void testGetUser(User user) throws InterruptedException {
        User[] users = GetUsers();

        Map<String,String> headersData = DataGenerator.getHeaderData();
        Response testGetUser = apiCoreRequests.getUser(URL+"/v2/user/user" + user.getId(), headersData);



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
     */

}
