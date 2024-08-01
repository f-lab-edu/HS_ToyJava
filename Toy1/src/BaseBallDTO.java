import java.util.List;

public class BaseBallDTO {
    private List<Integer> computerNumbers;
    private Long strikes;
    private Long balls;

    // 아 기본생성자를 정의를 해주는게 좋은건가? 다른생성자를 정의하면 기본생성자를정의해주는게맞나?..
    // 근데 기본생성자가 없으면 최초 객체 인스턴스화시에 일부 상태값만 사용하거나 혹은 정의하지 않고
    // 사용하고 싶으면 생각보다 생성자를 정의한것이 더 사용안할것같은데...
    // 기본생성자로 생성해서 상태값 하나씩 관리할수도있으니깐 편하기도 하고...
    public BaseBallDTO() {
    }

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
