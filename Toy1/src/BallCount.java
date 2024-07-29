import java.util.stream.IntStream;

public class BallCount {


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
        return (int) IntStream.range(0, 3) //0~2까지의 인덱스를 생성
                // 1. 각 i 에 대해 내부 스트림을 생성하여 조건을 만족하는 'j'를 찾는다
                // 2. filter(j -> i != j && computerNumbers[i] == userNumbers[j])는 서로 다른 위치에 같은 숫자가 있는지를 확인
                .flatMap(i -> IntStream.range(0, 3).filter(j -> i != j && computerNumbers[i] == userNumbers[j]))
                // 3. 일치하는 인덱스의 수를 세고, 이를 'int'로 반환
                .count();
    }


}
