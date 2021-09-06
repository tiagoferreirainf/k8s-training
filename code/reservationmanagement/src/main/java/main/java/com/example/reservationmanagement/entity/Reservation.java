package main.java.com.example.reservationmanagement.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Document(collection = "reservations")
public class Reservation {
    @Id
    private String id;

    private String userId;
    private String bookId;
    private Date reservationDate;
}
