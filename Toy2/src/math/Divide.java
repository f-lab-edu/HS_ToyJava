package math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class Divide implements Calculation {
    @Override
    public BigDecimal calculate(List<BigDecimal> values) {
        // 0으로 나누는 경우 예외 처리
        if (values.get(1).compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }
        return values.get(0).divide(values.get(1), MathContext.DECIMAL128);
    }
}
