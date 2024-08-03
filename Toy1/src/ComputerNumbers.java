import java.util.ArrayList;
import java.util.stream.IntStream;

public class ComputerNumbers {

    //1. List -> ArrayList로 변경
    //2. Integer -> Long으로 변경 (long타입변수는 Integer에 담을수없기에)
    private ArrayList<Long> computerNumbers;

    public ComputerNumbers() {
    }

    /*
    ????????????????????
     1. reuturn타입을 컬렉션으로 해야하지않을까? MatchResult이걸사용해도될까?
     2. match즉 결과를 확인하기위한 동작이 어느클래스에 있어야하는게 맞는걸까?
        내가 입력한값에대한 동작이니깐 computerNumbers클래스에 존재는해야할거같은데
        그러면 ball,strike를 사용해야하는데..그건 GameResult클래스에 존재하는데
    */
    MatchResult match(UserInput userInput) {

        /*
            1.long타입인데 LongStream이아닌 IntStream을 사용하는 정확한 이유는?
              LongStream을 사용할 이유가없다고?
        */
        long strikes = IntStream.range(0, computerNumbers.size())
                .filter(i -> computerNumbers.get(i).equals(userInput.getUserInput().get(i)))
                .count();

        long balls = IntStream.range(0, computerNumbers.size())
                .filter(i -> !computerNumbers.get(i).equals(userInput.getUserInput().get(i)))
                .filter(i -> computerNumbers.contains(userInput.getUserInput().get(i)))
                .count();

        return new MatchResult(strikes, balls);
    }

    public ComputerNumbers(ArrayList<Long> computerNumbers) {
        this.computerNumbers = computerNumbers;
    }

    public ArrayList<Long> getComputerNumbers() {
        return computerNumbers;
    }

    public void setComputerNumbers(ArrayList<Long> computerNumbers) {
        this.computerNumbers = computerNumbers;
    }

}
