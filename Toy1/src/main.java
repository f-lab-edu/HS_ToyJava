import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public final class main {


    // 전역변수로 선언 (지양하자...)
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        System.out.println("숫자 야구 게임에 오신 것을 환영합니다!");
        System.out.println("컴퓨터가 숫자를 선택했습니다. 숫자를 맞춰보세요!");

        ComputerNumbers computerNumbers = generateRandomNumbers();

        //기본생성자 생성 (Map초기화)
        GameResult gameResult = new GameResult();

        while (true) {
            UserInput userInput = inputUserNumbers(scanner);
            //게임 실행및 결과 도출
            MatchResult Result = computerNumbers.match(userInput);
            //GameResult에 진행중인게임 내역 저장
            gameResult.saveGameResult(Result);
            //MatchResult객체 초기화 및 현재 게임 내역 저장
            gameResult.saveMatchResult(Result);
            System.out.println(gameResult.formatLastGameResult());

            if (gameResult.isDone()) {
                System.out.println(gameResult.closeReason());
                break;
            }
        }
        scanner.close();
    }

// 1. 랜덤 난수 생성 메소드
    private static ComputerNumbers generateRandomNumbers() {
        Random random = new Random();

        /* 스트림의 종류
            1.기본형 스트림:
                - IntStream: int 타입의 스트림.
                - LongStream: long 타입의 스트림.
                - DoubleStream: double 타입의 스트림.
            2.참조형 스트림:
                - Stream<T>: 임의의 객체 타입 T를 처리하는 스트림.
            구문해석
                1. IntStream.range(0, 3):  0, 1, 2의 스트림을 생성 (0부터 3미만까지) 배열로치면 [0,1,2]
                2. map(i -> random.nextInt(9) + 1): 1부터 9까지의 난수를 생성하여 스트림에 매핑
                   map은 스트림의 매핑메서드라고 생각하자 여러종류의 map메서드가 존재하고 스트림의 타입마다 다르다
                   (map메서드는 스트림의 각요소에대해 주어진 함수로 적용한 결과를 매핑)
                   nextInt = 0부터 bound - 1 까지의 난수를생성 여기선 0~8까지 그래서 뒤에 +1을 해준다
                3. boxed(): boxed() 메서드는 기본형 스트림(IntStream, LongStream, DoubleStream)을
                   해당하는 래퍼 클래스의 스트림(Stream<Integer>, Stream<Long>, Stream<Double>)으로 변환하는 중간 연산
                4. collect메서드 = 종단연산중 하나이다 스트림의 요소를 컬렉션이나 다른 타입으로 변환하기위함이다
                5. Collectors.toList() = 스트림의 요소를 new ArrayList를 생성한뒤 add메서드로 요소를 추가한뒤 반환한다
        */

        /* question목록
           1. Int사용을 지양하라 하셨는데 그러면 IntStream이아닌 LongStream을 써야하는것인가?
           2. boxed()메서드사용시 바로 DTO같은 클래스로 씌울수있는방법이있을까?
           3. collect(Collectors.toList())결국 list변환은 Collectors인터페이스의 toList를 사용하는건데
              앞에 .collect는 그러면 꼭 세트처럼 붙어있어야하나? 무슨 역할이지?
              UnsupportedOperationException방지?....
              어 toArrayList는없다? 그러면 toList로만 변환하는게 맞는건가?
        */

        // 1부터 9까지의 숫자를 생성하여 리스트로 변환
        List<Long> numbers = LongStream.rangeClosed(1, 9) // 1부터 9까지의 숫자 생성
                .boxed() // LongStream을 Stream<Long>으로 변환
                .collect(Collectors.toCollection(ArrayList::new)); // 가변 리스트로 수집

        // 아 셔플은 가변List만 가능하다 근데 위에 toList로 컬렉션을 만들면
        // 불변리스트가 되어버린다 그래서 가변리스트로 변환해줘야한다 아.....
        Collections.shuffle(numbers, random);

        // 셔플된 리스트에서 처음 3개의 숫자를 선택
        List<Long> testcase1 = numbers.stream()
                .limit(3) // 처음 3개의 숫자를 선택
                .toList(); // List로 수집

        return new ComputerNumbers(testcase1);
    }

    private static UserInput inputUserNumbers(Scanner scanner) {
        while (true) {
            System.out.print("숫자 3자리를 입력하세요 (각 숫자는 1~9 범위): ");
            String input = scanner.nextLine();

            if (isValidInput(input)) {
                List<Long> testcase2 = input.chars()
                            .mapToLong(Character::getNumericValue)
                            .boxed()
                            .toList();
                // 중복 여부 검사
                if (hasUniqueDigits(testcase2)) {
                    return new UserInput(testcase2);
                } else {
                    System.out.println("중복된 숫자가 포함되어 있습니다. 다시 시도하세요.");
                }
            } else {
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }

    }

    //아직 구문해석불가 유효성검사기능도 따로 유틸클래스로 모아놓고싶다 프로젝트가 커진다면
    private static boolean isValidInput(String input) {
        // 스트림을 사용하여 입력값의 유효성 검사
        return input.length() == 3 && input.chars()
                .allMatch(c -> Character.isDigit(c) && c != '0');
    }
    private static boolean hasUniqueDigits(List<Long> numbers) {
        // 숫자 리스트가 중복이 없는지 확인
        return numbers.stream().distinct().count() == numbers.size();
    }

}

