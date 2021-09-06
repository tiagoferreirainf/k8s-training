package main.java.com.example.reservationmanagement;

import main.java.com.example.reservationmanagement.entity.Book;
import main.java.com.example.reservationmanagement.entity.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationMongoRepository extends MongoRepository<Reservation, String> {
    List<Reservation> findByUserId(String userId);
    List<Reservation> findByBookId(String bookId);
}

