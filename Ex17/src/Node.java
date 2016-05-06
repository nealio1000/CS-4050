import java.util.List;

/**
 * Created by neal on 5/5/16.
 */
public class Node {
  private List<Item> items;

  public Node(List<Item> items){
    this.items = items;

  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  @Override
  public String toString() {
    return "Node{" +
            "items=" + items +
            '}';
  }
}
