package fast.campus.netplix.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LocalDateTimeUtil {
    public static LocalDateTime atStartOfDay(LocalDateTime localDateTime) {
        return localDateTime.truncatedTo(ChronoUnit.DAYS);
    }

    public static LocalDateTime atEndOfDay(LocalDateTime localDateTime) {
        return localDateTime.plusDays(1).truncatedTo(ChronoUnit.DAYS);
    }
}
