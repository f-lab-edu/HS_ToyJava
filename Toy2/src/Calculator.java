import math.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Calculator {

    private final List<String> StringInput; // 파싱된 입력값을 저장하는 리스트
    private final Map<String, Calculation> operations = new HashMap<>(); // 연산자와 연산 클래스를 매핑하는 맵

    public Calculator(String input) {
        InputParser parser = new InputParser(input);
        this.StringInput = parser.getParsedResult();
        validateState();
        initializeOperations();
    }
    // 연산자와 그에 대응하는 연산 클래스를 매핑
    private void initializeOperations() {
        operations.put("+", new Plus());
        operations.put("-", new Minus());
        operations.put("*", new Multiple());
        operations.put("/", new Divide());
        operations.put("%", new Remainder()); // 나머지 연산 추가
        // 필요한 경우 다른 연산자들도 이곳에 추가 가능
    }

    /*
    클래스의 상태 즉 여기선 파싱된값을 잘받았는지 검증을하는데 각각 클래스마다 2차검증메소드가 필요할까?
     InputParser에서검증했는데 또??
     검증메소드는 클래스로 따로 뺄까?
    */
    private void validateState() {
        if (StringInput.isEmpty()) {
            throw new IllegalArgumentException("파싱 실패");
        }

        long parenthesisBalance = 0; // 괄호의 균형을 검증하기 위한 변수
        boolean lastWasOperator = true; // 마지막 토큰이 연산자인지 검증하기 위한 변수

        for (int i = 0; i < StringInput.size(); i++) {

            String token = StringInput.get(i);

            if (token.equals("(")) {
                parenthesisBalance++;
                lastWasOperator = true;
            } else if (token.equals(")")) {
                if (parenthesisBalance == 0) {
                    throw new IllegalArgumentException("괄호 불일치 오류: 위치 " + i);
                }
                parenthesisBalance--;
                lastWasOperator = false;
            } else if (isNumber(token)) {
                if (!lastWasOperator && (i > 0 && !StringInput.get(i - 1).equals("("))) {
                    throw new IllegalArgumentException("위치 " + i + "에서 숫자가 연속됩니다: " + token);
                }
                lastWasOperator = false;
            } else if (isOperator(token)) {
                if (lastWasOperator && !token.equals("(")) {
                    throw new IllegalArgumentException("위치 " + i + "에서 연산자가 연속됩니다: " + token);
                }
                lastWasOperator = true;
            } else {
                throw new IllegalArgumentException("위치 " + i + "에서 유효하지 않은 토큰: " + token);
            }
        }

        if (parenthesisBalance != 0) {
            throw new IllegalArgumentException("괄호 불일치 오류: 전체 수식에서 괄호가 일치하지 않습니다.");
        }

        if (lastWasOperator) {
            throw new IllegalArgumentException("수식이 잘못되었습니다. 마지막 항목이 연산자입니다.");
        }
    }

    //숫자 검증을 위한 메소드
    private boolean isNumber(String str) {
        try {
            new BigDecimal(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //연산자 검증을 위한 메소드
    private boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("%");
    }


    // 계산을 수행하는 메서드 (Calculator 클래스의 핵심 메서드)
    public BigDecimal calculate() {
        List<String> postfix = convertToPostfix(StringInput);
        return evaluatePostfix(postfix);
    }


    // 수식을 후위 표기법으로 변환하는 메서드
    private List<String> convertToPostfix(List<String> infix) {
        List<String> postfix = new ArrayList<>(); // 후위 표기법으로 변환된 수식을 저장할 리스트
        //컬렉션 뭘로해야될까 정하기도 어렵다
        Deque<String> OperatorTemporaryDeque = new ArrayDeque<>(); // 연산자를 임시로 저장할 deque

        for (String token : infix) {
            if (isNumber(token)) {
                postfix.add(token);
            } else if (isOperator(token)) {
                while (!OperatorTemporaryDeque.isEmpty() && !OperatorTemporaryDeque.peek().equals("(") &&
                        OperatorPrecedence.hasHigherPrecedence(OperatorTemporaryDeque.peek(), token)) {
                    postfix.add(OperatorTemporaryDeque.pop());
                }
                OperatorTemporaryDeque.push(token);
            } else if (token.equals("(")) {
                OperatorTemporaryDeque.push(token);
            } else if (token.equals(")")) {
                while (!OperatorTemporaryDeque.peek().equals("(")) {
                    postfix.add(OperatorTemporaryDeque.pop());
                }
                OperatorTemporaryDeque.pop(); // Remove the '('
            } else {
                throw new IllegalArgumentException("유효하지 않은 토큰: " + token);
            }
        }
        while (!OperatorTemporaryDeque.isEmpty()) {
            postfix.add(OperatorTemporaryDeque.pop());
        }
        return postfix;
    }

    private BigDecimal evaluatePostfix(List<String> postfix) {
        Deque<BigDecimal> dequeForCalculation = new ArrayDeque<>(); // 계산을 위한 deque

        for (String token : postfix) {
            if (isNumber(token)) {
                dequeForCalculation.push(new BigDecimal(token));
            } else if (isOperator(token)) {
                BigDecimal b = dequeForCalculation.pop();
                BigDecimal a = dequeForCalculation.pop();
                Calculation calculation = operations.get(token);
                if (calculation == null) {
                    throw new IllegalArgumentException("지원되지 않는 연산자입니다: " + token);
                }
                // 연산자에 따라 계산을 수행하고 결과 도출
                BigDecimal result = calculation.calculate(List.of(a, b));
                //setScale 메소드를 사용하여 소수점 이하 3자리까지만 표시
                if (result.scale() > 0) {
                    result = result.setScale(3, RoundingMode.DOWN);
                    if (result.stripTrailingZeros().scale() <= 0) {
                        result = result.setScale(0, RoundingMode.DOWN);
                    }
                } else {
                    result = result.setScale(0, RoundingMode.DOWN);
                }
                dequeForCalculation.push(result);
            }
        }

        if (dequeForCalculation.size() != 1) {
            throw new IllegalStateException("계산 중 오류가 발생했습니다. 스택 상태를 확인하십시오.");
        }

        return dequeForCalculation.pop();
    }


}