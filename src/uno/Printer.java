package uno;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Printer
 */
public class Printer {
  private static Scanner in = new Scanner(System.in);
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

  public static void printCentered(String centerText) {
    printMultiple(" ", (97 - centerText.length()) / 2);
    System.out.println(centerText);
  }

  public static void printFirstTurn(Player player) {
    System.out.print("\033[H\033[2J");
    System.out.println("It's your turn, " + player.getName() + "!");
    System.out.println();
    System.out.println();
    System.out.println();

    System.out.println("These are your cards:");

    printCardsSet( player.getOrderedCards() );

    System.out.println();
    System.out.println();
    System.out.println("} To play a card type the letter below it");
    System.out.println();
    
    System.out.print("> ");
  }

  public static void printGreeting() {
    System.out.print("\033[H\033[2J");
    System.out.println(
      "                                                                                                 \n" +
      "                                                                                                 \n" +
      "                                                                                                 \n" +
      "                 UUUUUUUU     UUUUUUUUNNNNNNNN        NNNNNNNN     OOOOOOOOO                     \n" +
      "                 U::::::U     U::::::UN:::::::N       N::::::N   OO:::::::::OO                   \n" +
      "                 U::::::U     U::::::UN::::::::N      N::::::N OO:::::::::::::OO                 \n" +
      "                 UU:::::U     U:::::UUN:::::::::N     N::::::NO:::::::OOO:::::::O                \n" +
      "                  U:::::U     U:::::U N::::::::::N    N::::::NO::::::O   O::::::O                \n" +
      "                  U:::::D     D:::::U N:::::::::::N   N::::::NO:::::O     O:::::O                \n" +
      "                  U:::::D     D:::::U N:::::::N::::N  N::::::NO:::::O     O:::::O                \n" +
      "                  U:::::D     D:::::U N::::::N N::::N N::::::NO:::::O     O:::::O                \n" +
      "                  U:::::D     D:::::U N::::::N  N::::N:::::::NO:::::O     O:::::O                \n" +
      "                  U:::::D     D:::::U N::::::N   N:::::::::::NO:::::O     O:::::O                \n" +
      "                  U:::::D     D:::::U N::::::N    N::::::::::NO:::::O     O:::::O                \n" +
      "                  U::::::U   U::::::U N::::::N     N:::::::::NO::::::O   O::::::O                \n" +
      "                  U:::::::UUU:::::::U N::::::N      N::::::::NO:::::::OOO:::::::O                \n" +
      "                    UU:::::::::::::UU  N::::::N       N:::::::N OO:::::::::::::OO                \n" +
      "                      UU:::::::::UU    N::::::N        N::::::N   OO:::::::::OO                  \n" +
      "                        UUUUUUUUU      NNNNNNNN         NNNNNNN     OOOOOOOOO                    \n" +
      "                                                                                                 \n" +
      "                                                                                                 \n" +
      "                                                                                                 \n" +
      "                                                                                                 \n" +
      "                                                                                                 \n" +
      "                                                                                                 "
    ); 


    System.out.println("Type how many will play!");
  }

  public static void printMultiple(String toPrint, int n) {
    if (n > 0) {
      System.out.print(toPrint);
      printMultiple(toPrint, n - 1);
    }
  }

  public static String printNamePetition(int playerNumber) {
    System.out.print("\033[H\033[2J");

    System.out.println("What's your name, player " + (playerNumber + 1) + "?");
    System.out.print("> ");

    return in.next();
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
    
  public static void printTurn(Player player, Card card) {
    System.out.print("\033[H\033[2J");
    System.out.println("It's your turn, " + player.getName() + "!");
    System.out.println();
    System.out.println();

    System.out.println("Last Played Card:");
    System.out.println("+--------+");
    System.out.println("| " + card.getNumberS() + "      | ");
    System.out.println("|        |");
    System.out.print(  "| " + card.getColor());
    printMultiple(" ", 7 - card.getColor().length());
    System.out.println("|");
    System.out.println("|        |");
    System.out.println("|      " + card.getNumberS() + " |");
    System.out.println("+--------+");

    System.out.println();
    System.out.println("These are your cards:");

    printCardsSet( player.getOrderedCards() );

    System.out.println();
    System.out.println();
    System.out.println("} To play a card type the letter below it");
    System.out.println("} To eat type 'eat'");
    System.out.println("} To skip type 'pass'");
    System.out.println();
    
    System.out.print("> ");
  }
}