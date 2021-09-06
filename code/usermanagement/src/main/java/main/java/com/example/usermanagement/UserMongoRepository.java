package main.java.com.example.usermanagement;

import main.java.com.example.usermanagement.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {
    User findByName(String name);
}

