import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by neal on 5/5/16.
 */
public class Ex17 {
  private static int capacity;
  private static int numOfItems;
  private static int[] profits;
  private static int[] weights;


  public static void main (String[] args){
    readInDataFile();
  }

  private static void readInDataFile(){
    Scanner scanner = null;
    try {
      scanner = new Scanner(new FileReader("ex9"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    capacity = scanner.nextInt();
    numOfItems = scanner.nextInt();
    profits = new int[numOfItems];
    weights = new int[numOfItems];

    for(int i = 0; i < numOfItems; i++){
      profits[i] = scanner.nextInt();
      weights[i] = scanner.nextInt();
    }
  }
}
