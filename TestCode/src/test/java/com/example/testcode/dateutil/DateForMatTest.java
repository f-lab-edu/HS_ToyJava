package com.example.testcode.dateutil;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateForMatTest {


    @Test
    @Order(1)
    @DisplayName("SuccessCase-두 날짜의 차이를 구한다.(날짜형식 : yyyy-MM-dd / 입력타입은 String)")
    public void givenTwoDate_whenDiff_thenDiffDate() {
        //given
        String date1 = "2021-01-01";
        String date2 = "2021-01-23";

        //when
        int result = DateFormat.diffDate(date1, date2);

        //then
        assertEquals(22, result);

    }


    @Test
    @Order(2)
    @DisplayName("FailCase-날짜 형식이 yyyyMMdd로 입력되었을경우 (날짜형식 : yyyyMMdd / 입력타입은 String)")
    public void givenCurrentDate_whenDiff_thenDiffDate() {
        //given
        String date1 = "20240901";

        //when
        int result = DateFormat.diffDateFromNow(date1);

        //then
        assertEquals(22, result);
    }


    @Test
    @Order(3)
    @DisplayName("BoundaryCase-두 날짜가 같을경우")
    public void givenSameDate_whenDiff_thenDiffDate() {
        //given
        String date1 = "2021-01-01";
        String date2 = "2021-01-01";

        //when
        int result = DateFormat.diffDate(date1, date2);

        //then
        assertEquals(0, result);

    }


    @Test
    @Order(4)
    @DisplayName("BoundaryCase-윤년을 계산한 날짜 비교 테스트")
    public void givenLeapYearDate_whenDiff_thenDiffDate() {
        //given
        String date1 = "2021-02-28";
        String date2 = "2021-03-01";

        //when
        int result = DateFormat.diffDate(date1, date2);

        //then
        assertEquals(1, result);

    }


    @Test
    @Order(5)
    @DisplayName("BoundaryCase-최소및 최대날짜 비교테스트")
    public void givenMinAndMaxDate_whenDiff_thenDiffDate() {
        //given
        String date1 = String.valueOf(LocalDate.MIN);
        String date2 = String.valueOf(LocalDate.MAX);

        //when
        int result = DateFormat.diffDate(date1, date2);

        //then
        assertEquals(3652058, result);

    }
}