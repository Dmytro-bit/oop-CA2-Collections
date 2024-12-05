import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Question2  // Car Parking - Stack
{
    public static void main(String[] args) {
        runSimulation();
    }

    public static void runSimulation() {
        Deque<Integer> driveway = new ArrayDeque<>();
        Deque<Integer> street = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-----------------------------------------");
            System.out.println("Please enter a command: ");
            String command_str = scanner.nextLine();
            if (!isNumeric(command_str)) continue;

            int command = Integer.parseInt(command_str);

            if (command == 0) {
                break;
            }

            if (command > 0) {
                if (inDriveway(command, driveway)) {
                    System.out.println("Car already in driveway");
                } else {
                    driveway.push(command);
                }
            } else {
                int numberPlate = -command;
                Integer currentNumberPlate;

                if (!inDriveway(numberPlate, driveway)) {
                    System.out.println("Car not in driveway");
                    continue;
                }

                while (true) {
                    currentNumberPlate = driveway.pop();
                    if (currentNumberPlate.equals(numberPlate)) {
                        System.out.printf("Car %d was removed from driveway\n", currentNumberPlate);
                        break;
                    }
                    street.push(currentNumberPlate);
                }
            }

            emptyStreet(driveway, street);

            System.out.print("Driveway: ");
            System.out.println(driveway);

        }

    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Command is not numeric");
            return false;
        }
    }

    public static boolean inDriveway(int number, Deque<Integer> driveway) {
        return driveway.contains(number);
    }

    public static void emptyStreet(Deque<Integer> driveway, Deque<Integer> street) {
        while (!street.isEmpty()) {
            driveway.push(street.pop());
        }
    }
}