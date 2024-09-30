package com.example.testcode.parameterized;

import java.time.LocalDate;
import java.util.stream.Stream;

public class FindSunday {


    public static LocalDate findFirstSunday(LocalDate date) {
        if (date.getDayOfWeek().getValue() == 7) {  // 날짜가 일요일인지 확인
            return date; // 만약 해당 날짜가 일요일이면 그 날짜를 반환
        }
        return findFirstSunday(date.plusDays(1)); // 일요일이 아니면 하루를 더하고 다시 함수 호출
    }

}
