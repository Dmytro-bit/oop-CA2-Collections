import java.util.*;

public class Question6      // Flight take-off (Queue)
{
    final public String[] commandList = {"next", "land", "takeoff"};
    public Queue<String> landLane = new LinkedList<>();
    public Queue<String> takeoffLane = new LinkedList<>();

    public void executeCommand(String query) {
        if(!query.split("\\s+")[0].equalsIgnoreCase("next"))
        {
            (query.split("\\s+")[0].equals("takeoff") ? takeoffLane : landLane).add(query.split("\\s+")[1]);
            System.out.println(query.split("\\s+")[1] + " is added to a " + query.split("\\s+")[0] + " queue");
        }
        else {
            if (!landLane.isEmpty()) {
                System.out.println("Processing " + landLane.remove() + " from landing queue.");
            } else if (!takeoffLane.isEmpty()) {
                System.out.println("Processing " + takeoffLane.remove() + " from takeoff queue.");
            } else {
                System.out.println("No flights in either queue to process.");
            }
        }
    }

    public String enterCommand()
    {
        for(String command : commandList)
        {
            System.out.println("• " + command);
        }
        try
        {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter command: ");
            String command = input.nextLine();
            String flightNumberRegex = "Flight-\\d{3}";

            String[] commandLine = command.split("\\s+");
            if(commandLine.length == 2 && Arrays.asList(commandList).contains(commandLine[0].toLowerCase()) &&
                    commandLine[1].matches(flightNumberRegex) || commandLine[0].equalsIgnoreCase("next"))
                return command.trim();
            else
            {
                System.out.println("This command should contain a flight number. e.g. takeoff Flight-100");
                throw new InputMismatchException();
            }
        }
        catch (InputMismatchException e)
        {
            System.out.println("You must enter a valid command.");
            enterCommand();
        }
        return " ";
    }

    public void simulation()
    {
        do
        {
            System.out.println("Land Lane : " + landLane);
            System.out.println("Take off Lane : " + takeoffLane);
            executeCommand(enterCommand());
        } while (!landLane.isEmpty() || !takeoffLane.isEmpty());
    }

    public static void main(String[] args)
    {
        Question6 q = new Question6();
        q.simulation();
    }
}