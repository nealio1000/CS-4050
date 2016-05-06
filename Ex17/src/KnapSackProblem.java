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
    System.out.println("\n\nBest node is: " + currentBest.toString());

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
    System.out.println("\nExploring: " + node.toString());
    if (node.bound > currentBest.profit && node.level < itemList.size() - 1) {
      exploreNodeWithoutItem(node);
      exploreNodeWithItem(node);
    }
    else
      System.out.println("\t\tpruned, don't explore children because bound " + node.bound +
              " is smaller than known achievable profit " + currentBest.profit);
  }

  /**
   * Explore the child node where the decision is to select the item
   *
   * @param parent the parent node
   */
  private void exploreNodeWithItem(Node parent) {
    Node with = new Node(parent);
    Item item = itemList.get(parent.level);
    with.weight += item.weight;
    with.items.add(itemList.get(parent.level));
    with.profit += item.profit;
    with.setBound(computeBound(with));
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
    } else
      System.out.println("\t\tpruned because too heavy");
  }

  /**
   * Explore the child node where the decision is to not select the item
   *
   * @param parent the parent node
   */
  private void exploreNodeWithoutItem(Node parent) {
    Node without = new Node(parent);
    without.setBound(computeBound(without));
    System.out.println("\tLeft child is: " + without.toString());

    if (without.bound > currentBest.profit) {
      pq.offer(without);
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
    int w = node.weight;
    Item item;

    int i = node.level;
    do {
      item = itemList.get(i);
      if (w + item.weight > capacity)
        break;
      w += item.weight;
      bound += item.profit;
      i++;
    } while (i < numOfItems);

    bound += (capacity - w) * (item.profit / item.weight);

    return bound;
  }
}
