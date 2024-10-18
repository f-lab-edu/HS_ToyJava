package com.example.mylist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
add는 기본적으로 성공이 되야되는데 실패와 경계케이스를 생각할수있을까?..
그리고 실패케이스라고 생각해도 타입이 안맞는 경우인데
즉 컴파일에러의 경우는 테스트코드 작성 기준이 아니지않을까?
우리는 런타임의 예외를 잡기위해 테스트코드를 작성하는것이 아닌가?
 */
public class MyListAddTest {

    @Test
    @DisplayName("MyList객체에 add메서드를 이용하여 요소를 추가할 수 있다 기본크기 5을 넘어서 추가되는경우 크기가 늘어난다")
    public void addElement() {
        MyList<String> myList = new MyList<>();
        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("4");
        myList.add("5");
        myList.add("6");
        myList.add("7");
        myList.add("8");


        assertEquals(8, myList.size());
        assertEquals("1", myList.get(0));
        assertEquals("2", myList.get(1));
        assertEquals("7", myList.get(6));
        assertEquals("8", myList.get(7));
    }



    @Test
    @DisplayName("인덱스 중간에 요소를 추가할경우 해당인덱스에 요소가 추가되고 기존요소들은 뒤로 밀린다")
    public void addElementWithIndex() {
        MyList <String> myList = new MyList<>();
        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("4");
        myList.add("5");
        myList.add(2, "SSSS");

        assertEquals(5, myList.size());
        assertEquals("SSSS", myList.get(2));
    }

    @Test
    @DisplayName("addAll")
    public void addAllTest() {
        MyList<String> myList = new MyList<>();
        ArrayList<String> array = new ArrayList<>();

        array.add("1");
        array.add("2");
        array.add("3");

        myList.addAll(array);

        assertEquals(3, myList.size());
        assertEquals("1", myList.get(0));
        assertEquals("2", myList.get(1));
        assertEquals("3", myList.get(2));

    }

    @Test
    @DisplayName("컬렉션addAll시에 인덱스를 추가하면 해당 위치에서 부터 추가된다")
    public void addAllWithindexCollection() {
        MyList<String> myList = new MyList<>();
        ArrayList<String> array = new ArrayList<>();

        myList.add("1");
        myList.add("2");
        myList.add("3");

        array.add("A");
        array.add("B");
        array.add("C");


        myList.addAll(2, array);

        assertEquals(5, myList.size());
        assertEquals("1", myList.get(0));
        assertEquals("A", myList.get(2));
        assertEquals("C", myList.get(4));
    }


    @Test
    @DisplayName("인덱스가 음수이거나 MyList배열의 크기보다 큰 인덱스인경우 IndexOutOfBoundsException이 발생한다")
    public void addElementWithNegativeIndex() {
        MyList <String> myList = new MyList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            myList.add(-1, "SSSS");
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            myList.add(100, "SSSS");
        });

    }




}
