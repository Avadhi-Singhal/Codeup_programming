import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Expression expression = new Expression();
        
        boolean continueEvaluation = true;

        while (continueEvaluation) {
            System.out.println(Constant.USER_PROMPT);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println(Constant.EXIT_MSG);
                continueEvaluation = false;
                break;
            }

            if (expression.validate(input)) {
                float result = expression.evaluate(input);
                System.out.println(Constant.RESULT + result);
            } else {
                System.out.println(Constant.INVALID_EXPRESSION_MSG);
            }
        }

        scanner.close();
    }

}
