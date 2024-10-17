package com.example.mylist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class MyListConstructorTest {

    //사이즈 지정하지않은 생성자 테스트
    @Test
    @DisplayName("사이즈지정하지않은 CustomList생성시 정상적으로 생성이된다")
    void createCustomList() {
        MyList<String> myList = new MyList<>();
        assertNotNull(myList);
    }

    //======================================================================
    // 사이즈 지정 생성자 테스트


    //성공테스트 && 최대값
    @Test
    @DisplayName("CustomList객체 생성시 사이즈를 지정하여도 생성된다")
    void createCustomListWithSize() {
        //1억은 되는데 10억은 안된다 메모리한계로 예상
        MyList<String> myList = new MyList<>(100000000);
        assertNotNull(myList);
    }

    //실패테스트 && 최소값
    @Test
    @DisplayName("CustomList객체 생성시 사이즈를 음수로 지정하면 IllegalArgumentException이 발생한다")
    void createCustomListWithSizeMinusParameter() {
        assertThrows(IllegalArgumentException.class, () -> {
            MyList<String> myList = new MyList<>(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            MyList<String> myList = new MyList<>(Integer.MIN_VALUE);
        });
    }

    //======================================================================
    // 컬렉션 복사 생성자 테스트


    //Set과 ArrayList를 분리하지 않고 한번에 테스트를 진행해보았다
    @Test
    @DisplayName("CustomList 객체 복사 생성 테스트")
    void createCustomListWithCollection() {
        ArrayList<String> array = new ArrayList<>();
        array.add("a");
        array.add("b");
        array.add("c");

        HashSet<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");

        MyList<String> copiedList = new MyList<>(array);
        MyList<String> copiedList2 = new MyList<>(set);

        assertEquals(array.get(1), copiedList.get(1));
        //set은 순서가 없기때문에 순서가 다를수있다 다른방식을 생각해보자
        //assertEquals(set.contains("a"), copiedList2.get(0));

    }

    //실패테스트 경계 테스트가 생각이안난다...

}