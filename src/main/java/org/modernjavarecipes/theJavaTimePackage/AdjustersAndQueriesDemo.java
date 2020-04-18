package org.modernjavarecipes.theJavaTimePackage;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class AdjustersAndQueriesDemo {
    public static void main(String[] args) {

    }

    //Using static methods in TemporalAdjusters
    @Test
    public void adjusters() throws Exception {
        LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
        LocalDateTime end = start.with(TemporalAdjusters.firstDayOfNextMonth());
        assertEquals("2017-03-01T11:30", end.toString());
        end = start.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        assertEquals("2017-02-09T11:30", end.toString());
        end = start.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY));
        assertEquals("2017-02-02T11:30", end.toString());
    }

    public class PaydayAdjuster implements TemporalAdjuster {
        public Temporal adjustInto(Temporal input) {
            LocalDate date = LocalDate.from(input);
            int day;
            if (date.getDayOfMonth() < 15) {
                day = 15;
            } else {
                day = date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
            }
            date = date.withDayOfMonth(day);
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                date = date.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
            }
            return input.with(date);
        }
    }

    //Testing the adjuster for July 2017
    @Test
    public void payDay() throws Exception {
        TemporalAdjuster adjuster = new PaydayAdjuster();
        IntStream.rangeClosed(1, 14)
                .mapToObj(day -> LocalDate.of(2017, Month.JULY, day))
                .forEach(date ->
                        assertEquals(14, date.with(adjuster).getDayOfMonth()));
        IntStream.rangeClosed(15, 31)
                .mapToObj(day -> LocalDate.of(2017, Month.JULY, day))
                .forEach(date ->
                        assertEquals(31, date.with(adjuster).getDayOfMonth()));
    }

    //Utility class with adjusters
    public static class Adjusters {
        public static Temporal adjustInto(Temporal input) {
            LocalDate date = LocalDate.from(input);
            // ... implementation as before ...
            return input.with(date);
        }
    }

    @Test
    public void payDayWithMethodRef() throws Exception {
        IntStream.rangeClosed(1, 14)
                .mapToObj(day -> LocalDate.of(2017, Month.JULY, day))
                .forEach(date ->
                        assertEquals(14,
                                date.with(Adjusters::adjustInto).getDayOfMonth()));
        IntStream.rangeClosed(15, 31)
                .mapToObj(day -> LocalDate.of(2017, Month.JULY, day))
                .forEach(date ->
                        assertEquals(31,
                                date.with(Adjusters::adjustInto).getDayOfMonth()));
    }
}
