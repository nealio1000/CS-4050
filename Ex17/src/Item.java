/**
 * CS 4050
 * Exercise 17
 *
 * 0-1 Knapsack Problem
 * Branch & Bound Solution
 *
 * Instructions:
 *
 * javac *.java
 * java Ex17 <optional_data_file_argument>
 *
 * By Neal Friedman and Brunno Putnam
 */

import java.util.Comparator;

class Item {
  static int currentId = 0;
  int profit;
  int weight;
  int id;
  private double profitWeightRatio;

  /**
   * Construct an Item
   *
   * @param profit the item's profit
   * @param weight the item's weight
   */
  Item(int profit, int weight){
    currentId++; this.id = currentId;
    this.profit = profit;
    this.weight = weight;
    profitWeightRatio = profit / weight;
  }

  /**
   * Comparator method for sorting items by profit/weight ratio
   * @return the item with the larger profit/weight ratio
   */
//  static Comparator<Item> byRatio() {
//    return (item1, item2) -> Double.compare(item2.getProfitWeightRatio(), item1.getProfitWeightRatio());
//  }
  static Comparator<Item> byRatio() {
    return new Comparator<Item>() {
      public int compare(Item item1, Item item2) {
        return Double.compare(item2.getProfitWeightRatio(), item1.getProfitWeightRatio());
      }
    };
  }

  @Override
  public String toString() {
    return String.valueOf(id);
  }

  /**
   * Return the ratio of profit to weight of this item
   *
   * @return the profit/weight ratio
   */
  private double getProfitWeightRatio() {
    return profitWeightRatio;
  }
}