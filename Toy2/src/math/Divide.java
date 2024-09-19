package math;

import java.math.BigDecimal;
import java.math.MathContext;

public class Divide implements Calculation {
    @Override
    public BigDecimal calculate(BigDecimal number1, BigDecimal number2) {
        // 0으로 나누는 경우 예외 처리
        if (number2 == BigDecimal.ZERO) {
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }
        //MathContext.DECIMAL128는 34자리까지 정확한 값을 계산 지우려 하였으나 무한소수로인하여 에러가 날수있기때문에 정밀도 넣어줌
        return number1.divide(number2, MathContext.DECIMAL128);
    }
}
