package main.java.com.example.usermanagement.validator;

import lombok.extern.slf4j.Slf4j;
import main.java.com.example.usermanagement.entity.User;

@Slf4j
public class UserValidations {
    public static boolean userIsValidCreateOperation(User user){
        if(user.getAddress() == null || user.getAddress().equals("")){
            return false;
        }

        if(user.getName() == null || user.getName().equals("")){
            return false;
        }

        return user.getAge() >= 0;
    }

    public static boolean userIsValidGetByIdOperarions(User user){
        return userIdIsValid(user.getId());
    }

    public static boolean userIdIsValid(String id){
        return id != null && !id.equals("");
    }
}

