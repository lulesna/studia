import org.junit.Test;

import static org.junit.Assert.*;

public class RPNTest {

    // obliczanie
    @Test
    public void testSimpleAddition() {
        assertEquals(5, RPN.calculateRPN("2 3 +"));
        assertEquals(-1, RPN.calculateRPN("2 -3 +"));
    }

    @Test
    public void testSimpleSubtraction() {
        assertEquals(1, RPN.calculateRPN("3 2 -"));
        assertEquals(-1, RPN.calculateRPN("2 3 -"));
    }

    @Test
    public void testSimpleMultiplication() {
        assertEquals(6, RPN.calculateRPN("2 3 ×"));
        assertEquals(-6, RPN.calculateRPN("2 -3 ×"));
        assertEquals(6, RPN.calculateRPN("-2 -3 ×"));
    }

    @Test
    public void testSimpleDivision() {
        assertEquals(1, RPN.calculateRPN("3 2 /"));
        assertEquals(-1, RPN.calculateRPN("3 -2 /"));
        assertEquals(0, RPN.calculateRPN("2 3 /"));
    }

    @Test
    public void testSimplePower() {
        assertEquals(8, RPN.calculateRPN("2 3 ^"));
        assertEquals(1, RPN.calculateRPN("2 0 ^"));
        assertEquals(1, RPN.calculateRPN("1 2 ^"));
        assertEquals(0, RPN.calculateRPN("0 2 ^"));
    }

    @Test
    public void testComplexExpressions() {
        assertEquals(40, RPN.calculateRPN("12 2 3 4 × 10 5 / + × +"));
        assertEquals(14, RPN.calculateRPN("5 1 2 + 4 × + 3 -"));
        assertEquals(6, RPN.calculateRPN("10 5 % 2 3 × +"));
    }

    @Test
    public void testSingleNumber() {
        assertEquals(11, RPN.calculateRPN("11"));
    }

    // nieprawidłowe wyrażenia
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyExpression() {
        RPN.calculateRPN("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullExpression() {
        RPN.calculateRPN(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSpacesOnly() {
        RPN.calculateRPN("   ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCharacter() {
        RPN.calculateRPN("1 a +");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidOperator() {
        RPN.calculateRPN("1 2 @");
    }

    // usuwanie spacji
    @Test
    public void testMultipleSpaces() {
        assertEquals(5, RPN.calculateRPN("2   3   +"));
        assertEquals(5, RPN.calculateRPN("  2 3 +  "));
    }

    // nieprawidłowa liczba operatorów/liczb
    @Test(expected = IllegalArgumentException.class)
    public void testTooManyNumbers() {
        RPN.calculateRPN("1 2 3 +");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooManyOperators() {
        RPN.calculateRPN("1 2 + +");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOnlyOperator() {
        RPN.calculateRPN("+");
    }

    // nieprawidłowe obliczenia
    @Test(expected = ArithmeticException.class)
    public void testDivisionByZero() {
        RPN.calculateRPN("5 0 /");
    }

    @Test(expected = ArithmeticException.class)
    public void testModuloByZero() {
        RPN.calculateRPN("5 0 %");
    }
}