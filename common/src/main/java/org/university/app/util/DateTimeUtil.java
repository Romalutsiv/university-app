package org.university.app.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DateTimeUtil {
    LocalDateTime stringToLocalDateTime(String dateTimeStr);
    String dateTimeToStr(LocalDateTime dateTime);
    LocalDate stringToLocalDate(String dateStr);
    String dateToStr(LocalDate date);

}
