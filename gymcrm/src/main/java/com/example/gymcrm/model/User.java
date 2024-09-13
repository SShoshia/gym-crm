package com.example.gymcrm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.gymcrm.util.RandomStringGenerator.generateRandomString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Boolean isActive;

    public static String generateUsername(String firstName, String lastName) {
        return firstName + "." + lastName;
    }

    public static String generatePassword() {
        return generateRandomString(PASSWORD_LENGTH);
    }

    public static final int PASSWORD_LENGTH = 10;
}
