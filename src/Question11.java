import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class Question11
{

    public static void main(String[] args) throws FileNotFoundException {
//      Test Test Test
        String city1,city2;
        int distance;
        File file = new File("citiesQ11.txt");
        Scanner scanner =  new Scanner(file);
        HashMap<String, TreeSet<DistanceTo>> directRoutes = new HashMap<>();
        while (scanner.hasNextLine()){
            city1 = scanner.next();
            city2 = scanner.next();
            distance = scanner.nextInt();
            DistanceTo distanceTo2 = new DistanceTo(city2,distance);
            DistanceTo distanceTo1 = new DistanceTo(city1,distance);

            directRoutes.putIfAbsent(city1, new TreeSet<>());
            directRoutes.putIfAbsent(city2, new TreeSet<>());

            directRoutes.get(city1).add(distanceTo2);
            directRoutes.get(city2).add(distanceTo1);
        }

        System.out.println(directRoutes);

    }
}