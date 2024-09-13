package math;

//enum은 primitive가 아닌 reference 타입이다 해당 상수값들은 힙영역에 저장되어진다
public enum MathSign {
    PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/"), REMAINDER("%");


    private final String value;

    MathSign(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
