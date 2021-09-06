package main.java.com.example.reservationmanagement;

import main.java.com.example.reservationmanagement.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {}

