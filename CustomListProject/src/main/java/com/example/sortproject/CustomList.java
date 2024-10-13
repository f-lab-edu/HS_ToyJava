package com.example.sortproject;

import java.util.*;

public class CustomList<T> implements List<T> {

    //유효 아이디
    private static final long serialVersionUID = 1L;

    //기본 사이즈는 5로 만들어봄
    private static final int DEFAULT_CAPACITY = 5;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    protected transient int modCount = 0;

    /*
    객체를 저장하거나 다른 JVM으로 보낼때 transient라는 예약어를 사용하여 선언한 변수는 Serializable대상에서 제외된다
    (보안상 중요한변수나 꼭 저장해야 할 필요가 없는 변수에 대해서는 transent를 사용할수있다)
     */
    transient Object[] elementData;

    private int size;

    //생성자 시작=====================================================================

    //생성자1 ex) 용량을 지정하여 생성하는경우 new ArrayList(10);
    public CustomList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
    }

    //생성자2 ex) 용량을 지정하지 않고 생성하는경우 new ArrayList();
    public CustomList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    //생성자3 ex) 다른 리스트를 복사하여 생성하는경우 new ArrayList(list);
    public CustomList(Collection<? extends T> c) {
        Object[] a = c.toArray();
        if ((size = a.length) != 0) {
            if (c.getClass() == ArrayList.class) {
                elementData = a;
            } else {
                elementData = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            elementData = EMPTY_ELEMENTDATA;
        }
    }

    //생성자 종료========================================================================

    //size메소드
    @Override
    public int size() {
        return size;
    }

    //add메소드 시작 ====================================================================

    @Override
    public boolean add(T element) {
        modCount++;  // 리스트가 수정된 횟수를 기록 (변경 감지 목적)
        add(element, elementData, size);  // 실제로 요소를 추가하는 메서드 호출
        return true;  // 추가가 항상 성공하므로 true 반환
    }

    @Override
    public void add(int index, T element) {
        rangeCheckForAdd(index);
        modCount++;
        final int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elementData).length)
            elementData = grow();
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = element;
        size = s + 1;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private void add(T element, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = element;
        size = s + 1;
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length; // 현재 배열의 길이 확인 (기존 용량)
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) { //현재 용량이 0보다 크거나 기본 용량이 아닌 경우
            /* ArraysSupport.newLength 이건 지원클래스여서 사용 불가 직접 구현필요
            int newCapacity = ArraysSupport.newLength(oldCapacity,
                    minCapacity - oldCapacity,
                    oldCapacity >> 1);// 비트연산자를 사용하여 기존 용량의 절반을 새로운 용량으로 지정
            */
            int newCapacity = oldCapacity + (oldCapacity << 1); // 2배로 늘리는 방법


            return elementData = Arrays.copyOf(elementData, newCapacity); //현재배열을 새로운 길이로 복사하여 elementData에 다시 할당
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)]; // 기본 용량과 최소 용량 중 큰 값으로 새로운 배열 생성
        }
    }

    //add메소드 끝 ====================================================================

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
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
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
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
    public T get(int index) {
        Objects.checkIndex(index, size);
        return elementData(index);
    }

    T elementData(int index) {
        return (T) elementData[index];
    }

    //get 끝====================================================================

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public T remove(int index) {
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
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return List.of();
    }
}
