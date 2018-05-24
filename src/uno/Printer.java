package uno;

import java.util.List;
import java.util.ArrayList;

/**
 * Printer
 */
public class Printer {
  private static String qwerty = "qwertyuiopasdfghjklzxcvbnm";

  public static void printCardsSet(List<Card> cards) {
    printMultiple("+--------+ ", cards.size());
    System.out.println("");
    for (Card c : cards) { printSpecialRow("Top", c.getNumberS()); }
    System.out.println("");
    printMultiple("|        | ", cards.size());
    System.out.println("");
    for (Card c : cards) { printSpecialRow("Middle", c.getColor()); }
    System.out.println("");
    printMultiple("|        | ", cards.size());
    System.out.println("");
    for (Card c : cards) { printSpecialRow("Bottom", c.getNumberS()); }
    System.out.println("");
    printMultiple("+--------+ ", cards.size());
    System.out.println();
    System.out.print("    ");
    for (int i = 0; i < cards.size(); i++) {
      System.out.print("(" + qwerty.substring(i, i + 1) + ")        ");
    }
    System.out.println();
  }

  public static void printGreeting() {
    System.out.println("ooooooooooooooooooooooooooooooooooooooooooo");
    System.out.println("-------------------------------------------");
    System.out.println("||||||||||||||||||| UNO |||||||||||||||||||");
    System.out.println("-------------------------------------------");
    System.out.println("ooooooooooooooooooooooooooooooooooooooooooo");
    System.out.println("Type how many will play!");
  }
  
  public static void printTurn(Player player) {
    System.out.println("It's your turn, " + player.getName() + "!");
    System.out.println();
    System.out.println("Select one of your cards:");

    printCardsSet( player.getOrderedCards() );
    
    System.out.print("Card >");
  }

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