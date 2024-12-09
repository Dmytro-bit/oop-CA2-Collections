import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question5 {    //Java Identifier Count (Map)

    public static void main(String[] args) throws FileNotFoundException {
        readFile("src/test.java");
    }

    public static void readFile(String fileName) throws FileNotFoundException {
        Map<String, Integer> identifierCountMap = new HashMap<>();
        Map<String, ArrayList<String>> identifierLineMap = new HashMap<>();
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
                }
                else {
                    identifierCountMap.put(identifier, 1);
                }

                if (!identifierLineMap.containsKey(identifier)) {
                    identifierLineMap.put(identifier, new ArrayList<>());
                }
                identifierLineMap.get(identifier).add(lineNumber + ". " + line.trim());
            }
        }

        ArrayList<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(identifierCountMap.entrySet());

        sortedEntries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));


        for (Map.Entry<String, Integer> identifier : sortedEntries) {
            System.out.println("------------------------------------------");
            System.out.println(identifier.getKey() + ": " + identifier.getValue());
            for (String line : identifierLineMap.get(identifier.getKey())) {
                System.out.println(line);
            }
        }
    }
}