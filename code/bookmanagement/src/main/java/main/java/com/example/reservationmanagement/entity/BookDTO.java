package main.java.com.example.reservationmanagement.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class BookDTO {
    private String name;
    private String description;
}