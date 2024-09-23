package com.example.testcode.stringutil;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {


    @DisplayName("문자열 뒤집기 테스트입니다")
    @Nested
    class ReverseTest {


        @Test
        @DisplayName("문자열을 뒤집는다.")
        public void givenString_whenReverse_thenReversedString() {
            //given
            String str = "Hello World";

            //when
            String result = StringUtil.reverse(str);

            //then
            assertEquals("dlroW olleH", result);

        }

        @Test
        @DisplayName("숫자가 포함된 문자열을 뒤집는다.")
        public void givenStringIncludeNumber_whenReverse_thenReversedString() {
            //given
            String str = "Hello World 123";

            //when
            String result = StringUtil.reverse(str);

            //then
            assertEquals("321 dlroW olleH", result);

        }

    }


    @DisplayName("문자열을 잘라서 나누어봅시다")
    @Test
    public void givenString_whenSplit_thenSplitedString() {
        //given
        String str = "Hello World";

        //when
        String[] result = StringUtil.split(str);

        //then
        assertArrayEquals(new String[]{"Hello", "World"}, result);
    }


    @DisplayName("문자열에 대문자 발견시 새로운 문자열에 보관합니다")
    @Test
    public void givenString_whenFindUpperCase_thenNewString() {
        //given
        String str = "Hello World";

        //when
        List result = StringUtil.findUpperCase(str);

        //then
        assertEquals(2, result.size());
        assertEquals("Hello", result.get(0));
        assertEquals("World", result.get(1));



    }
}