package main.java.com.example.usermanagement;

import lombok.extern.slf4j.Slf4j;
import main.java.com.example.usermanagement.entity.User;
import main.java.com.example.usermanagement.entity.UserDTO;
import main.java.com.example.usermanagement.mapper.UserMapper;
import main.java.com.example.usermanagement.validator.UserValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/")
@Slf4j
public class UserController {

    private final UserMongoRepository userRepository;

    @Autowired
    public UserController(UserMongoRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "getusers", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        log.info("Getting all users.");
        return userRepository.findAll();
    }

    @RequestMapping(value = "getuserids", method = RequestMethod.GET)
    public List<String> getUserIds() {
        log.info("Getting all users ids.");

        List<User> allUsers = userRepository.findAll();
        return allUsers.stream().map(User::getId).collect(Collectors.toList());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public User addNewUsers(@RequestBody @NonNull UserDTO dto) throws Exception {
        boolean isValid = UserValidations.userIsValidCreateOperation(dto);
        if(!isValid){
            throw new Exception("Invalid User Entity");
        }

        User user = UserMapper.mapUserDTOToUser(dto);
        log.info("Saving user:  {}", user.toString());
        return userRepository.save(user);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    public User getById(@PathVariable("id") @NonNull String id) throws Exception {
        log.info("Get user by id {} ", id);

        boolean isValid = UserValidations.userIdIsValid(id);
        if(!isValid){
            throw new Exception("Invalid User Entity");
        }

        Optional<User> byId = userRepository.findById(id);
        return byId.orElse(null);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") @NonNull String id) throws Exception {
        log.info("Delete user by id {}", id);

        boolean isValid = UserValidations.userIdIsValid(id);
        if(!isValid){
            throw new Exception("Invalid User Entity");
        }

        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }

    @DeleteMapping(value = "/delete/all")
    public void deleteAll() {
        log.info("Delete All users");
        userRepository.deleteAll();
    }

}
