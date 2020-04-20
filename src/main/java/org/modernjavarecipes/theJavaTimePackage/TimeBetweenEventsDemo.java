package org.modernjavarecipes.theJavaTimePackage;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class TimeBetweenEventsDemo {

    public static void main(String[] args) {
        //Days to Election Day
        LocalDate electionDay = LocalDate.of(2020, Month.NOVEMBER, 3);
        LocalDate today = LocalDate.now();
        System.out.printf("%d day(s) to go...%n", ChronoUnit.DAYS.between(today, electionDay));
        System.out.printf("%d day(s) to go...%n", today.until(electionDay).getDays());

        //Using Period to get days, months, and years
        electionDay = LocalDate.of(2020, Month.NOVEMBER, 3);
        today = LocalDate.now();
        java.time.Period until = today.until(electionDay);
        int years = until.getYears();
        int months = until.getMonths();
        int days = until.getDays();
        System.out.printf("%d year(s), %d month(s), and %d day(s)%n", years, months, days);

        Instant start = Instant.now();
        // ... call method to be timed ...
        Instant end = Instant.now();
        System.out.println(getTiming(start, end) + " seconds");
    }

    //Timing a method
    public static double getTiming(Instant start, Instant end) {
        return Duration.between(start, end).toMillis() / 1000.0;
    }
}
