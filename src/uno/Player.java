package uno;

import java.util.ArrayList;

/**
 * Player
 */
public class Player {

  private String name;
  private ArrayList<Card> cards;

  public Player(String name) {
  //public Player(String name, ArrayList<Card> cards) {
    this.name = name;
    // this.cards = cards;
  }

  public void printCards() {
    printMultiple("+-------+ ", this.cards.size());
  }

  public void printName() {
    System.out.println(this.name);
  }

  private static void printMultiple(String toPrint, int n) {
    if (n > 0) {
      System.out.print(toPrint);
      printMultiple(toPrint, n - 1);
    }
  }

}
