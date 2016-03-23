
import java.util.Scanner;

/**
 * Neal Friedman
 * CS 4050
 * Ex 11
 *
 *
 * Your program must ask the user, from the keyboard, for the defining information for
 * a directed graph, in precisely this order: number of vertices, followed by the weights,
 * row by row and column by column, where -1 is used to indicate that there is no edge between
 * the two vertices.Then the program must display, preferably nicely formatted,
 * the successive tables D0,D1, : : :, Dn, with -1 indicated as a dash, and each cell
 * containing both the optimal length and the intermediate vertex, if any, that produces it
 * (if the optimal length is just an edge, use 0 as the intermediate vertex).
 */
public class Floyd {

    private static int[][] distanceTable;
    private static int[][] pathTable;
    private static int numOfVertices;

    // Hardcoded table for testing
//    private static int [][] distanceTable = {
//        { 0,  1, -1,  1,  5},
//        { 9,  0,  3,  2, -1},
//        { -1, -1,  0,  4, -1},
//        { -1, -1,  2,  0,  3},
//        { 3,  -1, -1, -1,  0}};


    public static void main(String[] args)
    {
        // Wait for user input
        // TODO make sure user input is correct
        System.out.print("Enter n for number of vertices: ");
        Scanner sysin = new Scanner( System.in );
        numOfVertices = sysin.nextInt();
        distanceTable = new int[numOfVertices][numOfVertices];
        pathTable = new int[numOfVertices][numOfVertices];

        for(int i = 0; i < numOfVertices; i++){
            System.out.println("Enter row weights seperated by space: ");
                for(int j = 0; j < numOfVertices; j++)
                        distanceTable[i][j] = sysin.nextInt();
        }

        floydsAlgorithm();
    }

    private static void floydsAlgorithm(){
        // Print initial table
        System.out.println("\n-------------------------- D(0) --------------------------");
        print(distanceTable, pathTable);
        System.out.println("----------------------------------------------------------");

        // Floyds Algorithm
        // k = iteration number
        // i = row number
        // j = column number
        for(int k = 0; k < numOfVertices; k++) {
            for (int i = 0; i < numOfVertices; i++) {
                for (int j = 0; j < numOfVertices; j++) {
                    if ((distanceTable[i][j] > (distanceTable[i][k] + distanceTable[k][j])
                            || distanceTable[i][j] < 0)
                            && distanceTable[k][j] > 0
                            && distanceTable[i][k] > 0) {
                        distanceTable[i][j] = distanceTable[i][k] + distanceTable[k][j];
                        pathTable[i][j] = k+1;
                    }
                }
            }
            System.out.println("\n-------------------------- D(" + (k+1) + ") --------------------------");
            print(distanceTable, pathTable);
            System.out.println("----------------------------------------------------------");
        }
    }
    
    private static void print(int[][] table1, int[][] table2){
        for(int i = 0; i < table1.length && i < table2.length; i++){
            for(int k = 0; k < table1[i].length && k < table2[i].length; k++){
                if(table1[i][k] == -1)
                    System.out.print("-" + " (" + table2[i][k] + ")");
                else
                    System.out.print(table1[i][k] + " (" + table2[i][k] + ")");
                System.out.print("\t||\t");
            }
            System.out.println();
        }
    }
}
