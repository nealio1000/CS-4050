import java.util.Comparator;

/**
 * Created by neal on 5/5/16.
 */
public class Item {
  private static int currentId = 0;
  public int id;
  public int profit;
  public int weight;
  public double profitWeightRatio;

  @Override
  public String toString() {
    return  String.valueOf(id);
  }

  public Item(int profit, int weight){
    currentId++; this.id = currentId;
    this.profit = profit;
    this.weight = weight;
    profitWeightRatio = profit / weight;
  }

  public double getProfitWeightRatio() {
    return profitWeightRatio;
  }

  public static Comparator<Item> byRatio() {
    return new Comparator<Item>() {
      public int compare(Item item1, Item item2) {
        return Double.compare(item2.getProfitWeightRatio(), item1.getProfitWeightRatio());
      }
    };
  }
}
