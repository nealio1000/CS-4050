import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by neal on 5/5/16.
 */
public class KnapSackProblem {

  public KnapSackProblem(){}
  private int capacity;
  private int numOfItems;
  private List<Item> itemList;

  public void start(){
    readInDataFile();
  }

  private void readInDataFile(){
    Scanner scanner = null;
    try {
      scanner = new Scanner(new FileReader("ex9"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    this.itemList = new ArrayList<>();
    this.capacity = scanner.nextInt();
    this.numOfItems = scanner.nextInt();

    for(int i = 0; i < numOfItems; i++)
      itemList.add(new Item(scanner.nextInt(),scanner.nextInt(), capacity));
  }
}
