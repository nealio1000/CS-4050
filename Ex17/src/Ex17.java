/**
 * CS 4050
 * Exercise 17
 *
 * 0-1 Knapsack Problem
 * Branch & Bound Solution
 *
 * By Neal Friedman and Brunno Putnam
 */

public class Ex17 {
  public static void main (String[] args){
    KnapSackProblem knapSackProblemEx9 = new KnapSackProblem();
    knapSackProblemEx9.solveWithBranchAndBound();
    KnapSackProblem knapSackProblemTest2 = new KnapSackProblem("test2");
    knapSackProblemTest2.solveWithBranchAndBound();
  }
}
