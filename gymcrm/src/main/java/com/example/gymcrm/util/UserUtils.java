package com.example.gymcrm.util;

import static com.example.gymcrm.util.RandomStringGenerator.generateRandomString;

public class UserUtils {
    public static final int PASSWORD_LENGTH = 10;

    public static String generateUsername(String firstName, String lastName) {
        return firstName + "." + lastName;
    }

    public static String generatePassword() {
        return generateRandomString(PASSWORD_LENGTH);
    }
}
