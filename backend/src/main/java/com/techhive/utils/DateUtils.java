package com.techhive.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {


    private static final DateTimeFormatter ENG_FORMATTER = DateTimeFormatter.ofPattern("MMM'.'dd'.'yyyy", Locale.ENGLISH);
    private static final DateTimeFormatter KOR_FORMATTER = DateTimeFormatter.ofPattern("yyyy'년' M'월' d'일'", Locale.KOREAN);

    public static LocalDateTime convertToLocalDateTimeFromEngDate(String date) {
        LocalTime currentTime = LocalTime.now();
        return LocalDate.parse(date, ENG_FORMATTER).atTime(currentTime);
    }

    public static LocalDateTime convertToLocalDateTimeFromKorDate(String date) {
        LocalTime currentTime = LocalTime.now();
        return LocalDate.parse(date, KOR_FORMATTER).atTime(currentTime);
    }
}
