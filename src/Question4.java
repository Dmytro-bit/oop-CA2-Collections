import Question4_extra.Cell;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;


public class Question4  // Flood Fill (Stack, 2D Array)
{
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        Scanner scanner = new Scanner(System.in);

        final int max_row = 10;
        final int max_column = 10;
        int number = 1;
        boolean csv_display = false; // When true, prints in CSV format


        int[][] arr = floodFillStart(max_row, max_column);

        Deque<Cell> point_stack = new ArrayDeque<>();


        System.out.println("Enter start row");
        int start_row = scanner.nextInt();
        System.out.println("Enter start column");
        int start_column = scanner.nextInt();

        Cell start_cell = new Cell(start_row, start_column);

        fill(start_cell.getRow(), start_cell.getColumn(), arr, number);
        point_stack.push(start_cell);

        while (!point_stack.isEmpty()) {
            Cell cell = point_stack.pop();
            number = getNeighbors(cell.getRow(), cell.getColumn(), point_stack, arr, number);

        }

        if (csv_display) {
            displayCSV(arr, max_row, max_column);
        } else {
            display(arr, max_row, max_column);
        }

    }

    /*
        Starter function to create the 2D array and populate it with zeros
     */
    public static int[][] floodFillStart(int max_row, int max_column) {
        int[][] arr = new int[max_row][max_column];
        for (int x = 0; x < max_row; x++) {
            for (int y = 0; y < max_column; y++) {
                arr[x][y] = 0;
            }
        }
        return arr;
    }

    /*
        Helper function to display the image
     */
    public static void display(int[][] arr, int max_row, int max_column) {
        for (int x = 0; x < max_row; x++) {
            for (int y = 0; y < max_column; y++) {
                System.out.printf("%4d", arr[x][y]);
            }
            System.out.println();
        }
    }

    public static void displayCSV(int[][] arr, int max_row, int max_column) {
        for (int x = 0; x < max_row; x++) {
            String end = ",";
            for (int y = 0; y < max_column; y++) {
                if (y == max_column - 1) end = "";
                System.out.printf("%3d%s", arr[x][y], end);
            }
            System.out.println();
        }
    }

    private static void fill(int row, int column, int[][] arr, int number) {
        arr[row][column] = number;
    }

    private static int getNeighbors(int row, int column, Deque<Cell> point_stack, int[][] arr, int number) {
        int row_bound = arr.length - 1;
        int column_bound = arr[0].length - 1;

        for (int i = 0; i < 4; i++) {
            switch (i) {

//              Cell up
                case 0:
                    if (row - 1 < 0) continue;
                    if (checkIfFilled(row - 1, column, arr)) continue;
                    number = processCell(row - 1, column, point_stack, arr, number);
                    break;
//              Cell right
                case 1:
                    if (column + 1 > column_bound) continue;
                    if (checkIfFilled(row, column + 1, arr)) continue;

                    number = processCell(row, column + 1, point_stack, arr, number);
                    break;
//              Cell down
                case 2:
                    if (row + 1 > row_bound) continue;
                    if (checkIfFilled(row + 1, column, arr)) continue;

                    number = processCell(row + 1, column, point_stack, arr, number);
                    break;
//             Cell left
                case 3:
                    if (column - 1 < 0) continue;
                    if (checkIfFilled(row, column - 1, arr)) continue;

                    number = processCell(row, column - 1, point_stack, arr, number);
                    break;

            }
        }
        return number;
    }

    private static boolean checkIfFilled(int row, int column, int[][] arr) {
        return arr[row][column] != 0;
    }

    private static int processCell(int row, int column, Deque<Cell> point_stack, int[][] arr, int number) {
        number++;
        fill(row, column, arr, number);
        point_stack.push(new Cell(row, column));
        return number;
    }

}