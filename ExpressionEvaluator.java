import java.util.Scanner;
import java.util.Stack;

/***
 * This program evaluates mathematical expressions provided as strings using the BODMAS rule. It 
 * supports parentheses, operators (+, -, *, /, ^), and validates expressions. Now supports floating-point numbers.
 * 
 * Owner : Avadhi-Singhal
 * 
 * Date of Creation : 25/11/2024
 */

class ExpressionEvaluator {

    /**
     * Determines the precedence level of an operator.
     * @param operator the operator whose precedence is being determined
     * @return the precedence level (higher value = higher precedence)
     */
    private int precedence(char operator) {
        if (operator == '^') return 3; 
        if (operator == '*' || operator == '/') return 2;
        if (operator == '+' || operator == '-') return 1;
        return 0;
    }

    /**
     * Applies the given operator to two operands.
     * @param a        the first operand
     * @param b        the second operand
     * @param operator the operator to apply (e.g., +, -, *, /, ^)
     * @return the result of applying the operator
     */
    private float applyOperation(float a, float b, char operator) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': 
                if (b == 0) {
                    System.out.println(Error.DIVISION_BY_ZERO);
                    return 0; 
                }
                return a / b;
            case '^': return (float) Math.pow(a, b); 
            default: 
                System.out.println(Error.INVALID_OPERATOR);
                return 0;
        }
    }

    /**
     * Evaluates a mathematical expression using BODMAS rules.
     * @param expression the mathematical expression to evaluate
     * @return the result of the evaluated expression
     */
    public float evaluate(String expression) {
        expression = expression.replaceAll("\\s+", "");

        Stack<Float> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);

            if (current == ' ') continue;

            if (Character.isDigit(current) || current == '.') {
                StringBuilder num = new StringBuilder();
                boolean hasDecimalPoint = false;

                while (i < expression.length() && 
                       (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    if (expression.charAt(i) == '.') {
                        if (hasDecimalPoint) {
                            System.out.println("Error: Invalid number format");
                            return 0; 
                        }
                        hasDecimalPoint = true;
                    }
                    num.append(expression.charAt(i++));
                }
                i--; 
                operands.push(Float.parseFloat(num.toString()));
            }
            else if (current == '(') {
                operators.push(current);
            }
            else if (current == ')') {
                while (operators.peek() != '(') {
                    evaluateTop(operands, operators);
                }
                operators.pop(); 
            }
            else if ("+-*/^".indexOf(current) != -1) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(current)) {
                    evaluateTop(operands, operators);
                }
                operators.push(current);
            }
        }
        while (!operators.isEmpty()) {
            evaluateTop(operands, operators);
        }
        return operands.pop();
    }

    /**
     * Applies the top operator to the top two operands in their respective stacks.
     * @param operands  the stack of numbers
     * @param operators the stack of operators
     */
    private void evaluateTop(Stack<Float> operands, Stack<Character> operators) {
        float b = operands.pop();
        float a = operands.pop();
        char op = operators.pop();
        float result = applyOperation(a, b, op);
        operands.push(result);
    }

    /**
     * Validates the mathematical expression for proper syntax.
     * @param expression the mathematical expression to validate
     * @return true if the expression is valid, false otherwise
     */
    public boolean validateExpression(String expression) {
        if (expression == null || expression.isEmpty()) {
            System.out.println("Error: Expression is empty");
            return false;
        }

        int openParentheses = 0;
        boolean lastWasOperator = true; 
        boolean lastWasDigitOrCloseParen = false;

        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);

            if (current == ' ') continue;
            if (Character.isDigit(current) || current == '.') {
                lastWasOperator = false;
                lastWasDigitOrCloseParen = true;
            }
            else if (current == '(') {
                openParentheses++;
                lastWasOperator = true;
                lastWasDigitOrCloseParen = false;
            }
            else if (current == ')') {
                if (openParentheses == 0) {
                    System.out.println(Error.UNMATCHED_PARANTHESIS);
                    return false;
                }
                openParentheses--;
                lastWasOperator = false;
                lastWasDigitOrCloseParen = true;
            }
            else if ("+-*/^".indexOf(current) != -1) {
                if (lastWasOperator || !lastWasDigitOrCloseParen) {
                    System.out.println(Error.INVALID_OPERATOR_PLACEMENT);
                    return false;
                }
                lastWasOperator = true;
                lastWasDigitOrCloseParen = false;
            }
            else {
                System.out.println(Error.INVALID_CHARACTER + current);
                return false;
            }
        }

        if (openParentheses != 0) {
            System.out.println(Error.UNMATCHED_CLOSING_PARANTHESIS);
            return false;
        }

        if (lastWasOperator) {
            System.out.println(Error.INVALID_EXPRESSION);
            return false;
        }

        return true; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpressionEvaluator evaluator = new ExpressionEvaluator();

        while (true) {
            System.out.println(Constant.USER_PROMPT);
            String expression = scanner.nextLine();

            if (expression.equalsIgnoreCase("exit")) {
                System.out.println(Constant.EXIT_MSG);
                break;
            }

            if (evaluator.validateExpression(expression)) {
                float result = evaluator.evaluate(expression);
                System.out.println(Constant.RESULT + result);
            } else {
                System.out.println(Constant.INVALID_EXPRESSION_MSG);
            }
        }

        scanner.close();
    }
}
