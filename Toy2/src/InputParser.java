import java.util.ArrayList;
import java.util.List;

//1.사용자에게 입력받은 문자열을 숫자,연산자,소수점,공백으로만 이루어진 문자열을 반환하는 클래스(1차검증소)
public class InputParser {

    private final List<String> parsedResult;

    // 생성자에서 ParsedResult 초기화
    public InputParser(final String input) {
        this.parsedResult = new ArrayList<>(); // 리스트 초기화
        parseInput(input);
    }

    // final을 생성자에 이미 선언하면 굳이 안해도되지않을까?
    public List<String> parseInput(final String input) throws IllegalArgumentException {

        //정규식을 이용 숫자(0~9),연산자(+,-,*,/,%),소수점(.),공백(\\s)만 입력받도록 함
        if (!input.matches("[0-9+\\-*/%().\\s]*")) {
            throw new IllegalArgumentException("입력한 문자가 잘못되었습니다");
        }

        StringBuilder number = new StringBuilder();
        String inputReplaceBlank = input.replaceAll("\\s", "");

        //입력받은 문자열을 문자배열로 바꾼뒤 루프 시작
        for (char ch : inputReplaceBlank.toCharArray()) {
            //메소드화
            parseChar(ch, number);
        }

        //마지막 숫자 처리 메소드로 뺴야하나?
        if (number.length() > 0) {
            parsedResult.add(number.toString());
        }


        return parsedResult;
    }

    //early return pattern
    /*
    1. 입력받은 문자열을 문자배열로 바꾼뒤 루프 시작
    2. 문자가 ( or ) 이면 숫자가 있으면 리스트에 추가하고 숫자 초기화
    3. 문자가 연산자이면 숫자가 있으면 리스트에 추가하고 숫자 초기화

    # 이미 입력값의 검증은 끝났기에 예외처리 삭제
     */
    private void parseChar(char ch, StringBuilder number) {

        if (ch == '(' || ch == ')') {
            if (number.length() > 0) {
                parsedResult.add(number.toString());
                number.setLength(0);
            }
            parsedResult.add(String.valueOf(ch));
            return;
        }

        // 문자or소수점 확인
        if (Character.isDigit(ch) || ch == '.') {
            number.append(ch);
            return;
        }

        // 연산자인지 확인
        if (isOperator(ch)) {
            if (number.length() > 0) {
                parsedResult.add(number.toString());
                number.setLength(0);
            }
            parsedResult.add(String.valueOf(ch));
            return;
        }

    }

    //연산자인지 확인하는 메소드
    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%';
    }

    public List<String> getParsedResult() {
        return List.copyOf(parsedResult);
    }
}