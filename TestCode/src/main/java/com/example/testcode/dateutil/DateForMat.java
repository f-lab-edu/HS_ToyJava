package com.example.testcode.dateutil;

import java.time.LocalDate;

public class DateForMat {

    public static int diffDate(String date1, String date2) {

        LocalDate localDate1 = LocalDate.parse(date1);
        LocalDate localDate2 = LocalDate.parse(date2);

        // 두 날짜가 같은지 확인
        boolean sameDay = localDate1.isEqual(localDate2);
        // date1이 date2보다 이전인지 확인
        boolean isBefore = localDate1.isBefore(localDate2);
        // date1이 date2보다 이후인지 확인
        boolean isAfter = localDate1.isAfter(localDate2);
        // 두 날짜 사이의 일수 계산
        int diff = localDate1.until(localDate2).getDays();

        return diff;
    }

    public static int diffDateFromNow(String date1) {

        LocalDate localDate1 = LocalDate.parse(date1);
        LocalDate localDate2 = LocalDate.now();

        // 두 날짜가 같은지 확인
        boolean sameDay = localDate1.isEqual(localDate2);
        // date1이 date2보다 이전인지 확인
        boolean isBefore = localDate1.isBefore(localDate2);
        // date1이 date2보다 이후인지 확인
        boolean isAfter = localDate1.isAfter(localDate2);
        // 두 날짜 사이의 일수 계산
        int diff = localDate1.until(localDate2).getDays();

        return diff;
    }

}
