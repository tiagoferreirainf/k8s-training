package main.java.com.example.reservationmanagement.validator;

import lombok.extern.slf4j.Slf4j;
import main.java.com.example.reservationmanagement.entity.BookDTO;
import main.java.com.example.reservationmanagement.entity.Book;

@Slf4j
public class BookValidations {
    public static boolean bookIsValidCreateOperation(BookDTO book){

        if(book.getName() == null || book.getName().equals("")){
            return false;
        }

        return book.getDescription() != null && !book.getDescription().equals("");
    }

    public static boolean bookIsValidGetByIdOperarions(Book book){
        return bookIdIsValid(book.getId());
    }

    public static boolean bookIdIsValid(String id){
        return id != null && !id.equals("");
    }
}

