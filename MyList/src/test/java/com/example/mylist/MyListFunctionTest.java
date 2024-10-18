package com.example.mylist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MyListFunctionTest {

    private MyList<String> testList; // testList 변수를 클래스 레벨에서 선언

    @BeforeEach
    public void setUp() {
        // 각 테스트 전에 새로운 MyList 인스턴스를 생성
        testList = new MyList<>("1", "2", "3");
    }

    //get() 메서드 테스트
    @Test
    @DisplayName("get() 메서드를 이용하여 해당 인덱스의 요소를 가져올 수 있다")
    void get() {
        assertEquals("1", testList.get(0));
        assertEquals("2", testList.get(1));
        assertEquals("3", testList.get(2));

    }

    //elementData() 메서드 테스트
    @Test
    @DisplayName("elementData() 메서드를 이용하여 해당 인덱스의 요소를 가져올 수 있다, 유효하지않은 인덱스인경우 예외발생")
    void elementData() {


        assertEquals("1", testList.elementData(0));
        assertEquals("2", testList.elementData(1));
        assertEquals("3", testList.elementData(2));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            testList.elementData(3);
        });

    }

    //size() 메서드 테스트(실패케이스 경계테스트 없다고 판단)
    @Test
    @DisplayName("size() 메서드를 이용하여 리스트의 크기를 확인할 수 있다")
    void size() {
        assertEquals(3, testList.size());
    }

    //isEmpty() 메서드 테스트
    @Test
    @DisplayName("isEmpty() 메서드를 이용하여 리스트가 비어있는지 확인할 수 있다")
    void isEmpty() {
        MyList<String> myList = new MyList<>();

        assertEquals(false, testList.isEmpty());
        assertEquals(true, myList.isEmpty());
    }

    //contains() 메서드 테스트
    @Test
    @DisplayName("contains() 메서드를 이용하여 해당 요소가 존재하는지 true/false로 확인할 수 있다")
    void contains() {
        assertEquals(true, testList.contains("1"));
        assertEquals(false, testList.contains("4"));
    }

    //iterator() 메서드 테스트
    @Test
    @DisplayName("iterator() 메서드를 이용하여 리스트의 요소를 순회할 수 있다")
    void iterator() {

        //아 테스트로 행위가 이루어지면 계속 이어서 진행할수있구나
        //assert메소드는 단발성일줄알았는데 코드라인 이어서 가는구나
        Iterator<String> iterator = testList.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals("1", iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals("2", iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals("3", iterator.next());
        assertEquals(false, iterator.hasNext());


    }

    //toArray() 메서드 테스트
    @Test
    @DisplayName("toArray() 메서드를 이용하여 리스트를 배열로 변환할 수 있다")
    void toArray() {
        Object[] array = testList.toArray();
        assertEquals("1", array[0]);
        assertEquals("2", array[1]);
        assertEquals("3", array[2]);
        assertEquals(array.length, testList.size());
    }

    //remove() 메서드 테스트
    @Test
    @DisplayName("remove() 메서드를 이용하여 해당 요소를 삭제할 수 있다")
    void remove() {

        assertEquals(true, testList.remove("1"));
        assertEquals(2, testList.size());
        assertEquals(false, testList.remove("4"));
        assertEquals(2, testList.size());
        assertEquals("2", testList.get(0));

    }

    //removeAll() 메서드 테스트
    @Test
    @DisplayName("removeAll() 메서드를 이용하여 해당 컬렉션의 요소를 삭제할 수 있다")
    void removeAll() {
        ArrayList<String> removeList = new ArrayList<>();
        removeList.add("1");
        removeList.add("2");

        assertEquals(true, testList.removeAll(removeList));
        assertEquals(1, testList.size());
        assertEquals("3", testList.get(0));
    }

    //remove(int index) 메서드 테스트
    @Test
    @DisplayName("remove(int index) 메서드를 이용하여 해당 인덱스의 요소를 삭제할 수 있다")
    void removeIndex() {
        assertEquals("1", testList.remove(0));
        assertEquals(2, testList.size());
        assertEquals("3", testList.get(1));
    }

    //containsAll() 메서드 테스트
    @Test
    @DisplayName("containsAll() 메서드를 이용하여 해당 컬렉션의 모든 요소가 존재하는지 확인할 수 있다")
    void containsAll() {
        ArrayList<String> containsList = new ArrayList<>();
        containsList.add("1");
        containsList.add("2");

        ArrayList<String> noHaveContainsList = new ArrayList<>();
        noHaveContainsList.add("4");
        noHaveContainsList.add("5");


        assertEquals(true, testList.containsAll(containsList));
        assertEquals(false, testList.containsAll(noHaveContainsList));
    }

    //clear() 메서드 테스트
    @Test
    @DisplayName("clear() 메서드를 이용하여 리스트의 모든 요소를 삭제할 수 있다")
    void clear() {
        testList.clear();
        assertEquals(0, testList.size());
    }

    //set() 메서드 테스트
    @Test
    @DisplayName("set() 메서드를 이용하여 해당 인덱스의 요소를 변경할 수 있다")
    void set() {
        assertEquals("1", testList.get(0));
        testList.set(0, "4");
        assertEquals("4", testList.get(0));
    }

    //indxOf() 메서드 테스트
    @Test
    @DisplayName("indexOf() 메서드를 이용하여 해당 요소의 인덱스를 반환할 수 있다")
    void indexOf() {
        assertEquals(0, testList.indexOf("1"));
        assertEquals(1, testList.indexOf("2"));
        assertEquals(2, testList.indexOf("3"));
    }

    //lastIndexOf() 메서드 테스트
    @Test
    @DisplayName("lastIndexOf() 메서드를 이용하여 해당 요소의 마지막 인덱스를 반환할 수 있다")
    void lastIndexOf() {
        testList.add("1");
        testList.add("3");
        assertEquals(3, testList.lastIndexOf("1"));
        assertEquals(4, testList.lastIndexOf("3"));
    }

    //subList() 메서드 테스트
    //제네릭이 마음에 걸린다
    //TODO 원인 수정필요
    @Test
    @DisplayName("subList() 메서드를 이용하여 해당 범위의 리스트를 반환할 수 있다")
    void subList() {
        MyList<String> subList = (MyList<String>) testList.subList(0, 1);
        assertEquals(2, subList.size());
        assertEquals("1", subList.get(0));
        assertEquals("2", subList.get(1));
    }


}
