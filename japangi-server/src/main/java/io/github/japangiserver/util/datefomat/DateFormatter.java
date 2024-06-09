package io.github.japangiserver.util.datefomat;

import java.time.format.DateTimeFormatter;

public class DateFormatter {
    public static DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public static DateTimeFormatter getMonthFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM");
    }
}
