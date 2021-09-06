package main.java.com.example.reservationmanagement.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private Date birthdate;
    private Date creationDate = new Date();
    private Map<String, String> userSettings;
    private String address;
}
