package math;

import java.math.BigDecimal;
import java.util.List;

public interface Calculation {

    //가변인자-동적상황, List 정적상황 명확한 차이가뭐지?
    //BigDecimal calculate(BigDecimal... values);
    BigDecimal calculate(List<BigDecimal> values);


}
