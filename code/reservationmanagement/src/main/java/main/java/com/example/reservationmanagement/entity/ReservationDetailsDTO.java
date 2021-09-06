package main.java.com.example.reservationmanagement.entity;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReservationDetailsDTO {
    private String id;
    private Date reservationDate;
    User user;
    Book book;
}
