import java.util.stream.IntStream;

public class StrikeCount {

    public static int countStrikes(int[] computerNumbers, int[] userNumbers) {
        /*
        int strikes = 0;
        for (int i = 0; i < 3; i++) {
            if (computerNumbers[i] == userNumbers[i]) {
                strikes++;
            }
        }
        return strikes;
        */
        return (int) IntStream.range(0, 3) // 0~2까지의 인덱스를 생성
                // 1. filter를 이용하여 컴퓨터 숫자와 사용자 숫자가 같은지 확인
                .filter(i -> computerNumbers[i] == userNumbers[i])
                // 2. 일치하는 인덱스의 수를 세고, 이를 'int'로 반환
                .count();
    }



}
