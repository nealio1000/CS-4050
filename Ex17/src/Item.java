import java.util.Comparator;

/**
 * Created by neal on 5/5/16.
 */
public class Item {
  public int profit;
  public int weight;
  public double profitWeightRatio;

  @Override
  public String toString() {
    return "Item{" +
            "profit=" + profit +
            ", weight=" + weight +
            ", profitWeightRatio=" + profitWeightRatio +
            '}';
  }

  public Item(int profit, int weight, int capacity){
    this.profit = profit;
    this.weight = weight;
    profitWeightRatio = profit / weight;
  }

  public int getProfit() {
    return profit;
  }

  public void setProfit(int profit) {
    this.profit = profit;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
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
