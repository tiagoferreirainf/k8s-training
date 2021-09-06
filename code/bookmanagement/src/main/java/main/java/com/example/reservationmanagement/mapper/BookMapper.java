package main.java.com.example.reservationmanagement.mapper;

import main.java.com.example.reservationmanagement.entity.BookDTO;
import main.java.com.example.reservationmanagement.entity.Book;

import java.util.Date;

public class BookMapper {
    public static Book mapBookDTOToBook(BookDTO dto){
        return Book.builder().creationDate(new Date())
                .description(dto.getDescription())
                .name(dto.getName()).build();
    }
}
