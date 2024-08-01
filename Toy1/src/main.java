import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class main {

    public static void main(String[] args) {

        // 플레이어에게 입력값을 받기 위한 스캐너
        Scanner scanner = new Scanner(System.in);
        // 컴퓨터에게 랜덤난수 생성 메소드
        BaseBallDTO computerNumbers = generateRandomNumbers();
        //게임 종료를 구분하기위한 boolean 변수
        boolean gameEnd = false;


        System.out.println("숫자 야구 게임에 오신 것을 환영합니다!");
        System.out.println("컴퓨터가 숫자를 선택했습니다. 숫자를 맞춰보세요!");


        GameResult gameResult = new GameResult();
        while (true) {
            UserInput userInput = inputUserNumbers();
            MatchResult gameResult = computerNumbers.match(userInput);
            gameResult.save(gameResult);
            System.out.println(gameResult.formatLastGameResult());
            if (gameResult.isDone()) {
                break;
            }
        }

        System.out.println(gameResult.formatTotalGameResult());
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
    public static BaseBallDTO generateRandomNumbers() {
        //랜덤 객체 랜덤 난수 생성을 하기위함
        Random random = new Random();

        //DTO에 담기위해 DTO객체 생성
        /*
        question1 : DTO에 생성자를 정의하였기 때문에 값을 넣어주어야 하는데, 이러면 생성자를 여러개 만들어야하는건가?.. 아닌거같은데
                    3개의 난수는 DTO에 담으면안되나?.. 상태값을 나눠야하나?
        */
        /*
        question2 : new로 객체를 생성하는 행위를 만약 스프링 프로젝트라고 가정하면
                    최대한 컴포넌트화시켜서 DI를 받아서 사용하는게 좋은건가?..
                    그러면 new도 안쓰고...너무 이상향적인 이야기인가?
                    장점은? 단점은?.....
         */
        BaseBallDTO baseBallDTO = new BaseBallDTO();

        List<Integer> testcase1 = IntStream.range(0, 3)
                .map(i -> random.nextInt(9) + 1)
                .boxed()
                .collect(Collectors.toList());

        baseBallDTO.setComputerNumbers(testcase1);


        return baseBallDTO;
    }

    public static int countStrikes(int[] computerNumbers, int[] userNumbers) {
    /* case1 스트림 이용X
        int strikes = 0;
        for (int i = 0; i < 3; i++) {
            if (computerNumbers[i] == userNumbers[i]) {
                strikes++;
            }
        }
        return strikes;

    */

    //case2 스트림 이용O
        return (int) IntStream.range(0, 3)
                .filter(i -> computerNumbers[i] == userNumbers[i])
                .count();
    }

    public static int countBalls(int[] computerNumbers, int[] userNumbers) {

    /* case1 스트림 이용X
        int balls = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j && computerNumbers[i] == userNumbers[j]) {
                    balls++;
                }
            }
        }
        return balls;

    */

    //case2 스트림 이용O
        return (int) IntStream.range(0, 3)
                .flatMap(i -> IntStream.range(0, 3)
                .filter(j -> i != j && computerNumbers[i] == userNumbers[j]))
                .count();
    }


}

