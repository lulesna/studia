import java.util.EmptyStackException;

public class RPN {

    public static int calculateRPN(String expression) {
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException("Wyrażenie nie może być null lub puste");
        }

        String[] elements = expression.trim().replaceAll("\\s+", " ").split(" ");
        Stack stack = new Stack();

        for (String element : elements) {
            // liczba -> na stos
            if (isNumber(element)) {
                stack.push(element);
            // operator: zdejmuje 2 liczby ze stosu i wykonuje działanie na nich, wynik -> na stos
            } else if (isOperator(element)) {
                try {
                    int b = Integer.parseInt(stack.pop());
                    int a = Integer.parseInt(stack.pop());
                    int result = performOperation(a, b, element);
                    stack.push(String.valueOf(result));
                } catch (EmptyStackException e) {
                    throw new IllegalArgumentException("Za mało liczb w wyrażeniu");
                }
            // element nie jest ani liczbą, ani operatorem
            } else {
                throw new IllegalArgumentException("Niepoprawny element: " + element);
            }
        }
        return getFinalResult(stack);
    }

    private static int getFinalResult(Stack stack) {
        try {
            int result = Integer.parseInt(stack.pop());
            // stos po pobraniu wyniku całego wyrażenia powinien być pusty
            if (!stack.isEmpty()) {
                throw new IllegalArgumentException("Za dużo liczb w wyrażeniu");
            }
            return result;
        } catch (EmptyStackException e) {
            throw new IllegalArgumentException("Stos jest pusty");
        }
    }

    private static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("×") || s.equals("/") || s.equals("^") || s.equals("%");
    }

    private static int performOperation(int a, int b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "×": return a * b;
            case "/": return a / b;
            case "^": return (int)Math.pow(a, b);
            case "%": return a % b;
            default:
                throw new IllegalArgumentException("Nieznany operator: " + operator);
        }
    }
}
