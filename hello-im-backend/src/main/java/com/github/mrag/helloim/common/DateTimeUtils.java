package com.github.mrag.helloim.common;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

public final class DateTimeUtils {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = Optional.ofNullable(date)
                                  .orElseThrow(() -> new NullPointerException("传入的日期[date]为空"))
                                  .toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static Date localDateTime2Date(LocalDateTime ldt) {
        ldt = Optional.ofNullable(ldt).orElseThrow(() -> new NullPointerException("传入的日期[ldt]为空"));
        ZoneId zoneId = ZoneId.systemDefault();
        return Date.from(ldt.atZone(zoneId).toInstant());
    }

    public static String format(LocalDateTime localDateTime) {
        return DATE_TIME_FORMATTER.format(localDateTime);
    }

    public static LocalDateTime parse(String formatTime) {
        return LocalDateTime.parse(formatTime, DATE_TIME_FORMATTER);
    }
}
