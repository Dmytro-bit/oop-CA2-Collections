import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Question9 {

    public static int calculate()
    {
        Scanner input = new Scanner(System.in);
        Deque<Integer> numbers = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();

        System.out.println("Enter the expression: ");
        final String simpleOperators = "+-*/";

        String expression = input.nextLine().trim();
        expression = expression.replaceAll(" ", "");

        for (int i = 0; i < expression.length(); i++)
        {
            char currChar = expression.charAt(i);

            if(currChar == '(')
                operators.push(currChar);
            else if (simpleOperators.indexOf(currChar) >= 0)
            {
                while(!operators.isEmpty() && (operators.peek() == '*' || operators.peek() == '/'))
                    evaluateTop(operators.pop(), numbers);
                operators.push(currChar);
            }
            else if (currChar == ')')
            {
                while(!operators.isEmpty() && operators.peek() != '(')
                    evaluateTop(operators.pop(), numbers);
                operators.pop();
            }
            else if (Character.isDigit(currChar)) {
                int num = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                i--;
                numbers.push(num);
            }
        }

        while(!operators.isEmpty())
        {
            evaluateTop(operators.pop(), numbers);
        }

        return numbers.pop();

    }

    public static void evaluateTop(char operator, Deque<Integer> numbers)
    {
        switch (operator)
        {
            case '+':
                numbers.push(numbers.pop() + numbers.pop());
                break;
            case '-':
                int numToSubtract = numbers.pop();
                numbers.push(numbers.pop() - numToSubtract);
                break;
            case '*':
                numbers.push(numbers.pop() * numbers.pop());
                break;
            case '/':
                int numToDivide = numbers.pop();
                numbers.push(numbers.pop() / numToDivide);
                break;
        }
    }


    public static void main(String[] args) {
        System.out.println(calculate());
    }
}