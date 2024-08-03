

/*
 이 클래스는 실행흐름상 결합도를 낮추기위한 클래스로 읽힌다..
 역할은 입력한 숫자의 결과값을 담고 전달..
 computerNumbers의 match결과 -> MatchResult에담음 -> GameResult에 전달???
 computerNumbers의 match결과 -> GameResult에 전달 이것도 될텐데...
 그리고 그냥 컬렉션 하나 생성해서 전달하는것도 괜찮지않을까?
 MatchResult가 어떤 역할을해야하는건지 잘모르겠다
*/

public class MatchResult {


    private long strikes;
    private long balls;


    public MatchResult() {
    }

    /*
    스트라이크랑 볼변수를 또 선언하는건 내가 잘못설계한거인데
    어떻게 해야하는지 모르겠다
    MatchResult가 GameResult를 상속해야하는건가?
    아니면 컬렉션 형태로 스트라이크 랑 볼 카운트를 가지고이어야하나?
     */
    public MatchResult(long strikes, long balls) {
        this.strikes = strikes;
        this.balls = balls;
    }

    public long getStrikes() {
        return strikes;
    }

    public void setStrikes(long strikes) {
        this.strikes = strikes;
    }

    public long getBalls() {
        return balls;
    }

    public void setBalls(long balls) {
        this.balls = balls;
    }
}
