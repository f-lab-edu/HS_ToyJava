package com.example.mylist;

import java.lang.annotation.ElementType;
import java.util.*;

public class MyList<E> implements List<E>{

    //유효아이디
    private static final long serialVersionUID = 1L;

    //기본 리스트의 크기
    private static final int DEFAULT_CAPACITY = 5;

    //기본 리스트
    private static final Object[] EMPTY_ELEMENTDATA = {};

    //기본리스트2
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    //내부 배열
    /*
    객체를 저장하거나 다른 JVM으로 보낼때 transient라는 예약어를 사용하여 선언한 변수는 Serializable대상에서 제외된다
    (보안상 중요한변수나 꼭 저장해야 할 필요가 없는 변수에 대해서는 transent를 사용할수있다)
     */
    transient  Object[] elementData;

    //변경횟수
    protected transient int modCount = 0;

    //리스트의 크기
    private int size;

    //생성자 시작========================================================================

    //1. 기본생성자
    public MyList() {
        //왜 size지정 안해주지? 미리 조금이라도 메모리 차지안하겠다?
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    //2. 사이즈 지정생성자
    public MyList(int n) {
        if(n > 0) {
            this.elementData = new Object[n];
        }
        if(n == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        }
        if(n < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + n);
        }
    }


    //3. 컬렉션주입 생성자
    public MyList(Collection<? extends E> c) {
        elementData = c.toArray();
        if(elementData.length != 0) {
            size = elementData.length;
        }
        else {
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }


    //생성자 끝========================================================================


    //add시작=========================================================================
    @Override
    public boolean add(E e) {

        size++;

        //1. elementData가 비어있을때
        if(elementData.length == 0) {

            elementData = new Object[DEFAULT_CAPACITY];
            elementData[0] = e;
        }
        //2. elementData가 비어있지 않을때
        if(elementData.length <= DEFAULT_CAPACITY) {
            Object temp[] = new Object[1];
            temp[0] = e;
            System.arraycopy(temp, 0, elementData, elementData.length, 1);


            /*
            배열은 size사용이 불가능
            1. index값을 관리하는 flag변수를 추가할것인가
            */
        }
        //3. elementData가 꽉찼을때


        return true;
    }

    @Override
    public void add(int index, E element) {

    }


    //add 끝=========================================================================

    //get 시작=================================================================

    /*
    방어적 프로그래밍으로 인덱스 검증을 한번한다 런타임에러를 방지하기위해
    그래서 실제 ArrayList에서는
    Objects.checkIndex(index, size); 라는 Objects클래스의 checkIndex메소드를 사용하여 인덱스를 검증한다
    index가 유효하지 않으면 자동으로 **IndexOutOfBoundsException**을 던진다
    그리고 elementData(index); 메소드를 호출하여 요소를 반환한다
    이것은 메서드 분리를 한건데
    만약 리스트의 다른 메서드에서도 내부 배열에 접근해야 한다면
    직접 배열에 접근하는 대신 이 메서드를 통해 안전하게 접근하기 위함이다
     */
    @Override
    public E get(int index) {
        //이거 난 모르는 기능
        Objects.checkIndex(index, size);
        return elementData(index);
    }

    E elementData(int index) {
        return (E) elementData[index];
    }

    //get 끝====================================================================

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return List.of();
    }
}
