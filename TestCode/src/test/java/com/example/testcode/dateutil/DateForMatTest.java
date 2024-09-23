package com.example.testcode.dateutil;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateForMatTest {

    @Test
    @DisplayName("두 날짜의 차이를 구한다.(날짜형식 : yyyy-MM-dd / 입력타입은 String)")
    public void givenTwoDate_whenDiff_thenDiffDate() {
        //given
        String date1 = "2021-01-01";
        String date2 = "2021-01-23";

        //when
        int result = DateForMat.diffDate(date1, date2);

        //then
        assertEquals(22, result);

    }

    @Test
    @DisplayName("현재 날짜와 비교하여 날짜의 차이를 구해보자 (날짜형식 : yyyy-MM-dd / 입력타입은 String)")
    public void givenCurrentDate_whenDiff_thenDiffDate() {
        //given
        String date1 = "2024-09-01";

        //when
        int result = DateForMat.diffDateFromNow(date1);

        //then
        assertEquals(22, result);

    }

}