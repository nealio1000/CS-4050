import java.util.Comparator;

/**
 * Created by neal on 5/5/16.
 */
public class NodeComparator implements Comparator<Node> {

  @Override
  public int compare(Node o1, Node o2) {
    if(o1.id < o2.id)
      return -1;
    if(o1.id > o2.id)
      return 1;
    return 0;
  }
}
