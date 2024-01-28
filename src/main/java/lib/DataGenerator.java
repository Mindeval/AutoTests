package lib;
import user.User;

import javax.xml.datatype.DatatypeConstants;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class DataGenerator {

    public static Integer getRandomId() {

        return Integer.parseInt(new SimpleDateFormat("yyMMssSSS").format(new java.util.Date()));
    }



    public static Map<String, String> getHeaderData(){
        Map<String,String> data = new HashMap<>();
        data.put("accept", "application/json");
        data.put("Content-Type", "application/json");
        return data;
    }

    public static User getUserData(){
        User user = new User();
        Integer randomId = DataGenerator.getRandomId();
        user.setId(randomId);
        user.setUsername("user" + randomId);
        user.setFirstName("TestFirstName");
        user.setLastName("TestLastName");
        user.setEmail("user" + randomId + "@example.com");
        user.setPassword("testPass");
        user.setPhone("+77777777777");
        user.setUserStatus(0);
        return  user;
    }

    public static Map<String, String> getBodyData(User user){
        Map<String, String> data = new HashMap<>();
        data.put("id", String.valueOf(user.getId()));
        data.put("username", user.getUsername());
        data.put("firstName", user.getFirstName());
        data.put("lastName", user.getLastName());
        data.put("email", user.getEmail());
        data.put("password", user.getPassword());
        data.put("phone", user.getPhone());
        data.put("userStatus", String.valueOf(user.getUserStatus()));

        return  data;
    }





}
