import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

public class Question11
{

    public static void main(String[] args) throws FileNotFoundException {
//      Test Test Test
        String city1,city2;
        int distance;
        String from = "";
        int counter = 0;
        File file = new File("citiesQ11.txt");
        Scanner scanner =  new Scanner(file);
        HashMap<String, TreeSet<DistanceTo>> directRoutes = new HashMap<>();
        while (scanner.hasNextLine()){
            city1 = scanner.next();
            city2 = scanner.next();
            distance = scanner.nextInt();
            DistanceTo distanceTo2 = new DistanceTo(city2,distance);
            DistanceTo distanceTo1 = new DistanceTo(city1,distance);
            if(counter<1){
                from = city1;
                counter++;
            }

            directRoutes.putIfAbsent(city1, new TreeSet<>());
            directRoutes.putIfAbsent(city2, new TreeSet<>());

            directRoutes.get(city1).add(distanceTo2);
            directRoutes.get(city2).add(distanceTo1);
        }

        DistanceTo distance0 = new DistanceTo(from, 0);
        PriorityQueue<DistanceTo> pq = new PriorityQueue<>();
        HashMap<String, Integer> shortestKnownDistances = new HashMap<>();

        pq.add(distance0);
        while(!pq.isEmpty()){
            DistanceTo smallest = pq.poll();
            String target = smallest.getTarget();
            if(!shortestKnownDistances.containsKey(target)){
                int d = smallest.getDistance();
                shortestKnownDistances.put(target,d);
                directRoutes.forEach((key, value) ->{
                    value.forEach(distanceTo -> {
                        if(distanceTo.getTarget().equals(target)){
                            pq.add(new DistanceTo(key,d+distanceTo.getDistance()));
                        }
                    });
                });
            }

        }
        shortestKnownDistances.forEach((key, value) -> {
            System.out.println(key + " - " + value);
        });
    }
}