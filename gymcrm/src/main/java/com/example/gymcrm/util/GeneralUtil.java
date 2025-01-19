package com.example.gymcrm.util;

public class GeneralUtil {

    public static <T> T defaultIfNull(T value, T defaultValue) {
        return (value != null) ? value : defaultValue;
    }

}
