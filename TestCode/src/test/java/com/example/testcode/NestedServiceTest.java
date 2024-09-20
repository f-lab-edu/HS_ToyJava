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
    @DisplayName("테스트 시작합니다")
    class FirstTest {

        @Nested
        @DisplayName("테스트가 성공하는 경우 (case1)")
        class start {

            @Test
            @DisplayName("덧셈하기")
            void FirstTest() {
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
        @DisplayName("테스트가 실패하는 경우 (case2)")
        class fail {

            @Test
            @DisplayName("뺄셈하기")
            void SecondTest() {
                //given
                Long a = 1L;
                Long b = 2L;
                //when
                Long result = a - b;
                //then
                assertEquals(-1L, result);
            }
        }
    }

}