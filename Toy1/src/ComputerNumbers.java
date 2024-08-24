import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class ComputerNumbers {

    private final List<Long> computerNumbers;


    MatchResult match(UserInput userInput) {

        long strikes = IntStream.range(0, computerNumbers.size())
                .filter(i -> isStrike(i, userInput))
                .count();
        long balls = IntStream.range(0, computerNumbers.size())
                .filter(i -> !isStrike(i, userInput))
                .filter(i -> computerNumbers.contains(userInput.getUserInput().get(i)))
                .count();

        return new MatchResult(strikes, balls);
    }

    //메소드 뽑기
    private boolean isStrike(int i, UserInput userInput) {
        return computerNumbers.get(i).equals(userInput.getUserInput().get(i));
    }


    public ComputerNumbers(List<Long> computerNumbers) {
        //컴퓨터넘버 자리수 검증
        if(computerNumbers.size() != 3){
            throw new IllegalArgumentException("컴퓨터 숫자는 "+ Constants.CUMPUTER_NUMBER_SIZE +" 자리수여야합니다.");
        }
        //중복검증
        if(computerNumbers.stream().distinct().count() != Constants.CUMPUTER_NUMBER_SIZE){
            throw new IllegalArgumentException("컴퓨터 숫자는 중복되지않아야합니다.");
        }
        this.computerNumbers = computerNumbers;
    }

}
