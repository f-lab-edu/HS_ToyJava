package com.example.mylist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {

    //사이즈 지정하지않은 생성자 테스트
    @Test
    @DisplayName("사이즈지정하지않은 CustomList생성시 빈 배열로 생성이된다")
    void createCustomList() {
        MyList<String> myList = new MyList<>();
        assertNotNull(myList);
        assertArrayEquals(new Object[0], myList.elementData);
        /*
        기본Capacity는 5를 확인하고싶었으나 private static final로 선언되어있어서 불가
        assertEquals(5, customList.DEFAULT_CAPACITY);
        어자피 내부 동작 변수니깐 테스트가필요없을까?
        -> 그러면 나중에 사이즈늘릴때 테스트는 어떻게해야할까?
         */
    }
    /*
    실패케이스와 경계테스트 진행이 가능한가?
    기본형태의 생성자는 실패케이스와 경계테스트가 떠오르지않는다
    */

    //======================================================================
    // 사이즈 지정 생성자 테스트


    //성공테스트
    @Test
    @DisplayName("CustomList객체 생성시 사이즈를 지정하면 해당 사이즈만큼 배열이 생성된다")
    void createCustomListWithSize() {
        MyList<String> myList = new MyList<>(10);
        assertArrayEquals(new Object[10], myList.elementData);
    }

    //실패테스트
    @Test
    @DisplayName("CustomList객체 생성시 사이즈를 음수로 지정하면 IllegalArgumentException이 발생한다")
    void createCustomListWithSizeMinusParameter() {
        assertThrows(IllegalArgumentException.class, () -> {
            MyList<String> myList = new MyList<>(-1);
        });
    }

    //최소값테스트
    @Test
    @DisplayName("CustomList객체 생성시 사이즈를 MIN_VALUE")
    void createCustomListWithSizeMIN_VALUE() {
        assertThrows(IllegalArgumentException.class, () -> {
            MyList<String> myList = new MyList<String>(Integer.MIN_VALUE);
        });
    }

    //MAX_VALUE는 사용하지않을것같아서 현실적인값으로 최댓값테스트를 해보았음
    @Test
    @DisplayName("CustomList객체 생성시 사이즈를 MAX_VALUE")
    void createCustomListWithSizeMAX_VALUE() {
        MyList<String> myList = new MyList<String>(1000000);
        assertArrayEquals(new Object[1000000], myList.elementData);
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

        assertArrayEquals(array.toArray(), copiedList.elementData);
        assertArrayEquals(set.toArray(), copiedList2.elementData);
    }

    //실패테스트 경계 테스트가 생각이안난다...

}