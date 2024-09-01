import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("계산기 프로그램입니다.");
        System.out.println("계산 식을 입력하여 주십시오 (예: 1+2)");

        while (true) {
            try {
                String input = sc.nextLine();
                Calculator calculator = new Calculator(input);
                BigDecimal result = calculator.calculate();
                System.out.println("결과: " + result);
            } catch (ArithmeticException e) {
                System.out.println("수학적 오류: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요. / " + e.getMessage());
            }
        }

    }
}
