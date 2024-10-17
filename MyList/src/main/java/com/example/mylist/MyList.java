package com.example.mylist;

import java.util.*;

public class MyList<E> implements List<E>{

    private static Object[] elementData;

    private final int DEFAULT_CAPACITY = 5;

    private int size;


    //생성자 시작========================================================================

    //1. 기본생성자
    public MyList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    //2. 사이즈 지정생성자
    public MyList(int n) {
        if(n > 0) {
            this.elementData = new Object[n];
        }
        if(n == 0) {
            this.elementData = new Object[DEFAULT_CAPACITY];
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
            this.elementData = new Object[DEFAULT_CAPACITY];
        }
    }


    //생성자 끝========================================================================


    //add시작=========================================================================
    @Override
    public boolean add(E e) {
        //꽉찬경우
        if(size == elementData.length) {
            grow();
        }
        elementData[size] = e;
        size++;
        return true;

    }


    // 배열의 크기를 두 배로 확장하는 메서드
    private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] tobe = new Object[newCapacity];
        System.arraycopy(elementData, 0, tobe, 0, size);
        elementData = tobe;

    }



    @Override
    public void add(int index, E element) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if(size == elementData.length) {
            grow();
        }
        //index부터 뒤로 한칸씩 미루기
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
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
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {

        for(int i=0; i<size; i++) {
            //Object클래스의 equals메소드는 동일성비교
            if(o.equals(elementData[i])) {
                return true;
            }
        }

        return false;
    }

    // Iterator 시작 ==========================================================================
    @Override
    public Iterator<E> iterator() {



        return null;
    }

    // Iterator 끝 ==========================================================================

    // toArray 시작 ==========================================================================
    @Override
    public Object[] toArray() {
        Object[] result = new Object[elementData.length];

        for(int i=0; i<elementData.length; i++) {
            result[i] = elementData[i];

        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }
    // toArray 끝 ==========================================================================


    // remove 시작 ==========================================================================
    @Override
    public boolean remove(Object o) {
        for(int i=0; i<elementData.length; i++) {
            if(elementData[i].equals(o)) {
                System.arraycopy(elementData, i+1, elementData, i, size - i);
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    // remove 끝 ==========================================================================

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
