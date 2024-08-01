import java.util.List;

public class BaseBallDTO {
    private List<Integer> computerNumbers;
    private Long strikes;
    private Long balls;

    //생성자 기본생성자 생성해주지만 항상 적어주는 버릇을 해보자
    public BaseBallDTO(List<Integer> computerNumbers, Long strikes, Long balls) {
        this.computerNumbers = computerNumbers;
        this.strikes = strikes;
        this.balls = balls;
    }

    //getter setter
    public List<Integer> getComputerNumbers() {
        return computerNumbers;
    }

    public void setComputerNumbers(List<Integer> computerNumbers) {
        this.computerNumbers = computerNumbers;
    }

    public Long getStrikes() {
        return strikes;
    }

    public void setStrikes(Long strikes) {
        this.strikes = strikes;
    }

    public Long getBalls() {
        return balls;
    }

    public void setBalls(Long balls) {
        this.balls = balls;
    }
}
