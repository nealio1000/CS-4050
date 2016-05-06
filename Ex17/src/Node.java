import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node>{
  public List<Item> items;
  private static int currentId = 0;
  public int id;
  public int height;
  public int profit;
  public int weight;
  public int bound;

  public Node(){
    currentId++; this.id = currentId;
    this.items = new ArrayList<>();
  }

  public Node(Node parentNode){
    currentId++; this.id = currentId;
    this.height = parentNode.height + 1;
    this.items = new ArrayList<Item>(parentNode.items);
    this.bound = parentNode.bound;
    this.profit = parentNode.profit;
    this.weight = parentNode.weight;
  }

  @Override
  public String toString() {
    return "Node{" +
            "id=" + id +
            ", items=" + items +
            ", profit=" + profit +
            ", weight=" + weight +
            ", bound=" + bound +
            '}';
  }

  public int computeBound() {
    bound = profit;
    int w = weight;
    Item item = null;
    for(int i = height; i < KnapSackProblem.numOfItems; i++){
      item = KnapSackProblem.itemList.get(i);
      if(item != null) {
        if (weight + item.weight > KnapSackProblem.capacity)
          break;
        w += item.weight;
        bound += item.profit;
      }
    }

    if(item != null)
      bound += (KnapSackProblem.capacity - w) * (item.profit/ item.weight);

    return bound;
  }

  @Override
  public int compareTo(Node other) {
    if(this.bound < other.bound)
      return -1;
    if(this.bound > other.bound)
      return 1;
    return 0;
  }
}
