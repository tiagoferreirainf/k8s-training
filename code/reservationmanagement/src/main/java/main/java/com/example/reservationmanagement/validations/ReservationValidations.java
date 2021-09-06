package main.java.com.example.reservationmanagement.validations;

import main.java.com.example.reservationmanagement.entity.ReservationDTO;

public class ReservationValidations {
    public static boolean reservationIsValidCreateOperation(ReservationDTO reservationDTO){

        if(reservationDTO.getBookId() == null || reservationDTO.getBookId().equals("")){
            return false;
        }

        return reservationDTO.getUserId() != null && !reservationDTO.getUserId().equals("");
    }
}
