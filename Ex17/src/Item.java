/**
 * Created by neal on 5/5/16.
 */
public class Item {
  private int profit;
  private int weight;
  private int capacity;
  private int profitWeightRatio;

  public Item(int profit, int weight, int capacity){
    this.profit = profit;
    this.weight = weight;
    this.capacity = capacity;
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

  public int getProfitWeightRatio() {
    return profitWeightRatio;
  }

  @Override
  public String toString() {
    return "Item{" +
            "profit=" + profit +
            ", weight=" + weight +
            '}';
  }
}
