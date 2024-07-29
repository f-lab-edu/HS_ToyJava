import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class main {

    public static void main(String[] args) {

        // 플레이어에게 입력값을 받기 위한 스캐너
        Scanner scanner = new Scanner(System.in);
        // 컴퓨터에게 랜덤난수 생성 메소드
        int[] computerNumbers = generateRandomNumbers();
        //게임 종료를 구분하기위한 boolean 변수
        boolean gameEnd = false;

        // case2 난수생성기능을 클래스로 나누었을떄
        CreateRandomNumber createRandomNumber = new CreateRandomNumber();
        int[] computerNumbers2 = createRandomNumber.generateRandomNumbers();

        System.out.println("숫자 야구 게임에 오신 것을 환영합니다!");
        System.out.println("컴퓨터가 숫자를 선택했습니다. 숫자를 맞춰보세요!");

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

        scanner.close();
    }

// 1. 랜덤 난수 생성 메소드
    public static int[] generateRandomNumbers() {
        //랜덤 객체 랜덤 난수 생성을 하기위함
        Random random = new Random();

        Set<Integer> numbersSet = IntStream.generate(() -> random.nextInt(9) + 1)
                .distinct() // 스트림에서 고유한값만 유지
                .limit(3) // 스트림의 크기를 3으로 제한
                .boxed() // IntStream을 Stream<Integer>로 변환
                .collect(Collectors.toSet()); // Stream(Collection요소)을 Set으로 변환

        int[] numbers = numbersSet.stream() // Set을 Stream으로 변환
                .mapToInt(Number::intValue) //각 Integer를 int로 변환
                .toArray(); // 스트림의 요소를 배열로 수집한다

        return numbers;
    }


}

