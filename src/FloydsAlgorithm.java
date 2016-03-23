
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
public class FloydsAlgorithm {

    private static int numOfVertices;
    private static int distanceTable[][];
    private static int sequenceTable[][];
    private static int distanceTableHardCode[][] = {
            {0,  0,  0,  0,  0,  0},
            {0,  0,  1, -1,  1,  5},
            {0,  9,  0,  3,  2, -1},
            {0, -1, -1,  0,  4, -1},
            {0, -1, -1,  2,  0,  3},
            {0,  3, -1, -1, -1,  0}};

    public static void main(String[] args)
    {
        System.out.print("Enter n for number of vertices: ");
        Scanner sysin = new Scanner( System.in );
        numOfVertices = sysin.nextInt();
        distanceTable = new int[numOfVertices][numOfVertices];
        sequenceTable = new int[numOfVertices][numOfVertices];


        // fill distance table (D0)
//        for(int i = 1; i <= numOfVertices; i++){
//            System.out.println("Enter row weights seperated by space: ");
//            for(int j = 1; j <= numOfVertices; j++)
//                distanceTable[i][j] = sysin.nextInt();
//
//        }



        // Initialize Sequence Table to 0
        for(int i = 0; i < numOfVertices; i++)
            for (int j = 0; j < numOfVertices; j++)
                sequenceTable[i][j] = 0;

        // Floyds

        // n tables
        for(int i = 0; i < numOfVertices; i++)


        // i = row number
        // j = column number
        // k = iteration number
        //if(distanceTable[i][j] > distanceTable[i][k] + distanceTable[k][j])
            // fill the cell C[i][j] in D[k] with the value D[i][k] + D[k][j] of D[k-1] table
        //else
            // fill the cell C[i][j] in D[k] with the value D[i][j] of D[k-1] table

        // we will always fill the cell C[i][k] in D[k] table with the smallest value


        //int[][] shortestPath = floyds(distanceTable, 1);

        print(distanceTableHardCode);




        int breakhere = 1;
    }

    private static void printRow(int[] row) {
        for (int i : row) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
    }

    private static void print(int[][] table){
        for(int[] row : table) {
            printRow(row);
        }
    }

//
//    public static int[][] floyds (int[][] D, int k){
//        int i = k + 1;
//        int j = i;
//        int [][]dSubKMinusOne = D;
//
//
//        for(; i <= numOfVertices; i++) {
//            for (; j <= numOfVertices; j++){
//                if((D[i][j] > dSubKMinusOne[i][k] + dSubKMinusOne[k][j]) || (D[i][j] == -1))
//                    D[i][j] = dSubKMinusOne[i][k] + dSubKMinusOne[k][j];
//            }
//        }
//        if(k == numOfVertices)
//            return D;
//        else
//            return floyds(D, ++k);
//
//
//    }


    public static int max(int num1, int num2){
        if (num1 > num2)
            return num1;
        else
            return num2;
    }

    public static int min(int num1, int num2){
        if(num1 < num2)
            return num1;
        else
            return num2;
    }
}
