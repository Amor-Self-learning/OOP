/*
 * BYTECODE INSPECTION NOTES
 *
 * 1. What instruction does the JVM use to load an integer parameter onto the operand stack?
 *    (Look for iload in the bytecode for add() and multiply())
 * ANSWER : JVM Uses iload_1 and iload_2 in add() aand multiply to load integers
 * 2. What is the difference between 'ireturn' and 'dreturn'?
 *    (Look at add() vs divide())
 * ANSWER: ireturn means integer return type and dreturn means double return type
 * 3. In divide(), what instruction corresponds to the cast (double) a?
 *    (Hint: look for i2d)
 * ANSWER: i2d is used to cast and integer to a double to avoid integer division truncation.
 * 4. In main(), what instruction is used to call calc.add()?
 *    (Hint: look for invokevirtual)
 * ANSWER:  14: invokevirtual #23  // Method add:(II)I
 * 5. How many bytecode instructions does add() require?
 *    Count them in the output.
 * ANSWER: add() requires 4 bytecode instructions.
 */

package Phase_00;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is undefined");
        }
        return (double) a / b;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println("3 + 4 = " + calc.add(3, 4));
        System.out.println("3 × 4 = " + calc.multiply(3, 4));
        System.out.println("10 ÷ 4 = " + calc.divide(10, 4));
    }
}
