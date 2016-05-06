import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class KnapSackProblem {
  static int capacity;
  static int numOfItems;
  static List<Item> itemList = new ArrayList<>();

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
        itemList.add(new Item(scanner.nextInt(),scanner.nextInt()));

      this.start();
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

      this.start();
    }
  }

  /**
   * Solve 0-1 Knapsack using branch and bound
   */
  private void start(){

    System.out.println("Capacity of knapsack is " + capacity);
    System.out.println("Items are:");
    for(int i = 0; i < itemList.size(); i++) {
      Item item = itemList.get(i);
      System.out.println(i + 1 + ": " + item.profit + " " + item.weight);
    }
    System.out.println("\nBegin exploration of the possibilities tree:\n");

    Collections.sort(itemList, Item.byRatio());
    Node root = new Node();
    Node currentBest = root;
    root.computeBound();

    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(root);

    while(!pq.isEmpty()){
      Node node = pq.poll();
      System.out.println("Exploring: " + node.toString());
      if (node.bound > currentBest.profit && node.level < itemList.size() - 1) {



        Node without = new Node(node);
        without.computeBound();
        System.out.println("\tLeft child is: " + without.toString());

        if (without.bound > currentBest.profit) {
          pq.offer(without);
          System.out.println("\t\texplore further");
        }
        else
          System.out.println("\t\tpruned, don't explore children because bound " + without.bound +
                              " is smaller than known achievable profit " + currentBest.profit);



        Node with = new Node(node);
        Item item = itemList.get(node.level);
        with.weight += item.weight;
        with.items.add(itemList.get(node.level));
        with.profit += item.profit;
        with.computeBound();
        System.out.println("\tRight" +
                "" +
                " child is: " + with.toString());

        if (with.weight <= capacity) {
          if (with.profit > currentBest.profit) {
            currentBest = with;
            System.out.println("\t\tnote achievable profit of " + currentBest.profit);
          }
          if (with.bound > currentBest.profit) {
            pq.offer(with);
            System.out.println("\t\texplore further");
          }
          else
            System.out.println("\t\tpruned, don't explore children because bound " + with.bound +
                    " is smaller than known achievable profit " + currentBest.profit);
        }
        else
          System.out.println("\t\tpruned because too heavy");
      }
    }

    System.out.println("\n\n The best node is: " + currentBest.toString());
  }
}
