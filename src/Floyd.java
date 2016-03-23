
import java.util.Scanner;

/**
 * Your program must ask the user, from the keyboard, for the defining information for
 * a directed graph, in precisely this order: number of vertices, followed by the weights,
 * row by row and column by column, where -1 is used to indicate that there is no edge between
 * the two vertices.Then the program must display, preferably nicely formatted,
 * the successive tables D0,D1, : : :, Dn, with -1 indicated as a dash, and each cell
 * containing both the optimal length and the intermediate vertex, if any, that produces it
 * (if the optimal length is just an edge, use 0 as the intermediate vertex).
 */
public class Floyd {

    private static int numOfVertices;
    private static int distanceTable[][];
    private static int distanceTableHardCode[][] = {
            { 0,  1, -1,  1,  5},
            { 9,  0,  3,  2, -1},
            { -1, -1,  0,  4, -1},
            { -1, -1,  2,  0,  3},
            { 3,  -1, -1, -1,  0}};

    public static void main(String[] args)
    {
        System.out.print("Enter n for number of vertices: ");
        Scanner sysin = new Scanner( System.in );
        numOfVertices = sysin.nextInt();
        distanceTable = new int[numOfVertices][numOfVertices];

        // Print initial table
        System.out.println("\n---------------- D(0) ----------------");
        print(distanceTableHardCode);
        System.out.println("--------------------------------------");


        // Floyds Algorithm
        // k = iteration number
        // i = row number
        // j = column number
        for(int k = 0; k < numOfVertices; k++) {
            for (int i = 0; i < numOfVertices; i++) {
                for (int j = 0; j < numOfVertices; j++) {
                    if ((distanceTableHardCode[i][j] > (distanceTableHardCode[i][k] + distanceTableHardCode[k][j])
                            || distanceTableHardCode[i][j] < 0)
                            && distanceTableHardCode[k][j] > 0
                            && distanceTableHardCode[i][k] > 0)
                        distanceTableHardCode[i][j] = distanceTableHardCode[i][k] + distanceTableHardCode[k][j];
                }
            }
            System.out.println("\n---------------- D(" + (k+1) + ") ----------------");
            print(distanceTableHardCode);
            System.out.println("--------------------------------------");
        }
    }

    private static void printRow(int[] row) {
        for (int i : row) {
            if(i == -1)
                System.out.print("-");
            else
                System.out.print(i);
            System.out.print("\t||\t");
        }
        System.out.println();
    }

    private static void print(int[][] table){
        for(int[] row : table) {
            printRow(row);
        }
    }
}
