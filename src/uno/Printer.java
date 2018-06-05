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

  public static void printCard(Card card) {
    System.out.println( printCentered("+--------+ ") );
    System.out.println( printCentered( printSpecialRow("Top", card.getNumberS()) + " " ) );
    System.out.println( printCentered("|        | ") );
    System.out.println( printCentered( printSpecialRow("Middle", card.getColor()) + " " ) );
    System.out.println( printCentered("|        | ") );
    System.out.println( printCentered( printSpecialRow("Bottom", card.getNumberS()) + " ") );
    System.out.println( printCentered("+--------+ ") );
  }

  public static void printCardsSet(List<Card> cards) {
    String tops = "";
    String middles = "";
    String bottoms = "";
    String letters = "";
    for (Card c : cards) { 
      tops += (printSpecialRow("Top", c.getNumberS()) + " ");
      middles += (printSpecialRow("Middle", c.getColor()) + " "); 
      bottoms += (printSpecialRow("Bottom", c.getNumberS()) + " ");
    }
    for (int i = 0; i < cards.size(); i++) {
      letters += ("    (" + qwerty.substring(i, i + 1) + ")    ");
    }
    
    System.out.println( printCentered( printMultiple("+--------+ ", cards.size()) ) );
    System.out.println( printCentered(tops) );
    System.out.println( printCentered( printMultiple("|        | ", cards.size()) ) );
    System.out.println( printCentered(middles) );
    System.out.println( printCentered( printMultiple("|        | ", cards.size()) ) );
    System.out.println( printCentered(bottoms) );
    System.out.println( printCentered( printMultiple("+--------+ ", cards.size()) ) );
    System.out.println( printCentered(letters) );    
    
    System.out.println();
  }

  public static String printCentered(String centerText) {
    return printMultiple(" ", (128 - centerText.length()) / 2) + centerText;
  }

  public static void printFirstTurn(Player player) {
    System.out.print("\033[H\033[2J");
    System.out.println();
    System.out.println( printCentered("Lets get this going, " + player.getName() + "!") );

    for (int i = 0; i < 5; i++) { System.out.println(); }

    System.out.println( printCentered("These are your cards:") );

    printCardsSet( player.getOrderedCards() );

    for (int i = 0; i < 3; i++) { System.out.println(); }

    System.out.println( printCentered("} To play a card type the letter below it {") );

    for (int i = 0; i < 2; i++) { System.out.println(); }
    
    System.out.print("> ");
  }

  public static void printGreeting() {
    System.out.print("\033[H\033[2J");
    
    System.out.println( printCentered("UUUUUUUU     UUUUUUUUNNNNNNNN        NNNNNNNN     OOOOOOOOO     ") );
    System.out.println( printCentered("U::::::U     U::::::UN:::::::N       N::::::N   OO:::::::::OO   ") );
    System.out.println( printCentered("U::::::U     U::::::UN::::::::N      N::::::N OO:::::::::::::OO ") );
    System.out.println( printCentered("UU:::::U     U:::::UUN:::::::::N     N::::::NO:::::::OOO:::::::O") );
    System.out.println( printCentered(" U:::::U     U:::::U N::::::::::N    N::::::NO::::::O   O::::::O") );
    System.out.println( printCentered(" U:::::D     D:::::U N:::::::::::N   N::::::NO:::::O     O:::::O") );
    System.out.println( printCentered(" U:::::D     D:::::U N:::::::N::::N  N::::::NO:::::O     O:::::O") );
    System.out.println( printCentered(" U:::::D     D:::::U N::::::N N::::N N::::::NO:::::O     O:::::O") );
    System.out.println( printCentered(" U:::::D     D:::::U N::::::N  N::::N:::::::NO:::::O     O:::::O") );
    System.out.println( printCentered(" U:::::D     D:::::U N::::::N   N:::::::::::NO:::::O     O:::::O") );
    System.out.println( printCentered(" U:::::D     D:::::U N::::::N    N::::::::::NO:::::O     O:::::O") );
    System.out.println( printCentered(" U::::::U   U::::::U N::::::N     N:::::::::NO::::::O   O::::::O") );
    System.out.println( printCentered(" U:::::::UUU:::::::U N::::::N      N::::::::NO:::::::OOO:::::::O") );
    System.out.println( printCentered("  UU:::::::::::::UU  N::::::N       N:::::::N OO:::::::::::::OO ") );
    System.out.println( printCentered("    UU:::::::::UU    N::::::N        N::::::N   OO:::::::::OO   ") );
    System.out.println( printCentered("      UUUUUUUUU      NNNNNNNN         NNNNNNN     OOOOOOOOO     ") );

    System.out.println(
      "--------------------------------------------------------------------------------------------------------------------------------"
    );

    System.out.println("Type how many will play!");
  }

  public static String printMultiple(String toPrint, int n) {
    String result = "";
    for (int i = 0; i < n; i++) {
      result += toPrint;
    }

    return result;
  }

  public static String printNamePetition(int playerNumber) {
    System.out.print("\033[H\033[2J");

    System.out.println("What's your name, player " + (playerNumber + 1) + "?");
    System.out.print("> ");

    return in.next();
  }

  private static String printSpecialRow(String kind, String text) {
    String result = "";

    switch (kind) {
      case "Top":
        result += ("| " + text + "      |");
        break;
      case "Middle":
        result += ("| " + text + printMultiple(" ", 7 - text.length()) + "|");
        break;
      case "Bottom":
        result += ("|      " + text + " |");
        break;
      default:
        break;
    }

    return result;
  }
    
  public static void printTurn(Player player, Card card) {
    System.out.print("\033[H\033[2J");
    
    System.out.println();
    System.out.println( printCentered("-_-_-_ It's your turn, " + player.getName() + "! _-_-_-") ); 
    System.out.println( printCentered("      -________________" + printMultiple("_", player.getName().length()) + "_-      ") ); 
    for (int i = 0; i < 2; i++) { System.out.println(); }

    System.out.println( printCentered("     Last played card     ") );
    System.out.println( printCentered("~~+--------------------+~~") );
    System.out.println();
    printCard(card);

    for (int i = 0; i < 2; i++) { System.out.println(); }
    System.out.println( printCentered("     Your cards     ") );
    System.out.println( printCentered("~~+--------------+~~") );
    System.out.println();

    printCardsSet( player.getOrderedCards() );

    
    for (int i = 0; i < 2; i++) { System.out.println(); }
    System.out.println( printCentered("+~: HELP :~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~+") );
    System.out.println( printCentered("|                                                |") );
    System.out.println( printCentered("|  - To eat type 'eat'                           |") );
    System.out.println( printCentered("|  - To skip type 'pass'                         |") );
    System.out.println( printCentered("|  - To play a card type the letter below it     |") );
    System.out.println( printCentered("|                                                |") );
    System.out.println( printCentered("+~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~+") );
    for (int i = 0; i < 1; i++) { System.out.println(); }
    
    System.out.print( printCentered("> ") );
  }
}