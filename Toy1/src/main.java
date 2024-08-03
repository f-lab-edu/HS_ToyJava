import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class main {

    public static void main(String[] args) {

        // 플레이어에게 입력값을 받기 위한 스캐너
        Scanner scanner = new Scanner(System.in);
        //게임 종료를 구분하기위한 boolean 변수
        boolean gameEnd = false;


        System.out.println("숫자 야구 게임에 오신 것을 환영합니다!");
        System.out.println("컴퓨터가 숫자를 선택했습니다. 숫자를 맞춰보세요!");

        // 컴퓨터에게 랜덤난수 생성 메소드
        ComputerNumbers computerNumbers = generateRandomNumbers();
        GameResult gameResult = new GameResult();

        while (true) {
            UserInput userInput = inputUserNumbers(scanner);
            //match메소드는 private이라서 외부에서 호출이 불가능하다
            //그다음 상위단계인 default로 사용해보자
            MatchResult Result = computerNumbers.match(userInput);
            //save메소드는 private이라서 외부에서 호출이 불가능하다
            //그다음 상위단계인 default로 사용해보자
            gameResult.save(Result);
            System.out.println(gameResult.formatLastGameResult());

            if (gameResult.isDone()) {
                System.out.println(gameResult.closeReason());
                break;
            }
        }

        //System.out.println(gameResult.formatTotalGameResult());
        /*
        while (!gameEnd) {
            System.out.print("3자리 숫자를 입력하세요: ");
            String input = scanner.nextLine();

            if (input.length() != 3) {
                System.out.println("3자리 숫자를 입력해주세요.");
                continue;
            }

            int[] userNumbers = new int[3];
            try {
                for (int i = 0; i < 3; i++) {
                    userNumbers[i] = Integer.parseInt(input.substring(i, i + 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
                continue;
            }

            int strikes = countStrikes(computerNumbers, userNumbers);
            int balls = countBalls(computerNumbers, userNumbers);


            System.out.println("스트라이크: " + strikes + " 볼: " + balls);

            if (strikes == 3) {
                System.out.println("축하합니다! 숫자를 모두 맞추셨습니다.");
                gameEnd = true;
            }
        }
        */
        scanner.close();
    }

// 1. 랜덤 난수 생성 메소드
    private static ComputerNumbers generateRandomNumbers() {
        //랜덤 객체 랜덤 난수 생성을 하기위함
        Random random = new Random();

        //난수 데이터를 저장하는 ComputerNumbers 객체 생성
        /*
        question1 : 생성자를 정의하였기 때문에 인스턴스 생성시 초기값을 넣어주어야 하는데,
                    비어있는 껍데기 인스턴스를 만들기 위해선 어떻게 해결해야하지?
                    if 초기상태값이 총 3개에서 1개만넣어줄수있다면?
                    오버로딩 형식으로 여러개 생성자를 만들어야하나?

        question2 : new로 객체를 생성하는 행위를 만약 스프링 프로젝트라고 가정하면
                    최대한 컴포넌트화시켜서 DI를 받아서 사용하는게 좋은건가?..
                    그러면 new도 안쓰고...너무 이상향적인 이야기인가?

         */
        ComputerNumbers ComputerNumbers = new ComputerNumbers();

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
        /*
            중복되는 경우가 있기에 수정을하려하는데
            generate를 쓰는경우에는 set을 활용할수있는데
            range를 쓰는경우는 1~9까지 생성하고 강제로 섞고 중복제거를하네?
            방식이 다르다??
         */
        // IntStream -> LongStream으로 변경

        // 1부터 9까지의 숫자를 생성하여 리스트로 변환
        List<Long> numbers = LongStream.rangeClosed(1, 9) // 1부터 9까지의 숫자 생성
                .boxed() // LongStream을 Stream<Long>으로 변환
                .collect(Collectors.toList());

        // 리스트를 셔플하여 난수를 랜덤하게 섞음
        Collections.shuffle(numbers, random);

        // 셔플된 리스트에서 처음 3개의 숫자를 선택
        ArrayList<Long> testcase1 = numbers.stream()
                .limit(3) // 처음 3개의 숫자를 선택
                .collect(Collectors.toCollection(ArrayList::new)); // ArrayList로 수집

        ComputerNumbers.setComputerNumbers(testcase1);


        return ComputerNumbers;
    }

    private static UserInput inputUserNumbers(Scanner scanner) {
        UserInput userInput = new UserInput();
        while (true) {
            System.out.print("숫자 3자리를 입력하세요 (각 숫자는 1~9 범위): ");
            //
            String input = scanner.nextLine();

            // 입력 유효성 검사 (실무에서 유효성검사를 많이 진행을 안해서 어색하다)
            if (isValidInput(input)) {
                // 스트림을 사용하여 문자열을 long 리스트로 변환
                ArrayList<Long> testcase2 = input.chars()
                            .mapToLong(Character::getNumericValue)
                            .boxed()
                            .collect(Collectors.toCollection(ArrayList::new));
                // 중복 여부 검사
                if (hasUniqueDigits(testcase2)) {
                    userInput.setUserInput(new ArrayList<>(testcase2));
                    break;
                } else {
                    System.out.println("중복된 숫자가 포함되어 있습니다. 다시 시도하세요.");
                }
            } else {
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
        return userInput;

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

