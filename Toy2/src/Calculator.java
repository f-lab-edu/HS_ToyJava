import math.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Calculator {

    private final List<String> stringInput; // 파싱된 입력값을 저장하는 리스트

    /*
     enum을 사용하게되면 Map<String, MathSign> operations = new HashMap<>();
     이런 형태를 써야하나? 그러면 연산자 인터페이스를 구현한 클래스들은 어떻게 사용하지?..
    */
    private final Map<String, Calculation> operations = new HashMap<>(); // 연산자와 연산 클래스를 매핑하는 맵

    public Calculator(String input) {
        InputParser parser = new InputParser(input);
        this.stringInput = parser.getParsedResult();
        validateState();
        initializeOperations();
    }
    // 연산자와 그에 대응하는 연산 클래스를 매핑
    private void initializeOperations() {


        //ENUM을 사용하면 궁극적인 형태는??이렇게?? -> operations.put(MathSign.PLUS.getValue(), MathSign.PLUS);
        operations.put(MathSign.PLUS.getValue(), new Plus());
        operations.put(MathSign.MINUS.getValue(), new Minus());
        operations.put(MathSign.MULTIPLY.getValue(), new Multiple());
        operations.put(MathSign.DIVIDE.getValue(), new Divide());
        operations.put(MathSign.REMAINDER.getValue(), new Remainder()); // 나머지 연산 추가
        // 필요한 경우 다른 연산자들도 이곳에 추가 가능
    }

    //연산 가능여부의 검증
    private void validateState() {
        long parenthesisBalance = 0; // 괄호의 균형을 검증하기 위한 변수
        boolean lastWasOperator = true; // 마지막 토큰이 연산자인지 검증하기 위한 변수

        //for문을 이용한부분을 메소드로 뺴고싶은데 지역변수가 존재하여 여기가 최선일것같다 다른방법이있을까?
        for (int i = 0; i < stringInput.size(); i++) {

            String token = stringInput.get(i);

            if (token.equals("(")) {
                parenthesisBalance++;
                lastWasOperator = true;
            }
            if (token.equals(")")) {
                if (parenthesisBalance == 0) {
                    throw new IllegalArgumentException("괄호 불일치 오류: 위치 " + i);
                }
                parenthesisBalance--;
                lastWasOperator = false;
            }
            if (isNumber(token)) {
                if (!lastWasOperator && (i > 0 && !stringInput.get(i - 1).equals("("))) {
                    throw new IllegalArgumentException("위치 " + i + "에서 숫자가 연속됩니다: " + token);
                }
                lastWasOperator = false;
            }
            if (isOperator(token)) {
                if (lastWasOperator && !token.equals("(")) {
                    throw new IllegalArgumentException("위치 " + i + "에서 연산자가 연속됩니다: " + token);
                }
                lastWasOperator = true;
            }
            throw new IllegalArgumentException("위치 " + i + "에서 유효하지 않은 토큰: " + token);

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
        List<String> postfix = convertToPostfix(stringInput);
        return evaluatePostfix(postfix);
    }


    // 수식을 후위 표기법으로 변환하는 메서드
    private List<String> convertToPostfix(List<String> infix) {
        List<String> postfix = new ArrayList<>(); // 후위 표기법으로 변환된 수식을 저장할 리스트
        //컬렉션 뭘로해야될까 정하기도 어렵다
        Deque<String> OperatorTemporaryDeque = new ArrayDeque<>(); // 연산자를 임시로 저장할 deque

        return ChangePostfixNotation(postfix, OperatorTemporaryDeque, infix);
    }

    //쪼개긴해봤는데 이게 의미가 있을까??
    private List<String> ChangePostfixNotation(List<String> postfix, Deque<String> OperatorTemporaryDeque, List<String> infix) {
        for (String token : infix) {
            // 숫자인 경우 바로 postfix 리스트에 추가
            if (isNumber(token)) {
                postfix.add(token);
            }
            // 연산자인 경우
            if (isOperator(token)) {
                // 스택이 비어있지 않고, 스택의 맨 위에 있는 연산자의 우선순위가 더 높은 경우
                while (!OperatorTemporaryDeque.isEmpty() && !OperatorTemporaryDeque.peek().equals("(") &&
                        OperatorPrecedence.hasHigherPrecedence(OperatorTemporaryDeque.peek(), token)) {
                    postfix.add(OperatorTemporaryDeque.pop());
                }
                OperatorTemporaryDeque.push(token);
            }

            // 왼쪽 괄호인 경우
            if (token.equals("(")) {
                OperatorTemporaryDeque.push(token);
            }
            // 오른쪽 괄호인 경우
            if (token.equals(")")) {
                // 왼쪽 괄호가 나올 때까지 스택에서 연산자를 pop하여 postfix 리스트에 추가
                while (!OperatorTemporaryDeque.peek().equals("(")) {
                    postfix.add(OperatorTemporaryDeque.pop());
                }
                OperatorTemporaryDeque.pop(); // Remove the '('
            }

            throw new IllegalArgumentException("유효하지 않은 토큰: " + token);

        }
        // 스택에 남아있는 모든 연산자를 pop하여 postfix 리스트에 추가
        while (!OperatorTemporaryDeque.isEmpty()) {
            postfix.add(OperatorTemporaryDeque.pop());
        }

        return postfix;
    }

    // 후위 표기법으로 변환된 수식을 계산하는 메서드
    private BigDecimal evaluatePostfix(List<String> postfix) {
        Deque<BigDecimal> dequeForCalculation = new ArrayDeque<>(); // 계산을 위한 deque

        // 계산 결과를 BigDecimal 형태로 반환
        return DoitCalculate(postfix, dequeForCalculation).pop();
    }

    private Deque<BigDecimal> DoitCalculate(List<String> postfix, Deque<BigDecimal> dequeForCalculation) {
        // 후위 표기법으로 변환된 수식을 순회하며 계산
        for (String token : postfix) {
            // 숫자인 경우 스택에 추가
            if (isNumber(token)) {
                dequeForCalculation.push(new BigDecimal(token));
            }

            // 연산자인 경우
            if (isOperator(token)) {
                BigDecimal b = dequeForCalculation.pop();
                BigDecimal a = dequeForCalculation.pop();
                Calculation calculation = operations.get(token);

                // 연산자가 null인 경우 예외 처리
                if (calculation == null) {
                    throw new IllegalArgumentException("지원되지 않는 연산자입니다: " + token);
                }
                // 연산자에 따라 계산을 수행하고 결과 도출
                BigDecimal result = calculation.calculate(List.of(a, b));

                //setScale 메소드를 사용하여 소수점 이하 3자리까지만 표시
                if (result.scale() > 0) {
                    result = result.setScale(3, RoundingMode.DOWN);
                    // 소수점 이하가 0인 경우 소수점을 제거
                    if (result.stripTrailingZeros().scale() <= 0) {
                        result = result.setScale(0, RoundingMode.DOWN);
                    }
                }

                // 결과를 스택에 추가
                result = result.setScale(0, RoundingMode.DOWN);
                //계산결과를 다시 스택에 넣어줌
                dequeForCalculation.push(result);
            }
        }
        // 스택에 결과가 하나만 남아있어야 함
        if (dequeForCalculation.size() != 1) {
            throw new IllegalStateException("계산 중 오류가 발생했습니다. 스택 상태를 확인하십시오.");
        }
        // Deque<BigDecimal> 형태로 반환
        return dequeForCalculation;
    }

}