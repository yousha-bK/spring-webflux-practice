package spring.webflux.practice.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/23/21
 */
public class TimeUtil {

    public static final String DETAIL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS Z";
    private static final ZoneId ZONE_ID_DHAKA = ZoneId.of("Asia/Dhaka");
    private static final DateTimeFormatter DETAIL_DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern(DETAIL_DATE_TIME_FORMAT);

    private TimeUtil() {
    }

    private static ZonedDateTime getZonedDateTime() {
        return ZonedDateTime.ofInstant(Instant.now(), ZONE_ID_DHAKA);
    }

    public static Long getCurrentTimestampInSecond() {
        return getZonedDateTime().toEpochSecond();
    }

    public static String getCurrentTimestamp() {

        return getZonedDateTime().format(DETAIL_DATE_TIME_FORMATTER);
    }

    public static String getTimestamp(Long timestamp) {

        return ZonedDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZONE_ID_DHAKA)
                .format(DETAIL_DATE_TIME_FORMATTER);
    }
}
