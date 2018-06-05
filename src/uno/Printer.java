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
    String tops = "";
    String middles = "";
    String bottoms = "";
    for (Card c : cards) { 
      tops += printSpecialRow("Top", c.getNumberS());
      middles += printSpecialRow("Middle", c.getColor()); 
      bottoms += printSpecialRow("Bottom", c.getNumberS());
    }
      
    
    System.out.println( printCentered( printMultiple("+--------+ ", cards.size()) ) );
    System.out.println( printCentered(tops) );
    System.out.println( printCentered( printMultiple("|        | ", cards.size()) ) );
    System.out.println( printCentered(middles) );
    System.out.println( printCentered( printMultiple("|        | ", cards.size()) ) );
    System.out.println( printCentered(bottoms) );
    System.out.println( printCentered( printMultiple("+--------+ ", cards.size()) ) );
    
    System.out.print("    ");
    
    System.out.println();
  }

  public static String printCentered(String centerText) {
    return printMultiple(" ", (128 - centerText.length()) / 2) + centerText;
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
        result += ("| " + text + "      | ");
        break;
      case "Middle":
        result += ("| " + text + printMultiple(" ", 7 - text.length()) + "| ");
        break;
      case "Bottom":
        result += ("|      " + text + " | ");
        break;
      default:
        break;
    }

    return result;
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
    System.out.print( printMultiple(" ", 7 - card.getColor().length()) );
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