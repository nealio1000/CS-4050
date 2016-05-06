import java.util.Comparator;

class Item {
  private static int currentId = 0;
  private int id;
  int profit;
  int weight;
  private double profitWeightRatio;

  @Override
  public String toString() {
    return  String.valueOf(id);
  }

  Item(int profit, int weight){
    currentId++; this.id = currentId;
    this.profit = profit;
    this.weight = weight;
    profitWeightRatio = profit / weight;
  }

  private double getProfitWeightRatio() {
    return profitWeightRatio;
  }

  static Comparator<Item> byRatio() {
    return (item1, item2) -> Double.compare(item2.getProfitWeightRatio(), item1.getProfitWeightRatio());
  }
}