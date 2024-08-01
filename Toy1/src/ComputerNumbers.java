import java.util.ArrayList;

public class ComputerNumbers {

    //1. List -> ArrayList로 변경
    //2. Integer -> Long으로 변경 (long타입변수는 Integer에 담을수없기에)
    private ArrayList<Long> computerNumbers;

    public ComputerNumbers() {
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
