package main.java.com.example.usermanagement.validator;

import lombok.extern.slf4j.Slf4j;
import main.java.com.example.usermanagement.entity.User;
import main.java.com.example.usermanagement.entity.UserDTO;
import main.java.com.example.usermanagement.mapper.UserMapper;

import java.text.ParseException;
import java.util.Date;

@Slf4j
public class UserValidations {
    public static boolean userIsValidCreateOperation(UserDTO user) throws ParseException {
        if(user.getAddress() == null || user.getAddress().equals("")){
            return false;
        }

        if(user.getName() == null || user.getName().equals("")){
            return false;
        }

        String birthdate = user.getBirthdate();
        if(birthdate == null || birthdate.equals("")){
            return false;
        }

        Date date = UserMapper.convertDateFromString(birthdate);

        return date.getTime() < new Date().getTime();
    }

    public static boolean userIsValidGetByIdOperarions(User user){
        return userIdIsValid(user.getId());
    }

    public static boolean userIdIsValid(String id){
        return id != null && !id.equals("");
    }
}

