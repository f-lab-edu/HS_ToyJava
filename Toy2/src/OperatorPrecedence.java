import java.util.Arrays;
import java.util.List;

public class OperatorPrecedence {

    // 연산자를 우선순위에 따라 리스트로 관리
    //Arrays.asList 메소드를 사용하여 문자열 배열 ("*", "/", "%", "+", "-")을 List로 변환
    private static final List<String> operators = Arrays.asList("*", "/", "%", "+", "-");

    // 연산자의 우선순위를 반환하는 메서드
    public static int getPrecedence(String operator) {
        // 연산자가 리스트에 있으면 해당 연산자의 인덱스를 반환
        int index = operators.indexOf(operator);
        //3을 나누는이유는 *,/,% 는 나눠서 0이되기떄문에 0순위 나머지 1순위 / 연산자가 리스트에없으면 MAX_VALUE반환
        return index != -1 ? index / 3 : Integer.MAX_VALUE; // 연산자가 없으면 가장 낮은 우선순위로 처리
    }

    // 두 연산자의 우선순위를 비교하는 메서드
    public static boolean hasHigherPrecedence(String op1, String op2) {
        return getPrecedence(op1) < getPrecedence(op2);
    }

}