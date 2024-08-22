import java.util.HashMap;
import java.util.Map;

/*
    GameResult에는 어떠한게 들어가야할까
    게임의 결과를 관리하는 클래스
    - 상태값 스트라이크와 볼 관리
    - 상태값을통한 결과 반환 메소드 필요 (true,false형태)

  1. 스트라이크/볼 수
  2.  그러면 결과를 던지는메소드도 만들어도될꺼같은데
     MatchResult가 메인메소드에 존재하니 이유가있을것이다...
     (MatchResult는 유틸리티 클래스가 되는건가?)
     if MatchResult구현시 GameResult를 상속하면? 스트라이크,볼 타입 공유가 가능하다?
*/
public class GameResult {


    private MatchResult matchResult;

    private final Map<Long, MatchResult> matchResultMap = new HashMap<>();


    //기본생성자 사용...오히려 커스텀 생성자가 사라졌다
    public GameResult() {
    }


    void saveGameResult(MatchResult matchResult) {
        final long gameCount = matchResultMap.keySet().size() + 1;
        this.matchResultMap.put(gameCount, matchResult);
    }

    void saveMatchResult(MatchResult matchResult) {
        this.matchResult = matchResult;
    }

    String formatLastGameResult() {
        return "스트라이크: " + matchResult.getStrikes() + " 볼: " + matchResult.getBalls() +"(입력횟수 = "+ matchResultMap.size() + ")";
    }

    boolean isDone() {
        return matchResult.getStrikes() == Constants.THREE || matchResultMap.size() == Constants.TEN;
    }

    String closeReason() {
        if (matchResult.getStrikes() == Constants.THREE) {
            return "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
        }
        return "입력횟수를 초과하셨습니다. 게임 종료";
    }

}
