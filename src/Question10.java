import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Question10 {
    public static void display(int[][] image) {
        for (int x = 0; x < image.length; x++) {
            for (int y = 0; y < image[0].length; y++) {
                System.out.printf("%4d", image[x][y]);
            }
            System.out.println();
        }
    }

    public static int[] getStartingPoint(final char[][] maze)
    {
        int[] startingPoint = new int[2];
        for(int i = 0; i < maze.length; i++)
        {
            for(int j = 0; j < maze[0].length; j++)
            {
                if(maze[i][j] == 'X')
                {
                    startingPoint[0] = i;
                    startingPoint[1] = j;
                    return startingPoint;
                }
            }
        }
        return startingPoint;
    }

    public static ArrayList<DIRECTION> solveMaze(final char[][] maze, int[] startPoint) {
        ArrayList<DIRECTION> path = new ArrayList<>();
        Stack<int[]> junctions = new Stack<>();
        Stack<DIRECTION> moves = new Stack<>();

        int[] currPoint = Arrays.copyOf(startPoint, 2);
        junctions.push(Arrays.copyOf(currPoint, 2));

        while (!junctions.isEmpty()) {

            if (isExit(currPoint, maze)) {
                System.out.println("Exit reached!");
                return path;
            }

            boolean moved = false;

            for (DIRECTION dir : DIRECTION.values()) {
                int[] nextPoint = Arrays.copyOf(currPoint, 2);

                switch (dir) {
                    case NORTH: nextPoint[0] -= 1; break;
                    case SOUTH: nextPoint[0] += 1; break;
                    case WEST: nextPoint[1] -= 1; break;
                    case EAST: nextPoint[1] += 1; break;
                }

                if (isValidMove(maze, nextPoint)) {
                    maze[nextPoint[0]][nextPoint[1]] = '#'; // Mark as visited
                    currPoint = nextPoint;
                    path.add(dir);
                    moves.push(dir);
                    junctions.push(Arrays.copyOf(currPoint, 2));
                    moved = true;
                    break;
                }
            }

            if (!moved) {
                junctions.pop();
                if (!junctions.isEmpty()) {
                    currPoint = junctions.peek();
                    if (!moves.isEmpty()) {
                        path.removeLast();
                        moves.pop();
                    }
                }
            }
        }

        System.out.println("No exit found. Explored Path: " + path);
        return path;
    }

    public static boolean isValidMove(char[][] maze, int[] point) {
        int x = point[0], y = point[1];
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == ' ';
    }
    public static boolean isExit(int[] currPoint, char[][] maze) {
        int x = currPoint[0], y = currPoint[1];
        return (x == 0 || x == maze.length - 1 || y == 0 || y == maze[0].length - 1);
    }

}