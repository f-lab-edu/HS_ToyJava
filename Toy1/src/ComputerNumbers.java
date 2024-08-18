import java.util.ArrayList;
import java.util.stream.IntStream;

public final class ComputerNumbers {

    private final ArrayList<Long> computerNumbers;


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


    public ComputerNumbers(ArrayList<Long> computerNumbers) {
        //컴퓨터넘버 자리수 검증
        if(computerNumbers.size() != 3){
            throw new IllegalArgumentException("컴퓨터 숫자는 3자리수여야합니다.");
        }
        //중복검증
        if(computerNumbers.stream().distinct().count() != 3){
            throw new IllegalArgumentException("컴퓨터 숫자는 중복되지않아야합니다.");
        }
        this.computerNumbers = computerNumbers;
    }

    public ArrayList<Long> getComputerNumbers() {
        return computerNumbers;
    }

    public void setComputerNumbers(ArrayList<Long> computerNumbers) {
        this.computerNumbers = computerNumbers;
    }

}
