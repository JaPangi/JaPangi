package io.github.japangiserver.util.datefomat;

import java.time.format.DateTimeFormatter;

/** NOTE
 * 날짜 형식 지정 유틸 클래스
 */
public class DateFormatter {

    /** NOTE
     * 년.월.일 형식 포맷팅
     */
    public static DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    /** NOTE
     * 년.월 형식 포맷팅
     */
    public static DateTimeFormatter getMonthFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM");
    }
}
