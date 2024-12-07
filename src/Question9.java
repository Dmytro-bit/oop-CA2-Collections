import java.util.Stack;
import java.util.Scanner;

public class Question9 {

    public static int calculate()
    {
        Scanner input = new Scanner(System.in);
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        System.out.println("Enter the expression: ");
        String simpleOperators = "+-*/";
        String expression = input.nextLine();

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
            else
            {
                numbers.push(Integer.parseInt(String.valueOf(currChar)));
            }
        }

        while(!operators.isEmpty())
        {
            evaluateTop(operators.pop(), numbers);
        }

        return numbers.pop();

    }

    public static void evaluateTop(char operator, Stack<Integer> numbers)
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