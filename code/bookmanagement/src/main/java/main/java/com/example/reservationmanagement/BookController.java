package main.java.com.example.reservationmanagement;

import lombok.extern.slf4j.Slf4j;
import main.java.com.example.reservationmanagement.entity.BookDTO;
import main.java.com.example.reservationmanagement.entity.Book;
import main.java.com.example.reservationmanagement.mapper.BookMapper;
import main.java.com.example.reservationmanagement.validator.BookValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/")
@Slf4j
public class BookController {

    private final BookMongoRepository bookRepository;

    @Autowired
    public BookController(BookMongoRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = "getbooks", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        log.info("Getting all books.");
        return bookRepository.findAll();
    }

    @RequestMapping(value = "getbookids", method = RequestMethod.GET)
    public List<String> getBookIds() {
        log.info("Getting all books ids.");

        List<Book> allBooks = bookRepository.findAll();
        return allBooks.stream().map(Book::getId).collect(Collectors.toList());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Book addNewBooks(@RequestBody @NonNull BookDTO bookDTO) throws Exception {
        boolean isValid = BookValidations.bookIsValidCreateOperation(bookDTO);
        if(!isValid){
            throw new Exception("Invalid Book Entity");
        }

        Book book = BookMapper.mapBookDTOToBook(bookDTO);
        log.info("Saving book:  {}", book.toString());
        return bookRepository.save(book);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    public Book getById(@PathVariable("id") @NonNull String id) throws Exception {
        log.info("Get book by id {} ", id);
        Optional<Book> byId = bookRepository.findById(id);
        return byId.orElse(null);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") @NonNull String id) throws Exception {
        log.info("Delete book by id {}", id);
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(bookRepository::delete);
    }

    @DeleteMapping(value = "/delete/all")
    public void deleteAll() {
        log.info("Delete All books");
        bookRepository.deleteAll();
    }

}
