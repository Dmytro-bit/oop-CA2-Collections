import java.util.*;

public class Question10 {

    //function to evaluate the starting point
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
        Deque<int[]> junctions = new ArrayDeque<>();
        Deque<DIRECTION> moves = new ArrayDeque<>();

        //sets the current point
        int[] currPoint = Arrays.copyOf(startPoint, 2);
        //pushes the begging point into a stack
        junctions.push(Arrays.copyOf(currPoint, 2));

        while (!junctions.isEmpty()) {

            //checks if the exit is found
            if (isExit(currPoint, maze)) {
                System.out.println("Exit reached!");
                return path;
            }

            //flag variable
            boolean moved = false;

            //loops through every direction
            for (DIRECTION dir : DIRECTION.values()) {
                int[] nextPoint = Arrays.copyOf(currPoint, 2);

                switch (dir) {
                    case NORTH: nextPoint[0] -= 1; break;
                    case SOUTH: nextPoint[0] += 1; break;
                    case WEST: nextPoint[1] -= 1; break;
                    case EAST: nextPoint[1] += 1; break;
                }

                //checks if the next step is valid
                if (isValidMove(maze, nextPoint)) {
                    //marks the cell as visited to avoid revisiting
                    maze[nextPoint[0]][nextPoint[1]] = '#';
                    //make a step
                    currPoint = nextPoint;
                    path.add(dir);
                    moves.push(dir);
                    junctions.push(Arrays.copyOf(currPoint, 2));
                    moved = true;
                    break;
                }
            }

            //handles dead-end
            if (!moved) {
                junctions.pop();
                if (!junctions.isEmpty()) {
                    //comes back to a valid move
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

    public static void main(String[] args) {
        Stack<DIRECTION> dirs = new Stack<>();
        dirs.push(DIRECTION.WEST);
        dirs.push(DIRECTION.SOUTH);
        dirs.push(DIRECTION.EAST);
        dirs.push(DIRECTION.NORTH);

        char[][] maze = new char[][]{{'#', '#', '#', '#', '#', '#', '#', '#'},
                                     {'#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
                                     {'#', '#', '#', '#', ' ', '#', '#', '#'},
                                     {'#', ' ', ' ', ' ', 'X', ' ', ' ', '#'},
                                     {'#', '#', '#', '#', ' ', '#', '#', '#'},
                                     {' ', ' ', ' ', ' ', ' ', '#', '#', '#'},
                                     {'#', '#', '#', '#', ' ', '#', '#', '#'},
                                     {'#', '#', '#', '#', '#', '#', '#', '#'}};

        String values = Arrays.toString(solveMaze(maze, getStartingPoint(maze)).toArray());
        System.out.println(values);
    }

}