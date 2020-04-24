package org.modernjavarecipes.java9;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class DateRangesDemo {
    public static void main(String[] args) {
        //You want a stream of dates between two given endpoints.
        //Use the new datesUntil method in the LocalDate class, added in Java 9

        LocalDate start = LocalDate.of(2017, Month.JUNE, 10);
        LocalDate end = LocalDate.of(2017, Month.JUNE, 17);
        System.out.println(getDays_java8(start, end));
        // [2017-06-10, 2017-06-11, 2017-06-12, 2017-06-13,
        // 2017-06-14, 2017-06-15, 2017-06-16]

        start = LocalDate.of(2017, Month.JUNE, 10);
        end = LocalDate.of(2017, Month.JULY, 17);
        System.out.println(getDays_java8(start, end));
        // []
        //The problem is that the getDays method on Period returns the days field from the period, not the number of days total.

    }

    //Days between two dates (WARNING: BUG!)
    public static List<LocalDate> getDays_java8(LocalDate start, LocalDate end) {
        Period period = start.until(end);
        return IntStream.range(0, period.getDays())
                .mapToObj(start::plusDays)
                .collect(Collectors.toList());
    }

    //Days between two dates (WORKS)
    public List<LocalDate> getDays_java8_done(LocalDate start, LocalDate end) {
        Period period = start.until(end);
        return LongStream.range(0, ChronoUnit.DAYS.between(start, end))
                .mapToObj(start::plusDays)
                .collect(Collectors.toList());
    }

    //Iterating on LocalDate
    public List<LocalDate> getDaysByIterate(LocalDate start, int days) {
        return Stream.iterate(start, date -> date.plusDays(1))
                .limit(days)
                .collect(Collectors.toList());
    }

    //Date ranges in Java 9
    public List<LocalDate> getDays_java9(LocalDate start, LocalDate end) {
        return start.datesUntil(end).collect(Collectors.toList());
    }
    public List<LocalDate> getMonths_java9(LocalDate start, LocalDate end) {
        return start.datesUntil(end, Period.ofMonths(1)).collect(Collectors.toList());
    }
}
