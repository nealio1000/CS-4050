import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

class KnapSackProblem {
  static int capacity;
  static int numOfItems;
  static List<Item> itemList = new ArrayList<>();
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
  public KnapSackProblem(String filename){
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
    root.computeBound();
    pq = new PriorityQueue<>();
    pq.offer(root);

    System.out.println("\nBegin exploration of the possibilities tree:");
    while(!pq.isEmpty())
      exploreNode(pq.poll());

    System.out.println("\n\nBest node is: " + currentBest.toString());
  }

  /**
   * Print the item list
   *
   * @param items the item list to print
   */
  private void printItemList(List<Item> items){
    System.out.println("Capacity of knapsack is " + capacity);
    System.out.println("Items are:");
    for(int i = 0; i < itemList.size(); i++) {
      Item item = itemList.get(i);
      System.out.println(i + 1 + ": " + item.profit + " " + item.weight);
    }
  }

  /**
   * Explore a node's left and right child where the left child is the node without the item
   * and the right child is the node with the item
   * @param node the node to explore
   */
  private void exploreNode(Node node){
    System.out.println("\nExploring: " + node.toString());
    if (node.bound > currentBest.profit && node.level < itemList.size() - 1) {

      Node without = new Node(node);
      without.computeBound();
      System.out.println("\tLeft child is: " + without.toString());

      if (without.bound > currentBest.profit) {
        pq.offer(without);
        System.out.println("\t\texplore further");
      }

      Node with = new Node(node);
      Item item = itemList.get(node.level);
      with.weight += item.weight;
      with.items.add(itemList.get(node.level));
      with.profit += item.profit;
      with.computeBound();
      System.out.println("\tRight child is: " + with.toString());

      if (with.weight <= capacity) {
        System.out.println("\t\texplore further");
        if (with.profit > currentBest.profit) {
          currentBest = with;
          System.out.println("\t\tnote achievable profit of " + currentBest.profit);
        }
        if (with.bound > currentBest.profit) {
          pq.offer(with);
        }
      }
      else
        System.out.println("\t\tpruned because too heavy");
    }
    else
      System.out.println("\t\tpruned, don't explore children because bound " + node.bound +
              " is smaller than known achievable profit " + currentBest.profit);
  }
}
