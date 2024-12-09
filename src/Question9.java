import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Question9 {

    public static int calculate()
    {
        //prompts the user to input an expression
        Scanner input = new Scanner(System.in);
        Deque<Integer> numbers = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();

        System.out.println("Enter the expression: ");
        //string with all the operators allowed
        final String simpleOperators = "+-*/";

        String expression = input.nextLine().trim();
        //delete all white-spaces within the expression
        expression = expression.replaceAll(" ", "");

        //checks if there are any letter(s) in the expression
        while(expression.matches(".*[a-zA-Z].*"))
        {
            System.out.println("Invalid Expression");
            System.out.println("Expression cannot contain letters");
            expression = input.nextLine().trim();
            //delete all white-spaces within the expression
            expression = expression.replaceAll(" ", "");
        }

        //loops through the expression
        for (int i = 0; i < expression.length(); i++)
        {
            char currChar = expression.charAt(i);

            //manages current character to decide in which stack to push it
            if(currChar == '(')
                operators.push(currChar);
            //checks which identifier is in use
            else if (simpleOperators.indexOf(currChar) >= 0)
            {
                //decides if the operator has higher or lower priority
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
            //handles numbers
            else if (Character.isDigit(currChar)) {
                //helps to handle numbers greater than 9
                int num = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                i--;
                numbers.push(num);
            }
        }

        //evaluates the rest of the expression
        while(!operators.isEmpty())
        {
            evaluateTop(operators.pop(), numbers);
        }

        return numbers.pop();

    }

    public static void evaluateTop(char operator, Deque<Integer> numbers)
    {
        //handles calculations
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