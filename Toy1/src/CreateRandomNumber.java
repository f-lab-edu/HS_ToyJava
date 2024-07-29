import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CreateRandomNumber {


    public static int[] generateRandomNumbers() {
        //랜덤 객체 랜덤 난수 생성을 하기위함
        Random random = new Random();

    /*
    case1. 스트림으로 변경하기이전코드

        1. Set을 이용하여 중복되지 않는 3자리 난수 생성(set은 중복허용안하니깐)
        Set<Integer> numbersSet = new HashSet<>();
        2. while을 이용하여 3자리 난수 생성
        while (numbersSet.size() < 3) {
            numbersSet.add(random.nextInt(9) + 1); // 1부터 9까지의 숫자
        }

    */

        //case2. 스트림을 이용하여 변경한 코드 위에 코드는 1.Set을 생성->2. 값넣어줌인데
        // 한번에 생성과 값대입이 다 되네?
        Set<Integer> numbersSet = IntStream.generate(() -> random.nextInt(9) + 1)
                .distinct() // 스트림에서 고유한값만 유지
                .limit(3) // 스트림의 크기를 3으로 제한
                .boxed() // IntStream을 Stream<Integer>로 변환
                .collect(Collectors.toSet()); // Stream(Collection요소)을 Set으로 변환

    /* case1 : 스트림을 이용하지 않고 값을 넣어주는 방법

        int[] numbers = new int[3];
        int index = 0;
        //NumbersSet에 있는 값을 numbers배열에 넣어줌
        for (int number : numbersSet) {
            numbers[index++] = number;
        }

    */

        // case2 : 스트림을 이용하여 배열로 변환
        int[] numbers = numbersSet.stream() // Set을 Stream으로 변환
                        .mapToInt(Number::intValue) //각 Integer를 int로 변환
                        .toArray(); // 스트림의 요소를 배열로 수집한다

        return numbers;
    }
}
