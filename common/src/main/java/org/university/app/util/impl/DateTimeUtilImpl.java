package org.university.app.util.impl;

import org.springframework.stereotype.Component;
import org.university.app.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Component
public class DateTimeUtilImpl implements DateTimeUtil {
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    @Override
    public LocalDateTime stringToLocalDateTime(String dateTimeStr) {

        return LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
    }

    @Override
    public String dateTimeToStr(LocalDateTime dateTime) {
        return dateTime == null ? "" : dateTime.format(DATE_TIME_FORMATTER);
    }

    @Override
    public LocalDate stringToLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    @Override
    public String dateToStr(LocalDate date) {
        return date == null ? "" : date.format(DATE_FORMATTER);
    }
}
