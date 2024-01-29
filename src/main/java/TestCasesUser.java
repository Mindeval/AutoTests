import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lib.ApiRequests;
import lib.Assertions;
import lib.DataGenerator;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import user.User;
import user.UserResponse;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(OrderAnnotation.class)
public class TestCasesUser {
    private final ApiRequests apiCoreRequests = new ApiRequests();
    String URL = "https://petstore.swagger.io";
    public static final User[] user = new User[3];

    @ParameterizedTest
    @ValueSource(ints ={0,1,2})
    @Order(1)
    public void testCreateUser(int i) {
        user[i] = DataGenerator.getUserData();
        Map<String, String> headersData = DataGenerator.getHeaderData();
        Map<String,String> bodyData = DataGenerator.getBodyData(user[i]);

        Response testCreateUser = apiCoreRequests.createUser(URL + "/v2/user", headersData, bodyData);
        assertEquals(200,testCreateUser.statusCode(), "http статус не соответствует ожидаемому");
        System.out.println(testCreateUser.asString());
        assertEquals(Integer.toString(user[i].getId()), testCreateUser.jsonPath().getString("message"),"Сообщение в ответе не соответсвует ожидаемому");

        Response testGetUser = apiCoreRequests.getUser(URL+"/v2/user/user" + user[i].getId(), headersData);

        ObjectMapper objectMapper = new ObjectMapper();
        UserResponse response = new UserResponse();
        try {
            response = objectMapper.readValue(testGetUser.asString(), UserResponse.class);
            }
        catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            }
        Assertions.assertUserCreate(user[i],response);
    }

    @Test
    @Order(2)
    public void testAuth() {
        String username = user[0].getUsername();
        String password = user[0].getPassword();
        Map<String, String> headersData = DataGenerator.getHeaderData();
        Response testLogin = apiCoreRequests.login(URL+"/v2/user/login", headersData, username, password);

        assertEquals(200,testLogin.statusCode(), "http статус не соответствует ожидаемому");
        assertTrue(testLogin.jsonPath().getString("message").indexOf("logged in user session:") != -1, "Сообщение в ответе не соответсвует ожидаемому");

        Response testLogout = apiCoreRequests.logout(URL+"/v2/user/logout", headersData);

        assertEquals(200,testLogout.statusCode(), "http статус не соответствует ожидаемому");
        assertEquals("ok", testLogout.jsonPath().getString("message"),"Сообщение в ответе не соответсвует ожидаемому");
    }

    @ParameterizedTest
    @ValueSource(ints ={0,1,2})
    @Order(3)
    public void testDeleteUser(int i) {
        Map<String, String> headersData = DataGenerator.getHeaderData();
        Response testDeleteUser = apiCoreRequests.deleteUser(URL+"/v2/user/"+ user[i].getUsername(), headersData);

        assertEquals(200,testDeleteUser.statusCode(), "http статус не соответствует ожидаемому");
        assertEquals(testDeleteUser.jsonPath().getString("message"),user[i].getUsername(), "Сообщение в ответе не соответсвует ожидаемому");

        Response testGetDeletedUser = apiCoreRequests.getUser(URL+"/v2/user/user" + user[i].getId(), headersData);

        assertEquals(404,testGetDeletedUser.statusCode(), "http статус не соответствует ожидаемому");
        assertEquals("User not found", testGetDeletedUser.jsonPath().getString("message"),"Сообщение в ответе не соответсвует ожидаемому");
    }
}
