/**
 * CS 4050
 * Exercise 17
 *
 * 0-1 Knapsack Problem
 * Branch & Bound Solution
 *
 * Instructions:
 *
 * javac *.java
 * java Ex17 <optional_data_file_argument>
 *
 * By Neal Friedman and Brunno Putnam
 */

public class Ex17 {
  public static void main (String[] args){
    String fname = "";
    if( args.length != 1 )
      fname = FileBrowser.chooseFile( true );
    else
      fname = args[0];

    if(!fname.equals("")) {
      KnapSackProblem knapSackProblem = new KnapSackProblem(fname);
      knapSackProblem.solveWithBranchAndBound();
    }
    else
      System.out.println("***ERROR*** Please select a file with the file browser or pass one in \nas a program argument");

  }
}
