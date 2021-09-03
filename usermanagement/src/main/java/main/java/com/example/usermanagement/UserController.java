package main.java.com.example.usermanagement;

import lombok.extern.slf4j.Slf4j;
import main.java.com.example.usermanagement.entity.User;
import main.java.com.example.usermanagement.validator.UserValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public User addNewUsers(@RequestBody User user) throws Exception {
        boolean isValid = UserValidations.userIsValidCreateOperation(user);
        if(!isValid){
            throw new Exception("Invalid User Entity");
        }

        user.setCreationDate(new Date());
        log.info("Saving user:  {}", user.toString());
        return userRepository.save(user);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    public User getById(@PathVariable("id") String id) throws Exception {
        log.info("Get user by id {} ", id);

        boolean isValid = UserValidations.userIdIsValid(id);
        if(!isValid){
            throw new Exception("Invalid User Entity");
        }

        Optional<User> byId = userRepository.findById(id);
        return byId.orElse(null);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") String id) throws Exception {
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
