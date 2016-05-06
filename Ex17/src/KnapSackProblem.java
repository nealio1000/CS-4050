import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class KnapSackProblem {
  public static int capacity;

  private int numOfItems;
  public static List<Item> itemList = new ArrayList<>();

  public KnapSackProblem(){
    Scanner scanner = null;
    try {
      scanner = new Scanner(new FileReader("ex9"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    this.capacity = scanner.nextInt();
    this.numOfItems = scanner.nextInt();

    for(int i = 0; i < numOfItems; i++)
      itemList.add(new Item(scanner.nextInt(),scanner.nextInt(), capacity));

    this.start();
  }
  public KnapSackProblem(String filename){
    Scanner scanner = null;
    try {
      scanner = new Scanner(new FileReader(filename));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    this.capacity = scanner.nextInt();
    this.numOfItems = scanner.nextInt();

    for(int i = 0; i < numOfItems; i++)
      itemList.add(new Item(scanner.nextInt(),scanner.nextInt(), capacity));

    this.start();
  }

  public void start(){
    Collections.sort(itemList, Item.byRatio());
    Node root = new Node();
    Node best = new Node();
    root.computeBound();

    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(root);

    while(!pq.isEmpty()){
      Node node = pq.poll();
      if (node.bound > best.profit && node.height < itemList.size() - 1) {

        Node with = new Node(node);
        Item item = itemList.get(node.height);
        with.weight += item.weight;

        if (with.weight <= capacity) {

          with.items.add(itemList.get(node.height));
          with.profit += item.profit;
          with.computeBound();

          if (with.profit > best.profit) {
            best = with;
          }
          if (with.bound > best.profit) {
            pq.offer(with);
          }
        }

        Node without = new Node(node);
        without.computeBound();

        if (without.bound > best.profit) {
          pq.offer(without);
        }
      }
    }

    int breakhere = 1;

  }
}
