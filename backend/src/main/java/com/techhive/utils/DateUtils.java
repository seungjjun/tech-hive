package com.techhive.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final ZoneId SEOUL_ZONE_ID = ZoneId.of("Asia/Seoul");
    
    public static LocalDateTime convertToLocalDateTimeFromDate(String date, DateTimeFormatter dateTimeFormatter) {
        LocalTime currentTime = LocalTime.now();
        return LocalDate.parse(date, dateTimeFormatter).atTime(currentTime);
    }

    public static LocalDateTime convertToLocalDateTimeFromDateTime(String dateTime) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTime).withZoneSameInstant(SEOUL_ZONE_ID);
        return zonedDateTime.toLocalDateTime();
    }
}
