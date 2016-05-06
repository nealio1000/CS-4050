import java.util.ArrayList;
import java.util.List;

class Node implements Comparable<Node>{
  private static int currentId = 0;
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
    return "<Node " + id + ":   " +
            " items: " + items +
            " level: " + level +
            " profit: " + profit +
            " weight: " + weight +
            " bound: " + bound +">";
  }

  /**
   * Starting at the current tree depth, compute the bound of this node.
   * @return the bound
   */
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
      if (w + item.weight > KnapSackProblem.capacity)
        break;
      w += item.weight;
      bound += item.profit;
      i++;
    } while (i < KnapSackProblem.numOfItems);


    bound += (KnapSackProblem.capacity - w) * (item.profit/ item.weight);

    return bound;
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
