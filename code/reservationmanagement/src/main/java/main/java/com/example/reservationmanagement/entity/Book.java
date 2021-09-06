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
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    private String name;
    private Date creationDate = new Date();
    private String description;
}
