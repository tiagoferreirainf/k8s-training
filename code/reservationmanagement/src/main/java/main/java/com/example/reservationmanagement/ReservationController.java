package main.java.com.example.reservationmanagement;

import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import main.java.com.example.reservationmanagement.entity.*;
import main.java.com.example.reservationmanagement.mapper.ReservationMapper;
import main.java.com.example.reservationmanagement.validations.ReservationValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/")
@Slf4j
public class ReservationController {

    private final ReservationMongoRepository reservationRepository;
    private final BookMongoRepository bookRepository;
    private final UserMongoRepository userRepository;

    @Autowired
    public ReservationController(ReservationMongoRepository reservationRepository, BookMongoRepository bookRepository, UserMongoRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "getreservations", method = RequestMethod.GET)
    public List<Reservation> getAllReservation() {
        log.info("Getting all reservations.");
        return reservationRepository.findAll();
    }

    @RequestMapping(value = "getreservationids", method = RequestMethod.GET)
    public List<String> getReservationIds() {
        log.info("Getting all reservation ids.");

        List<Reservation> allReservations = reservationRepository.findAll();
        return allReservations.stream().map(Reservation::getId).collect(Collectors.toList());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Reservation createNewReservation(@RequestBody @NonNull ReservationDTO reservationDTO) throws Exception {
        boolean isValid = ReservationValidations.reservationIsValidCreateOperation(reservationDTO);
        if(!isValid){
            throw new Exception("Invalid Reservation Entity");
        }

        Optional<Book> book = bookRepository.findById(reservationDTO.getBookId());
        if(!book.isPresent()){
            throw new Exception("Invalid BookId");
        }

        Optional<User> user = userRepository.findById(reservationDTO.getUserId());
        if(!user.isPresent()){
            throw new Exception("Invalid UserId");
        }

        Reservation reservation = ReservationMapper.mapReservationDTOToReservation(reservationDTO);
        log.info("Saving reservation:  {}", reservation.toString());
        return reservationRepository.save(reservation);
    }

    @RequestMapping(value = "/get/user/{id}", method = RequestMethod.POST)
    public List<Reservation> getByUserId(@PathVariable("id") @NonNull String id) throws Exception {
        log.info("Getting all reservation by user id " + id);
        return reservationRepository.findByUserId(id);
    }

    @RequestMapping(value = "/get/book/{id}", method = RequestMethod.POST)
    public List<Reservation> getByBookId(@PathVariable("id") @NonNull String id) throws Exception {
        log.info("Getting all reservation by book id" + id);
        return reservationRepository.findByBookId(id);
    }

    @RequestMapping(value = "/get/details/{id}", method = RequestMethod.POST)
    public ReservationDetailsDTO getDetailsById(@PathVariable("id") @NonNull String id) throws Exception {
        log.info("Getting all reservation ids.");
        Optional<Reservation> reservationOpt = reservationRepository.findById(id);

        if(reservationOpt.isPresent()){
            Reservation reservation = reservationOpt.get();
            String bookId = reservation.getBookId();
            String userId = reservation.getUserId();

            Optional<Book> bookById = bookRepository.findById(bookId);
            if(!bookById.isPresent()){
                throw new Exception("Book not found");
            }

            Optional<User> userById = userRepository.findById(userId);
            if(!userById.isPresent()){
                throw new Exception("user not found");
            }

            return ReservationMapper.mapReservationToReservationDetails(reservation, bookById.get(), userById.get());
        }

        return null;
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") @NonNull String id) throws Exception {
        log.info("Delete reservation by id {}", id);

        Optional<Reservation> reservation = reservationRepository.findById(id);
        reservation.ifPresent(reservationRepository::delete);
    }

    @DeleteMapping(value = "/delete/all")
    public void deleteAll() {
        log.info("Delete All reservations");
        reservationRepository.deleteAll();
    }
}
