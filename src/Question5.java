import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question5 {    //Java Identifier Count (Map)

    public static void main(String[] args) throws FileNotFoundException {
        readFile("src/Question2.java");
    }

    public static void readFile(String fileName) throws FileNotFoundException {
        TreeMap<String, Integer> identifierCountMap = new TreeMap<>();
        TreeMap<String, ArrayList<String>> identifierLineMap = new TreeMap<>();

        final String regex = "\\b[A-Za-z_][A-Za-z0-9_]*\\b";
        int lineNumber = 0;


        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            lineNumber++;
            String line = scanner.nextLine();

            Matcher matcher = Pattern.compile(regex).matcher(line);

            while (matcher.find()) {
                String identifier = matcher.group();


                if (identifierCountMap.containsKey(identifier)) {
                    identifierCountMap.put(identifier, identifierCountMap.get(identifier) + 1);
                } else {
                    identifierCountMap.put(identifier, 1);
                }

                if (!identifierLineMap.containsKey(identifier)) {
                    identifierLineMap.put(identifier, new ArrayList<>());
                }
                identifierLineMap.get(identifier).add(lineNumber + ". " + line.trim());
            }
        }

        for (String key : identifierCountMap.keySet()) {
            System.out.println("------------------------------------------");
            System.out.println(key + ": " + identifierCountMap.get(key));
            for (String line : identifierLineMap.get(key)) {
                System.out.println(line);
            }
        }
    }
}