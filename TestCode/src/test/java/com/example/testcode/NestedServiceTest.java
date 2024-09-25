package com.example.testcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/* Nested 테스트로 작성시 장점은 테스트 코드를 더욱 구조적으로 작성할 수 있다.
   또한 흐름을 쉽게 알수있으므로 초보자에게 굉장히 테스트 코드를 이해하기 쉽게 만들어준다.(직접 느낀점)
   장단점이 무엇이있을까?
*/
class NestedServiceTest {

    @Nested
    @DisplayName("정수 연산 테스트 시작합니다")
    class FirstTest {

        @Nested
        @DisplayName("정상적인 연산이 성공했을 경우 success")
        class Start {

            @Test
            @DisplayName("두 Long 타입의 숫자를 더할시 정수의 합을 반환한다.")
            void firstTest() {
                //given
                Long a = 1L;
                Long b = 2L;
                //when
                Long result = a + b;
                //then
                assertEquals(3L, result);
            }
        }

        @Nested
        @DisplayName("뺄셈 연산시 값이 같이 않을 경우 fail")
        class Fail {

            @Test
            @DisplayName("두정수를 뺄경우 값이 같지 않을경우 실패한다.")
            void secondTest() {
                //given
                Long a = 1L;
                //Long b = 2L;
                Long b = 3L;

                //when
                Long result = a - b;
                //then
                assertEquals(-1L, result);
            }
        }

        @Nested
        @DisplayName("경계테스트")
        class BoundaryTest {

            //최소값 테스트
            @Test
            @DisplayName("Long의 최소값과 2를 더할경우 성공하는지 확인?")
            void thirdTest() {
                //given
                Long a = Long.MIN_VALUE;
                Long b = 2L;
                //when
                Long result = a + b;
                //then
                assertEquals(-9223372036854775806L, result);

            }

            //최대값 테스트 & overflow 테스트
            @Test
            @DisplayName("Long의 최대값과 2를 더할경우 성공하는지 확인?")
            void fourthTest() {
                //given
                Long a = Long.MAX_VALUE;
                Long b = 2L;
                //when
                Long result = a + b;
                //then
                assertEquals(9223372036854775807L, result);
            }

            //0값 테스트
            @Test
            @DisplayName("Long의 0값과 2를 더할경우 성공하는지 확인?")
            void fifthTest() {
                //given
                Long a = 0L;
                Long b = 2L;
                //when
                Long result = a + b;
                //then
                assertEquals(2L, result);
            }

        }
    }

}