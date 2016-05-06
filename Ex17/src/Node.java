import java.util.ArrayList;
import java.util.List;

class Node implements Comparable<Node>{
  List<Item> items;
  private static int currentId = 0;
  private int id;
  int level;
  int profit;
  int weight;
  double bound;

  Node(){
    currentId++; this.id = currentId;
    this.items = new ArrayList<>();
  }

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
    return "<Node " + id + ":   " +
            " items: " + items +
            " level: " + level +
            " profit: " + profit +
            " weight: " + weight +
            " bound: " + bound +">";
  }

  double computeBound() {
    bound = profit;
    int w = weight;
    Item item;
//    for(int i = level; i < KnapSackProblem.numOfItems; i++){
//      item = KnapSackProblem.itemList.get(i);
//      if(item != null) {
//        if (weight + item.weight > KnapSackProblem.capacity)
//          break;
//        w += item.weight;
//        bound += item.profit;
//      }
//    }
    int i = level;
    do {
      item = KnapSackProblem.itemList.get(i);
      if (w + item.weight > KnapSackProblem.capacity) break;
      w += item.weight;
      bound += item.profit;
      i++;
    } while (i < KnapSackProblem.numOfItems);


    bound += (KnapSackProblem.capacity - w) * (item.profit/ item.weight);

    return bound;
  }

  @Override
  public int compareTo(Node other) {
    if(this.bound < other.bound)
      return 1;
    if(this.bound > other.bound)
      return -1;
    return 0;
  }
}
