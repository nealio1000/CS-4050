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

import java.util.ArrayList;
import java.util.List;

class Node implements Comparable<Node>{
  static int currentId = 0;
  List<Item> items;
  int level;
  int profit;
  int weight;
  double bound;
  private int id;

  /**
   * Constructor for a root node
   */
  Node(){
    currentId++; this.id = currentId;
    this.items = new ArrayList<>();
  }

  /**
   * Constructor for a child node
   *
   * @param parentNode the parent node
   */
  Node(Node parentNode){
    currentId++; this.id = currentId;
    this.level = parentNode.level + 1;
    this.items = new ArrayList<>(parentNode.items);
    this.bound = parentNode.bound;
    this.profit = parentNode.profit;
    this.weight = parentNode.weight;
  }

  @Override
  public String toString() {
    return "<Node " + id + ":" +
            " items: " + items +
            " level: " + level +
            " profit: " + profit +
            " weight: " + weight +
            " bound: " + bound +">";
  }

  void setBound(double bound) {
    this.bound = bound;
  }


  /**
   * Compare this node with some other node based on the node's bound
   * @param other the other node to compare this node with
   * @return Return 1 if the bound on this node is smaller than the bound of the other node. Return -1 if
   * the bound on this node is larger than the bound of the other node. Return 0 if the bounds are equal.
   */
  @Override
  public int compareTo(Node other) {
    if(this.bound < other.bound)
      return 1;
    if(this.bound > other.bound)
      return -1;
    return 0;
  }
}
