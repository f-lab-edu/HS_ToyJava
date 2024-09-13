import java.util.Arrays;
import java.util.List;

public class OperatorPrecedence {

    private final List<String> operators = Arrays.asList("*", "/", "%", "+", "-");

    private final Integer RANK_BOUNDARY = 3;

    // 연산자의 우선순위를 반환하는 메서드
    public int getPrecedence(String operator) {
        if(!operators.contains(operator)) {
            throw new IllegalArgumentException("유효하지 않은 연산자입니다");
        }

        int index = operators.indexOf(operator);
        //3항 연산자 지양하자
        return index/ RANK_BOUNDARY;
    }

    // 두 연산자의 우선순위를 비교하는 메서드
    // 인자값의 명시성을 위해 변수명 변경 에초에 축약형1,2 이런식으로 쓰지말자(각자 고유변수명으로)
    public boolean hasHigherPrecedence(String origin, String compaireTarget) {
        return getPrecedence(origin) < getPrecedence(compaireTarget);
    }

}