package com.example.testcode.parameterized;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FindSundayTest {


    @Test
    @DisplayName("LocalDate(yyyy-mm-dd)를 입력했을때 일요일을 찾는다. - success")
    public void givenLocalDate_whenFindFirstSunday_thenFirstSunday() {
        //given
        LocalDate date = LocalDate.of(2024, 9, 29);
        //when
        LocalDate result = FindSunday.findFirstSunday(date);
        //then
        assertEquals(LocalDate.of(2024, 9, 29), result);

    }

    @Test
    @DisplayName("날짜 포맷이 맞지않게(yyyy-mm-dd형식이아닌) 12345 입력했을때 실패한다.")
    public void givenWrongLocalDateForMat_whenFindFirstSunday_thenFirstSunday() {
        /* 기존 코드
        //given
        LocalDate date = LocalDate.of(12345, 12345, 12345);
        //when
        LocalDate result = FindSunday.findFirstSunday(date);

        // assertThrows 메서드가 예외를 발생시키는 코드를 람다 표현식으로 감싸야 한다
        //then
        assertThrows(DateTimeException.class, () -> FindSunday.findFirstSunday(date));
        */

        /*
        에초에 테스트작성시나리오가 잘못되었다 given에서 이미 예외가 발생하기 때문
        현재 상황에선 아래와 같이 테스트를 작성해야한다.
        혹은 테스트할 메소드에 LocalDate로 변환작업이 있어야지 기존 시나리오 검증이 된다
        */

        //then
        assertThrows(DateTimeException.class, () -> {
            //given
            LocalDate date = LocalDate.of(12345, 12345, 12345);
            //when
            FindSunday.findFirstSunday(date);
        });

    }

    @Test
    @DisplayName("경계테스트 - -999999999년을 입력해도 정상적으로 반환한다 - success!")
    public void givenMinLocalDate_whenFindFirstSunday_thenFirstSunday() {
        //given
        LocalDate date = LocalDate.MIN;
        //when
        LocalDate result = FindSunday.findFirstSunday(date);
        //then
        assertEquals(LocalDate.of(-999999999, 01, 7), result);

    }


    @Test
    @DisplayName("경계테스트 - 999999999년을 입력하면 정상적으로 반환이 되지않는다")
    public void givenMaxLocalDate_whenFindFirstSunday_thenFirstSunday() {
        //given
        LocalDate date = LocalDate.MAX;
        //when
        //LocalDate result = FindSunday.findFirstSunday(date);
        //then
        //assertThrows는 람다식으로 표현을 하게되면 give-when-then형식이 깨지게된다?
        assertThrows(DateTimeException.class, () -> FindSunday.findFirstSunday(date));

    }


    /*
    @ValueSource 어노테이션을 사용하여 여러개의 값을 테스트에 주입할 수 있다.
    특징은 기본형타입만 지원되며 배열형태로 설정이된다 그로고 하나씩 반복실행하게된다
    */
    @ParameterizedTest //@Test 대신 @ParameterizedTest를 사용한다.
    @DisplayName("@ValueSource 어노테이션을 사용하여 여러개의 값을 테스트에 주입할 수 있다. - success!")
    @ValueSource(strings = {"2024-09-30", "2024-10-01", "2024-10-02", "2024-10-03", "2024-10-04", "2024-10-05"})
    public void valueSourceTest(String date) {
        //given
        LocalDate localDate = LocalDate.parse(date);
        //when
        LocalDate result = FindSunday.findFirstSunday(localDate);
        //then
        assertEquals(LocalDate.of(2024, 10, 6), result);
    }



    //@EnumSource 어노테이션은 Enum 타입의 값을 테스트에 주입할 수 있다.
    //특정 Enum 타입을 지정하면 해당 Enum의 모든 값을 테스트에 주입한다.
    @ParameterizedTest //@Test 대신 @ParameterizedTest를 사용한다.
    @DisplayName("@EnumSource 어노테이션을 사용하여 여러개의 값을 테스트에 주입할 수 있다. - success!")
    @EnumSource(Day.class)
    //@EnumSource(value = Day.class, names = {"Monday", "Tuesday"}) // Day enum의 Monday, Tuesday만 테스트에 주입
    public void enumSourceTest(Day day) {
        //given
        LocalDate localDate = LocalDate.parse(day.getDate());
        //when
        LocalDate result = FindSunday.findFirstSunday(localDate);
        //then
        assertEquals(LocalDate.of(2024, 10, 6), result);

    }

    /*
    @valueSource어노테이션은 기본형만 지원하였으나
    @MethodSource어노테이션을 사용하면 복잡한객체나 여러가지 다양한타입의 데이터를 전달가능
    데이터를 제공할 메소드는 static으로 선언되어야하며
    !!Collection!!을 반환해야한다.
    가장 많이사용하는 Stream<T>, Iterable<T>, Iterator<T>, Object[]를 반환해야한다.
    그리고 해당 메소드는 테스트클래스나 외부클래스에존재해도된다
    */
    @ParameterizedTest
    @DisplayName("MethodSource 이용한 테스트")
    @MethodSource("testMethodSource")
    public void methodSourceTest(LocalDate date) {
        //given
        //when
        LocalDate result = FindSunday.findFirstSunday(date);
        //then
        assertEquals(LocalDate.of(2024, 10, 6), result);


    }

    // test테스트에 사용될 데이터 스트림으로 반환
    private static Stream<LocalDate> testMethodSource() {
        return Stream.of(LocalDate.of(2024, 9, 30));
    }


    /*
    Csv형태는 String으로 반환하지만
    Junit5에서는 자동으로 타입변환을 해준다. 즉 LocalDate로 받아도 된다.
    */
    @ParameterizedTest
    @DisplayName("CsvSource 이용한 테스트")
    @CsvSource({
            "20241004",
            "2024/10/05"
    })
    public void csvSourceTest(LocalDate date) {
        //given
        //when
        LocalDate result = FindSunday.findFirstSunday(date);
        //then
        assertThrows(ParameterResolutionException.class, () -> FindSunday.findFirstSunday(date));
    }



    /*
    @MethodSource와 다르게 ArgumentsProvider 라는 커스텀데이터 제공 클래스를 통해 데이터를 제공한다
    리턴타입은 ArgumentProvider의 ArgumentsStream이다. (Stream<? extends Arguments>)
    항상 외부클래스로 구현해야하고 ArgumentsProvider 인터페이스를 반드시 구현해야한다.
    이것역시도 자동으로 형변환을 지원해준다
    */
    @ParameterizedTest
    @DisplayName("@ArgumentSource 이용한 테스트")
    @ArgumentsSource(CustomArgumentsProvider.class)
    public void argumentSourceTest(LocalDate date) {
        //given
        //when
        LocalDate result = FindSunday.findFirstSunday(date);
        //then
        assertEquals(LocalDate.of(2024, 10, 6), result);
    }

}