package math;

import java.math.BigDecimal;

public class Remainder implements Calculation {
    @Override
    public BigDecimal calculate(BigDecimal number1, BigDecimal number2) {
        // 0으로 나머지 연산을 하는 경우 예외 처리
        if (number2 == BigDecimal.ZERO) {
            throw new ArithmeticException("0으로 나머지를 구할 수 없습니다.");
        }
        return number1.remainder(number2);
    }
}
