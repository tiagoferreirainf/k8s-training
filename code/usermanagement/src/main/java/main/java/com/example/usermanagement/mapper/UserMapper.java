package main.java.com.example.usermanagement.mapper;

import main.java.com.example.usermanagement.entity.User;
import main.java.com.example.usermanagement.entity.UserDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserMapper {

    public static final String PATTERN = "dd/MM/yyyy";

    public static User mapUserDTOToUser(UserDTO dto) throws ParseException {
        return User.builder().address(dto.getAddress())
                .userSettings(dto.getUserSettings())
                .name(dto.getName())
                .creationDate(new Date())
                .birthdate(convertDateFromString(dto.getBirthdate())).build();

    }

    public static Date convertDateFromString(String dateStr) throws ParseException {
        return new SimpleDateFormat(PATTERN).parse(dateStr);
    }
}
