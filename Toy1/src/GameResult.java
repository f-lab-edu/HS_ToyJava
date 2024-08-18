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



    public GameResult(MatchResult matchResult) {
        this.matchResult = matchResult;
    }

    void save(MatchResult Result) {
        this.matchResult = Result;
    }

    String formatLastGameResult() {
        return "스트라이크: " + matchResult.getStrikes() + " 볼: " + matchResult.getBalls() +"(입력횟수 = "+ matchResult.getMatchCount() + ")";
    }

    boolean isDone() {
        return matchResult.getStrikes() == 3 || matchResult.getMatchCount() == 10;
    }

    String closeReason() {
        if (matchResult.getStrikes() == 3) {
            return "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
        }
        return "입력횟수를 초과하셨습니다. 게임 종료";
    }

}
