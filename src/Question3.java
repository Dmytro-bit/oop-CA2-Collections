import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Question3 {   //Nested HTML (Stack)

    /*
filename: name of the file to test.
*/
    public static boolean validate(String filename) throws FileNotFoundException {
        Deque<String> stack = new ArrayDeque<>();
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");

            for (String tag : line) {
                if (tag.startsWith("</")) {

                    if (stack.isEmpty()) return false;

                    if (!stack.peek().equals(tag.replace("</", "<"))) {
                        return false;
                    }
                    stack.pop();
                } else {
                    stack.push(tag);
                }
            }
        }
        return stack.isEmpty();
    }

    /*
        This function tests the files in the files array to see if
         they are valid.
         tags_valid.txt should return true;
         tags_invalid.txt should output as invalid;


     */
    public static void main(String[] args) throws FileNotFoundException {
        String[] files = {"tags_valid.txt", "tags_invalid.txt"};
        for (String fName : files) {
            System.out.print(fName + ": ");
            if (validate(fName)) {
                System.out.println("This file is valid");
            } else {
                System.out.println("This file is invalid");
            }
        }
    }


}