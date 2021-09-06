package main.java.com.example.reservationmanagement.mapper;

import main.java.com.example.reservationmanagement.entity.*;

import java.util.Date;

public class ReservationMapper {
    public static Reservation mapReservationDTOToReservation(ReservationDTO dto){
        return Reservation.builder()
                .bookId(dto.getBookId())
                .userId(dto.getUserId())
                .reservationDate(new Date()).build();
    }

    public static ReservationDetailsDTO mapReservationToReservationDetails(Reservation reservation, Book book, User user){
        return ReservationDetailsDTO.builder()
                .id(reservation.getId())
                .reservationDate(reservation.getReservationDate())
                .book(book)
                .user(user)
                .build();
    }
}
