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

    //테스트용 생성자
    //아 테스트용 생성자 만들고 실제 생성자들은 private해도 될거같다
    public MyList(String number, String number1, String number2) {
        this.elementData = new Object[3];
        elementData[0] = number;
        elementData[1] = number1;
        elementData[2] = number2;
        size = 3;
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


    @Override
    public boolean addAll(Collection<? extends E> c) {
        //true/false반환하기위한 플래그 필요
        boolean retrunValue = false;
        //for문 돌때마다 플래그변수 대입되는게 불편...
        // 근데 true에서 또 true대입하게되면 그냥 자바는 무시할까?
        for(E o :c) {
            if(add(o)) {
                retrunValue = true;
            }
        }

        return retrunValue;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {

        E temp[] = (E[]) c.toArray();

        if(index > 0 || index < size) {
            System.arraycopy(temp, 0, elementData, index, temp.length);
            size = size + temp.length-1;
            return true;
        }

        return false;
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
        checkIndex(index);
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

    //Iterator 인터페이스는 hasNext(), next(), remove() 메소드를 가지고 있어야 합니다
    //hasNext()는 다음 요소가 있는지 확인하고, next()는 다음 요소를 반환하며,
    //remove()는 마지막으로 반환된 요소를 제거하는 역할을 합니다.
    /*
    MyList<String> myList = new MyList<>();
    myList.add("Apple");
    myList.add("Banana");
    myList.add("Cherry");

    // Iterator를 사용하여 리스트의 요소를 순회
    Iterator<String> it = myList.iterator();
    while (it.hasNext()) {
        String fruit = it.next();
        System.out.println(fruit); // 각 과일을 출력
    }

    iterator() 호출: myList.iterator()를 호출하여 Iterator 객체를 얻습니다.
    hasNext() 메소드: while (it.hasNext())를 통해 다음 요소가 있는지 확인합니다.
    currentIndex가 리스트의 크기보다 작으면 true를 반환합니다.
    next() 메소드: it.next()를 호출하여 현재 인덱스의 요소를 가져오고, 인덱스를 증가시킵니다.
    요소를 반환하고, 현재 인덱스는 다음 요소로 이동합니다.
    출력: 각 요소를 출력합니다.
     */
    // 처음에는 메소드 구현이 필요하다는 GPT조언으로 hasNext(), next(), remove()외부에 만들라했으나
    // 이터레이터는 구현체이기에 그러면 안된다고 판단
    // 구현체를 만들어야한다 익명클래스를 써야한다는GPT의 도움을 받음 or람다
    // 아직 익명클래스랑 람다 잘이해못했는데..
    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            private int iteratorIndex = 0;
            @Override
            public boolean hasNext() {
                if(elementData[iteratorIndex] != null) {
                    return true;
                }
                return false;
            }

            @Override
            public E next() {
                if(hasNext() == false) {
                    throw new NoSuchElementException();
                }
                return (E)elementData[iteratorIndex++];
            }

            @Override
            public void remove() {
                //인덱스 파라미터로 받는 remove가 없어서 구현은 우선 보류
            }
        };
    }

    //이터레이터 양방향 버전 구현은 우선 생략
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
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

    //. 1. 매개변수에 어떤타입이던 리턴시에 따라갈줄알았는데 타입캐스팅이 필요하다고?
    /*
    실행부...이해 100퍼 못했음
    String[] arr = new String[2];
    arr = (String[]) list.toArray(arr);
     */

    //TODO 제네릭이해도가 낮다 멘토님의 도움을 후에 요청해보자
    @Override
    public <T> T[] toArray(T[] a) {
        //이건되는데
        if(a.length >= size) {
            System.arraycopy(elementData, 0, a, 0,size);
            return a;
        }
        //이게 안되네
        if(a.length < size) {
            Object result = new Object[size];
            System.arraycopy(elementData, 0, result, 0,size);
            return (T[]) result;
        }



        return a;
    }
    // toArray 끝 ==========================================================================


    // remove 시작 ==========================================================================
    @Override
    public boolean remove(Object o) {
        for(int i=0; i<elementData.length; i++) {
            if(elementData[i].equals(o)) {
                System.arraycopy(elementData, i+1, elementData, i, size-1);
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        boolean flag = false;
        //반목이 많아지면 성능상 별로일거같은데 다른 방법을 생각해보았으나 결국 Stream이고
        //Sream역시도 결국 내부 반복이고 반복문은 피할수없을것같다
        // 반복문이 성능에 문제를 주긴하지만 내가 생각한것 만큼 많이 잡아먹지는 않는다고는한다
        for(Object o : c) {
            if(contains(o)) {
                //length가 아니라 size로 해야한다
                for(int i=0; i<size; i++) {
                    if(elementData[i].equals(o)) {
                        System.arraycopy(elementData, i+1, elementData, i, size-1);
                        //이거때문에 remove()를 못쓰다니...
                        elementData[size-1] = null;
                        size--;
                    }
                }
            }
            flag = true;
        }
        return flag;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E before = elementData(index);
        System.arraycopy(elementData, index+1, elementData, index, size-1);
        size--;

        return before;
    }

    // remove 끝 ==========================================================================

    @Override
    public boolean containsAll(Collection<?> c) {

        for(Object o : c) {
            if(!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        //이터레이터를 사용한다?
        //우선 시간상 보류
        return false;
    }

    @Override
    public void clear() {
        //요소를 제거하는거니깐 null로 초기화 리스트의 크기는 유지되겠지만
        // 참조요소를 없애면 GC가 처리해줄라나?
        for(int i=0; i<size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    public E set(int index, E element) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E before = elementData(index);
        elementData[index] = element;

        return before;
    }


    @Override
    public int indexOf(Object o) {

        int flag = 0;

        for(Object e : elementData) {
            if(e.equals(o)) {
                return flag;
            }
            flag++;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {

        Object[] temp = new Object[size];
        int flag = size-1;
        //역배치
        for(int i=0; i<size; i++) {
            temp[i] = elementData[size-i-1];
        }

        for(Object e : temp) {
            if(e.equals(o)) {
                return flag;
            }
            flag--;
        }


        return -1;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {

        List temp = new MyList();
        //TODO 왜 for문 진입하면 왜 elementData가 null이 되는가????!?!?!?
        for(int i=fromIndex; i<=toIndex; i++) {
            temp.add(elementData[i]);
        }

        return temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0; i<size; i++) {
            sb.append(elementData[i]);
            if(i != size-1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    //공통메소드
    private void checkIndex(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
