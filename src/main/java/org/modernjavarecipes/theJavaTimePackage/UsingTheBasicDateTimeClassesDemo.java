package org.modernjavarecipes.theJavaTimePackage;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class UsingTheBasicDateTimeClassesDemo {
    public static void main(String[] args) {
        //Date now factory method
        System.out.println("Instant.now(): " + Instant.now());
        System.out.println("LocalDate.now(): " + LocalDate.now());
        System.out.println("LocalTime.now(): " + LocalTime.now());
        System.out.println("LocalDateTime.now(): " + LocalDateTime.now());
        System.out.println("ZonedDateTime.now(): " + ZonedDateTime.now());

        //All output values are using the ISO 8601 standard formatting. For dates, the basic for‐
        //mat is yyyy-MM-dd. For times, the format is hh:mm:ss.sss.

        //The of method for the date/time classes
        System.out.println("First landing on the Moon:");
        LocalDate moonLandingDate = LocalDate.of(1969, Month.JULY, 20);
        LocalTime moonLandingTime = LocalTime.of(20, 18);
        System.out.println("Date: " + moonLandingDate);
        System.out.println("Time: " + moonLandingTime);
        System.out.println("Neil Armstrong steps onto the surface: ");
        LocalTime walkTime = LocalTime.of(20, 2, 56, 150_000_000);
        LocalDateTime walk = LocalDateTime.of(moonLandingDate, walkTime);
        System.out.println(walk);

        Set<String> regionNames = ZoneId.getAvailableZoneIds();
        System.out.println("There are " + regionNames.size() + " region names");

        //Applying a time zone to a LocalDateTime
        LocalDateTime dateTime = LocalDateTime.of(2017, Month.JULY, 4, 13, 20, 10);
        ZonedDateTime nyc = dateTime.atZone(ZoneId.of("America/New_York"));
        System.out.println(nyc);
        ZonedDateTime london = nyc.withZoneSameInstant(ZoneId.of("Europe/London"));
        System.out.println(london);

        //Some methods in the Month enum
        System.out.println("Days in Feb in a leap year: " +
                Month.FEBRUARY.length(true));
        System.out.println("Day of year for first day of Aug (leap year): " +
                Month.AUGUST.firstDayOfYear(true));
        System.out.println("Month.of(1): " + Month.of(1));
        System.out.println("Adding two months: " + Month.JANUARY.plus(2));
        System.out.println("Subtracting a month: " + Month.MARCH.minus(1));

        //Creating Dates and Times from Existing Instances
        //Once you’ve created a LocalDate, LocalTime, LocalDateTime, or ZonedDateTime,
        //it can no longer be changed. each returns a new instance, which is a copy of the original with the added
        //amount.

        //Using plus methods on LocalDate and LocalTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.of(2017, Month.FEBRUARY, 2);
        LocalDate end = start.plusDays(3);
        assertEquals("2017-02-05", end.format(formatter));
        end = start.plusWeeks(5);
        assertEquals("2017-03-09", end.format(formatter));
        end = start.plusMonths(7);
        assertEquals("2017-09-02", end.format(formatter));
        end = start.plusYears(2);
        assertEquals("2019-02-02", end.format(formatter));

        formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime startLocaltime = LocalTime.of(11, 30, 0, 0);
        LocalTime endLocaltime = startLocaltime.plusNanos(1_000_000);
        assertEquals("11:30:00.001", end.format(formatter));
        startLocaltime = startLocaltime.plusSeconds(20);
        assertEquals("11:30:20", end.format(formatter));
        startLocaltime = startLocaltime.plusMinutes(45);
        assertEquals("12:15:00", end.format(formatter));
        startLocaltime = startLocaltime.plusHours(5);
        assertEquals("16:30:00", end.format(formatter));

        Period period = Period.of(2, 3, 4); // 2 years, 3 months, 4 days
        LocalDateTime startLocalDateTime = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
        LocalDateTime endLocalDateTime = startLocalDateTime.plus(period);
        assertEquals("2019-05-06T11:30:00",
                startLocalDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        startLocalDateTime = startLocalDateTime.plus(3, ChronoUnit.HALF_DAYS);
        assertEquals("2017-02-03T23:30:00",
                startLocalDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        startLocalDateTime = startLocalDateTime.minus(period);
        assertEquals("2014-10-29T11:30:00",
                startLocalDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        startLocalDateTime = startLocalDateTime.minus(2, ChronoUnit.CENTURIES);
        assertEquals("1817-02-02T11:30:00",
                startLocalDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        startLocalDateTime = startLocalDateTime.plus(3, ChronoUnit.MILLENNIA);
        assertEquals("5017-02-02T11:30:00",
                startLocalDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        LocalDateTime start2 = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
        LocalDateTime end2 = start2.withMinute(45);
        assertEquals("2017-02-02T11:45:00",
                start2.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        end2 = start2.withHour(16);
        assertEquals("2017-02-02T16:30:00",
                end2.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        end2 = start2.withDayOfMonth(28);
        assertEquals("2017-02-28T11:30:00",
                end2.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        end2 = start2.withDayOfYear(300);
        assertEquals("2017-10-27T11:30:00",
                end2.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        end2 = start2.withYear(2020);
        assertEquals("2020-02-02T11:30:00",
                end2.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        LocalDateTime start3 = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
        start3.withDayOfMonth(29);

        //Adjusting the month to an invalid value
        LocalDateTime start4 = LocalDateTime.of(2017, Month.JANUARY, 31, 11, 30);
        LocalDateTime end4 = start4.with(ChronoField.MONTH_OF_YEAR, 2);
        assertEquals("2017-02-28T11:30:00",
                end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));


    }
}
