package com.example.testcode;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

//기본 어노테이션 사용법
class TestServiceTest {


    @BeforeAll //모든 테스트가 실행되기 전에 딱 한번 실행
    @DisplayName("한번만 실행할께요!")
    static void initAll() {
    }

    @BeforeEach //각 테스트가 실행되기 전에 실행
    void init() {
    }

    @Test //테스트 메소드
    @DisplayName("성공 테스트입니다!")
    void succeedingTest() {
        //성공
    }

    @Test //테스트 메소드
    void failingTest() {
        fail("a failing test"); //실패
    }

    @Test
    @Disabled("for demonstration purposes") //테스트를 비활성화
    void skippedTest() {
        // 비활성화된 테스트, 실행하지 않는다
    }

    @Test
    void abortedTest() {
        assumeTrue("abc".contains("Z")); //조건이 참이 아니면 테스트를 중단
        fail("test should have been aborted");
    }

    @AfterEach //각 테스트가 실행된 후에 실행
    void tearDown() {
    }

    @AfterAll //모든 테스트가 실행된 후에 딱 한번 실행
    static void tearDownAll() {
    }


}