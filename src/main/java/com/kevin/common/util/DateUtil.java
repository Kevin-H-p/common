package com.kevin.common.util;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;


public class DateUtil {

    public static List<LocalDate> getMonthsLastDay(LocalDate start,LocalDate end){
        LocalDate cursor = start.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate endSunday = end.with(TemporalAdjusters.lastDayOfMonth());
        List<LocalDate> ret = new ArrayList<>();
        while (cursor.isBefore(endSunday) || cursor.isEqual(endSunday)) {
            ret.add(cursor);
            cursor = cursor.plusDays(1).with(TemporalAdjusters.lastDayOfMonth());
        }
        return ret;
    }
    public static List<LocalDate> getMonthsFirstDay(LocalDate start,LocalDate end){
        LocalDate cursor = start.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endSunday = end.with(TemporalAdjusters.firstDayOfMonth());
        List<LocalDate> ret = new ArrayList<>();
        while (cursor.isBefore(endSunday) || cursor.isEqual(endSunday)) {
            ret.add(cursor);
            cursor = cursor.plusDays(1).with(TemporalAdjusters.firstDayOfMonth());
        }
        return ret;
    }

}
