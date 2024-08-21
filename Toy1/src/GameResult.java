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

    private Map<Long, MatchResult> matchResultMap = new HashMap<>();

    //게임 회차를 관리하기 위한 게임 변수
    //결국은 숫자상태 변수가 필요한건가...static으로 선언?
    // 아니면 초기화 단계를 분리?...
    private long gameCount = 0;


    public GameResult() {
        /* MatchResult와다르게 초기화를 먼저 해줘야하는데
           그리고 추후에는 초기화가 되면안되는데 그래서 생성자를 따로 뺐는데..
           디폴트생성자를 사용을 해야하나..
           변수선언시에 초기화하면 쓸데없이 메모리 잡아 먹을거같다
           HashMap말고 Map으로 선언해야하나?...
         */
        this.matchResultMap = new HashMap<>();
    }
    public GameResult(MatchResult matchResult) {
        this.matchResult = matchResult;
        /* MatchResult와다르게 초기화를 먼저 해줘야하는데
           그리고 추후에는 초기화가 되면안되는데 그래서 생성자를 따로 뺐는데..
           디폴트생성자를 사용을 해야하나..변수선언하고 바로 초기화하면되나?
           변수선언시에 초기화하면 쓸데없이 메모리 잡아 먹을거같다
           HashMap말고 Map으로 선언해야하나?...
         */
    }

    //set이긴하지만 이런식으로 메소드로 따로빼야하는건가?...
    void setMatchResult(MatchResult matchResult) {
        this.matchResult = matchResult;
    }

    void save(MatchResult Result) {
        this.matchResultMap.put(gameCount, Result);
        gameCount++;
    }

    String formatLastGameResult() {
        return "스트라이크: " + matchResult.getStrikes() + " 볼: " + matchResult.getBalls() +"(입력횟수 = "+ gameCount + ")";
    }

    boolean isDone() {
        return matchResult.getStrikes() == 3 || gameCount == 10;
    }

    String closeReason() {
        if (matchResult.getStrikes() == 3) {
            return "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
        }
        return "입력횟수를 초과하셨습니다. 게임 종료";
    }

}
