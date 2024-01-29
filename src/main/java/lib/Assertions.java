package lib;

import user.User;
import user.UserResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Assertions {
    public static void assertUserCreate(User user, UserResponse response){
        assertEquals(user.getId(), response.getId(), "ID созданного пользователя не совпадает с переданным");
        assertEquals(user.getUsername(), response.getUsername(), "Username созданного пользователя не совпадает с переданным");
        assertEquals(user.getFirstName(), response.getFirstName(), "FirstName созданного пользователя не совпадает с переданным");
        assertEquals(user.getLastName(), response.getLastName(), "LastName созданного пользователя не совпадает с переданным");
        assertEquals(user.getEmail(), response.getEmail(), "Email созданного пользователя не совпадает с переданным");
        assertEquals(user.getPassword(), response.getPassword(), "Password созданного пользователя не совпадает с переданным");
        assertEquals(user.getPhone(), response.getPhone(), "Phone созданного пользователя не совпадает с переданным");
        assertEquals(user.getUserStatus(), response.getUserStatus(), "Status созданного пользователя не совпадает с переданным");
    }
}
