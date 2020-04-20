package org.modernjavarecipes.theJavaTimePackage;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class FindingRegionNamesFromOffsets {
    public static void main(String[] args) {

    }

    //Getting region names given an offset
    public static List<String> getRegionNamesForOffset(ZoneOffset offset) {
        LocalDateTime now = LocalDateTime.now();
        return ZoneId.getAvailableZoneIds().stream()
                .map(ZoneId::of)
                .filter(zoneId -> now.atZone(zoneId).getOffset().equals(offset))
                .map(ZoneId::toString)
                .sorted()
                .collect(Collectors.toList());
    }

    //Get region names for a given offset
    public static List<String> getRegionNamesForZoneId(ZoneId zoneId) {
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zdt = now.atZone(zoneId);
        ZoneOffset offset = zdt.getOffset();
        return getRegionNamesForOffset(offset);
    }

    //Getting the current region names
    @Test
    public void getRegionNamesForSystemDefault() throws Exception {
        ZonedDateTime now = ZonedDateTime.now();
        ZoneId zoneId = now.getZone();
        List<String> names = getRegionNamesForZoneId(zoneId);
        assertTrue(names.contains(zoneId.getId()));
    }

    //Getting region names given an hour and minute offset
    public static List<String> getRegionNamesForOffset(int hours, int minutes) {
        ZoneOffset offset = ZoneOffset.ofHoursMinutes(hours, minutes);
        return getRegionNamesForOffset(offset);
    }

    //Testing region names for a given offset
    @Test
    public void getRegionNamesForGMT() throws Exception {
        List<String> names = getRegionNamesForOffset(0, 0);
        assertTrue(names.contains("GMT"));
        assertTrue(names.contains("Etc/GMT"));
        assertTrue(names.contains("Etc/UTC"));
        assertTrue(names.contains("UTC"));
        assertTrue(names.contains("Etc/Zulu"));
    }
    @Test
    public void getRegionNamesForNepal() throws Exception {
        List<String> names = getRegionNamesForOffset(5, 45);
        assertTrue(names.contains("Asia/Kathmandu"));
        assertTrue(names.contains("Asia/Katmandu"));
    }

    @Test
    public void getRegionNamesForChicago() throws Exception {
        ZoneId chicago = ZoneId.of("America/Chicago");
        List<String> names = this.getRegionNamesForZoneId(chicago);
        assertTrue(names.contains("America/Chicago"));
        assertTrue(names.contains("US/Central"));
        assertTrue(names.contains("Canada/Central"));
        assertTrue(names.contains("Etc/GMT+5") || names.contains("Etc/GMT+6"));
    }

}
