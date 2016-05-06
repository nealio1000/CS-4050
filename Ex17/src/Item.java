import java.util.Comparator;

/**
 * Created by neal on 5/5/16.
 */
public class Item {
  public static int profit;
  public static int weight;
  public static double profitWeightRatio;

  @Override
  public String toString() {
    return "Item{" +
            "profit=" + profit +
            ", weight=" + weight +
            ", profitWeightRatio=" + profitWeightRatio +
            '}';
  }

  public Item(int profit, int weight){
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
