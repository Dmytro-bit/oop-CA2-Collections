package question11Extra;

public class DistanceTo implements Comparable<DistanceTo> {
    private String target;
    private int distance;

    public DistanceTo(String city, int dist) {
        target = city;
        distance = dist;
    }

    public String getTarget() {
        return target;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "question11Extra.DistanceTo{" +
                "distance=" + distance +
                ", target='" + target + '\'' +
                '}';
    }

    public int compareTo(DistanceTo other) {
         if(distance - other.distance !=0){
             return Integer.compare(distance, other.distance);
         }
         return target.compareTo(other.target);

    }
}