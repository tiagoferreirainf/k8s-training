package main.java.com.example.usermanagement.entity;

import lombok.*;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class UserDTO {
    private String name;
    private String birthdate;
    private Map<String, String> userSettings;
    private String address;
}
