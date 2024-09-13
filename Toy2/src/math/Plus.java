package math;

import java.math.BigDecimal;
import java.util.List;

public class Plus implements Calculation {
    @Override
    public BigDecimal calculate(List<BigDecimal> values) {
        return values.get(0).add(values.get(1));
    }
}
