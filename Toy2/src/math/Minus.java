package math;

import java.math.BigDecimal;
import java.util.List;

public class Minus implements Calculation {
    @Override
    public BigDecimal calculate(List<BigDecimal> values) {
        return values.get(0).subtract(values.get(1));
    }
}
