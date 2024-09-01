package math;

import java.math.BigDecimal;
import java.util.List;

public class Remainder implements Calculation {
    @Override
    public BigDecimal calculate(List<BigDecimal> values) {
        // 0으로 나머지 연산을 하는 경우 예외 처리
        if (values.get(1).compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("0으로 나머지를 구할 수 없습니다.");
        }
        return values.get(0).remainder(values.get(1));
    }
}
