package math;

import java.math.BigDecimal;

public class Multiple implements Calculation {
    @Override
    public BigDecimal calculate(BigDecimal number1, BigDecimal number2) {
        return number1.multiply(number2);
    }
}
