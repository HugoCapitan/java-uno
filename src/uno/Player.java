package uno;

import java.util.ArrayList;
import java.util.List;

/**
 * Player
 */
public class Player {

  private String name;
  private List<Card> cards;

  // public Player(String name) {
  public Player(String name, List<Card> cards) {
    this.name = name;
    this.cards = cards;
  }

  public void printCards() {
    printMultiple("+--------+ ", this.cards.size());
    System.out.println("");
    for (Card c : this.cards) { printSpecialRow("Top", c.getNumber()); }
    System.out.println("");
    printMultiple("|        | ", this.cards.size());
    System.out.println("");
    for (Card c : this.cards) { printSpecialRow("Middle", c.getColor()); }
    System.out.println("");
    printMultiple("|        | ", this.cards.size());
    System.out.println("");
    for (Card c : this.cards) { printSpecialRow("Bottom", c.getNumber()); }
    System.out.println("");
    printMultiple("+--------+ ", this.cards.size());
    System.out.println("");
  }

  public String getName() { return this.name; }

  private static void printMultiple(String toPrint, int n) {
    if (n > 0) {
      System.out.print(toPrint);
      printMultiple(toPrint, n - 1);
    }
  }

  private static void printSpecialRow(String kind, String text) {
    switch (kind) {
      case "Top":
        System.out.print("| " + text + "      | ");
        break;
      case "Middle":
        System.out.print("| " + text);
        printMultiple(" ", 7 - text.length());
        System.out.print("| ");
        break;
      case "Bottom":
        System.out.print("|      " + text + " | ");
        break;
      default:
        break;
    }
  }

}
