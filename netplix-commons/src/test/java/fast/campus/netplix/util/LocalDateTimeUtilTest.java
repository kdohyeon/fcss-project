package fast.campus.netplix.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalDateTimeUtilTest {

    @Test
    public void test1() {
        // given
        LocalDateTime now = LocalDateTime.of(2024, 9, 18, 14, 15, 49);

        // when
        LocalDateTime startOfDay = LocalDateTimeUtil.atStartOfDay(now);

        // then
        assertEquals(2024, startOfDay.getYear());
        assertEquals(9, startOfDay.getMonthValue());
        assertEquals(18, startOfDay.getDayOfMonth());
        assertEquals(0, startOfDay.getHour());
        assertEquals(0, startOfDay.getMinute());
        assertEquals(0, startOfDay.getSecond());
    }

    @Test
    public void test2() {
        // given
        LocalDateTime now = LocalDateTime.of(2024, 9, 18, 14, 15, 49);

        // when
        LocalDateTime endOfDay = LocalDateTimeUtil.atEndOfDay(now);

        // then
        assertEquals(2024, endOfDay.getYear());
        assertEquals(9, endOfDay.getMonthValue());
        assertEquals(19, endOfDay.getDayOfMonth());
        assertEquals(0, endOfDay.getHour());
        assertEquals(0, endOfDay.getMinute());
        assertEquals(0, endOfDay.getSecond());
    }
}