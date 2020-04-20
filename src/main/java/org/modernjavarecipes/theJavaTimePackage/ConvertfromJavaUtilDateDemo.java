package org.modernjavarecipes.theJavaTimePackage;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ConvertfromJavaUtilDateDemo {
    public static void main(String[] args) {

    }

    //Converting java.util.Date to java.time.LocalDate via Instant
    public LocalDate convertFromUtilDateUsingInstant(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    //Converting a java.util.Date to a java.time.LocalDate
    public LocalDate convertUtilDateToLocalDate(java.util.Date date) {
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    //Converting from java.util.Calendar to java.time.ZonedDateTime
    public ZonedDateTime convertFromCalendar(Calendar cal) {
        return ZonedDateTime.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId());
    }

    //Using getter methods from Calendar to LocalDateTime
    public LocalDateTime convertFromCalendarUsingGetters(Calendar cal) {
        return LocalDateTime.of(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.HOUR),
                cal.get(Calendar.MINUTE),
                cal.get(Calendar.SECOND));
    }

    //Generating and parsing a timestamp string
    public LocalDateTime convertFromUtilDateToLDUsingString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(df.format(date), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    //Converting a GregorianCalendar to a ZonedDateTime
    public ZonedDateTime convertFromGregorianCalendar(Calendar cal) {
        return ((GregorianCalendar) cal).toZonedDateTime();
    }

    //Converting java.util.Date to java.time.LocalDate (JAVA 9 ONLY)
    public LocalDate convertFromUtilDateJava9(Date date) {
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public class ConvertDate {
        public LocalDate convertFromSqlDatetoLD(java.sql.Date sqlDate) {
            return sqlDate.toLocalDate();
        }
        public java.sql.Date convertToSqlDateFromLD(LocalDate localDate) {
            return java.sql.Date.valueOf(localDate);
        }
        public LocalDateTime convertFromTimestampToLDT(Timestamp timestamp) {
            return timestamp.toLocalDateTime();
        }
        public Timestamp convertToTimestampFromLDT(LocalDateTime localDateTime) {
            return Timestamp.valueOf(localDateTime);
        }
    }
}
