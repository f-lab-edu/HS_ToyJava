import java.util.Arrays;
import java.util.List;

public class OperatorPrecedence {

    // 연산자를 우선순위에 따라 리스트로 관리
    //Arrays.asList 메소드를 사용하여 문자열 배열 ("*", "/", "%", "+", "-")을 List로 변환
    private static final List<String> operators = Arrays.asList("*", "/", "%", "+", "-");

    // 연산자의 우선순위를 반환하는 메서드
    public static int getPrecedence(String operator) {
        // 연산자가 리스트에 있으면 해당 연산자의 우선순위를 반환
        switch (operator) {
            case "*":
            case "/":
            case "%":
                return 0; // 높은 우선순위
            case "+":
            case "-":
                return 1; // 낮은 우선순위
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    // 두 연산자의 우선순위를 비교하는 메서드
    public static boolean hasHigherPrecedence(String op1, String op2) {
        return getPrecedence(op1) < getPrecedence(op2);
    }

    // 주어진 연산자의 우선순위를 반환하는 메서드
    public static int rankingOperator(String operator) {
        return getPrecedence(operator);
    }
}