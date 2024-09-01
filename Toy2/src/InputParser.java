import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//1.사용자에게 입력받은 문자열을 숫자,연산자,소수점,공백으로만 이루어진 문자열을 반환하는 클래스(1차검증소)
public class InputParser {

    private final List<String> ParsedResult;

    // 생성자에서 ParsedResult 초기화
    public InputParser(String input) {
        this.ParsedResult = new ArrayList<>(); // 리스트 초기화
        parseInput(input);
    }

    public List<String> parseInput(String input) throws IllegalArgumentException {
        //정규식을 이용하여 숫자(0~9),연산자(+,-,*,/,%),소수점(.),공백(\\s)만 입력받도록 함
        if (!input.matches("[0-9+\\-*/%().\\s]*")) {
            throw new IllegalArgumentException("입력한 문자가 잘못되었습니다");
        }

        //StringBuilder는 문자열을 더할 때마다 새로운 객체를 생성하지 않고 기존의 문자열에 더하는 방식으로 문자열을 처리하는 클래스
        StringBuilder number = new StringBuilder();

        //공백 제거
        input = input.replaceAll("\\s", "");

        //입력받은 문자열을 문자배열로 바꾼뒤 루프 시작
        for (char ch : input.toCharArray()) {
            if (ch == '(' || ch == ')') {
                if (number.length() > 0) {
                    ParsedResult.add(number.toString());
                    number.setLength(0);
                }
                ParsedResult.add(String.valueOf(ch));
            // 문자or소수점 확인
            } else if (Character.isDigit(ch) || ch == '.') {
                number.append(ch);
            // 연산자인지 확인
            } else if (isOperator(ch)) {
                if (number.length() > 0) {
                    ParsedResult.add(number.toString());
                    number.setLength(0);
                }
                ParsedResult.add(String.valueOf(ch));
            } else {
                throw new IllegalArgumentException("유효하지 않은 문자: " + ch);
            }
        }
        //마지막 숫자 추가
        if (number.length() > 0) {
            ParsedResult.add(number.toString());
        }

        return ParsedResult;
    }



    //연산자인지 확인하는 메소드
    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%';
    }

    public List<String> getParsedResult() {
        return List.copyOf(ParsedResult);
    }
}