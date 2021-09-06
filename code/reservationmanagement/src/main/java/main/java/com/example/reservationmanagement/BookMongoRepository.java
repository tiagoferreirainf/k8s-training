package main.java.com.example.reservationmanagement;

import main.java.com.example.reservationmanagement.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMongoRepository extends MongoRepository<Book, String> {}

