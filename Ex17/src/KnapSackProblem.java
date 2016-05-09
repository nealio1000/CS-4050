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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

class KnapSackProblem {
  private int capacity;
  private int numOfItems;
  private List<Item> itemList = new ArrayList<>();
  private Node currentBest;
  private PriorityQueue<Node> pq;

  /**
   * Constructor for Knapsack Problem w/ canned Ex9 data
   */
  KnapSackProblem(){
    Scanner scanner = null;
    try {
      scanner = new Scanner(new FileReader("ex9"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    if (scanner != null) {
      capacity = scanner.nextInt();
      numOfItems = scanner.nextInt();
      for(int i = 0; i < numOfItems; i++)
        itemList.add(new Item(scanner.nextInt(), scanner.nextInt()));

      this.init();
    }
  }

  /** Constructor for Knapsack problem w/ specified data file
   *
   * @param filename 0-1 knapsack data file
   */
  KnapSackProblem(String filename) {
    Scanner scanner = null;
    try {
      scanner = new Scanner(new FileReader(filename));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    if (scanner != null) {
      capacity = scanner.nextInt();
      numOfItems = scanner.nextInt();
      for(int i = 0; i < numOfItems; i++)
        itemList.add(new Item(scanner.nextInt(),scanner.nextInt()));

      this.init();
    }
  }

  /**
   * Initialize problem with root node and sort item list by price/weight ratio
   */
  private void init(){
    printItemList(itemList);
    Collections.sort(itemList, Item.byRatio());
    Node root = new Node();
    currentBest = root;
    root.setBound(computeBound(root));
    pq = new PriorityQueue<>();
    pq.offer(root);
  }

  /**
   * Solve this 0-1 Knapsack problem using Branch & Bound
   */
  void solveWithBranchAndBound() {
    System.out.println("\nBegin exploration of the possibilities tree:");
    while (!pq.isEmpty())
      exploreNode(pq.poll());
    System.out.println("\nBest node: " + currentBest.toString());

    //reset NodeIds and ItemIds for next run
    Node.currentId = 0;
    Item.currentId = 0;
  }

  /**
   * Print the item list
   *
   * @param items the item list to print
   */
  private void printItemList(List<Item> items){
    System.out.println("\n\n-----------------------------------------------------------------------------------------");
    System.out.println("\nCapacity of knapsack is " + capacity);
    System.out.println("Items are:");
    for (Item item : items) {
      System.out.println(item.id + ": " + item.profit + " " + item.weight);
    }
  }

  /**
   * Explore a node
   * @param node the node to explore
   */
  private void exploreNode(Node node){
    System.out.println("\nExploring " + node.toString());
    if (node.bound > currentBest.profit && node.level < itemList.size() - 1) {
      exploreNodeWithoutItem(node);
      exploreNodeWithItem(node);
    }
    else
      System.out.println("\tpruned, don't explore children because bound " + node.bound +
              " is smaller than known achievable profit " + currentBest.profit);
  }

  /**
   * Explore the child node where the decision is to select the item
   *
   * @param parent the parent node
   */
  private void exploreNodeWithItem(Node parent) {
    Node withItem = new Node(parent);
    Item item = itemList.get(parent.level);
    withItem.weight += item.weight;
    withItem.items.add(itemList.get(parent.level));
    withItem.profit += item.profit;
	// Forces the bound to use the profit when over cap.
	if ( withItem.weight > capacity )
	{
		withItem.setBound(withItem.profit);
	}
    System.out.println("\tRight child is " + withItem.toString());
	withItem.setBound(computeBound(withItem));
	

    if (withItem.weight == capacity) {
      System.out.println("\t\thit capacity exactly so don't explore further");
      if (withItem.profit > currentBest.profit) {
        currentBest = withItem;	
        System.out.println("\t\tnote achievable profit of " + currentBest.profit);
      }
      if (withItem.bound > currentBest.profit) {
        pq.offer(withItem);
      }
    }
	else if (withItem.weight < capacity) {
      System.out.println("\t\texplore further");
      if (withItem.profit > currentBest.profit) {
        currentBest = withItem;
        System.out.println("\t\tnote achievable profit of " + currentBest.profit);
      }
      if (withItem.bound > currentBest.profit) {
        pq.offer(withItem);
      }
    }
	else
      System.out.println("\t\tpruned because too heavy");
  }

  /**
   * Explore the child node where the decision is to not select the item
   *
   * @param parent the parent node
   */
  private void exploreNodeWithoutItem(Node parent) {
    Node withoutItem = new Node(parent);
    withoutItem.setBound(computeBound(withoutItem));
    System.out.println("\tLeft child is " + withoutItem.toString());

    if (withoutItem.bound > currentBest.profit) {
      pq.offer(withoutItem);
      System.out.println("\t\texplore further");
    }
  }

  /**
   * Starting at the current tree depth, compute the bound of this node.
   *
   * @return the bound
   */
  private double computeBound(Node node) {
    double bound = node.profit;
    int startWeight = node.weight;
    Item item;

    int i = node.level;
    do {
      item = itemList.get(i);
      if (startWeight + item.weight > capacity)
        break;
      startWeight += item.weight;
      bound += item.profit;
      i++;
    } while (i < numOfItems);
    bound += (capacity - startWeight) * (item.profit / item.weight);

    return bound;
  }
}
