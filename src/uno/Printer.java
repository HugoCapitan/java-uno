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
  private static String upperQwerty = "QWERTYUIOPASDFGHJKLZXCVBNM";

  public static String askForWildColor() {
    String color; 

    for (int i = 0; i < 10; i++) { System.out.println(); }
    System.out.println(printCentered("What color?!"));
    System.out.print(printCentered("> "));

    color = in.next();

    return upperCaseFirst(color);
  }

  public static void printCard(Card card) {
    System.out.println( printCentered("+--------+ ") );
    System.out.println( printCentered( printSpecialRow("Top", card.getNumber()) + " " ) );
    System.out.println( printCentered("|        | ") );
    System.out.println( printCentered( printSpecialRow("Middle", card.getColor()) + " " ) );
    System.out.println( printCentered("|        | ") );
    System.out.println( printCentered( printSpecialRow("Bottom", card.getNumber()) + " ") );
    System.out.println( printCentered("+--------+ ") );
  }

  public static void printWildCard(Card card, String color) {
    System.out.println( printCentered("+--------+ ") );
    System.out.println( printCentered( printSpecialRow("Top", card.getNumber()) + " " ) );
    System.out.println( printCentered("|        | ") );
    System.out.println( printCentered( printSpecialRow("Middle", color) + " " ) );
    System.out.println( printCentered("|        | ") );
    System.out.println( printCentered( printSpecialRow("Bottom", card.getNumber()) + " ") );
    System.out.println( printCentered("+--------+ ") );
  }

  public static void printCardsSet(List<Card> cards) {
    String tops = "";
    String middles = "";
    String bottoms = "";
    String letters = "";
    for (Card c : cards) { 
      tops += (printSpecialRow("Top", c.getNumber()) + " ");
      middles += (printSpecialRow("Middle", c.getColor()) + " "); 
      bottoms += (printSpecialRow("Bottom", c.getNumber()) + " ");
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
    return printMultiple(" ", (190 - centerText.length()) / 2) + centerText;
  }

  public static void printEnd(Player winner) {
    System.out.print("\033[H\033[2J");
    System.out.println();

    System.out.println( printCentered("You Won, " + winner.getName() + "!!") );

    try {
      System.in.read();
    } catch (Exception e) {
    
    }
  }

  public static void printFirstTurn(Player player) {
    System.out.print("\033[H\033[2J");
    System.out.println();

    System.out.println( printCentered("-_-_-_ Lets get this going, " + player.getName() + "! _-_-_-") );
    System.out.println( printCentered("      -_____________________" + printMultiple("_", player.getName().length()) + "_-      ") ); 

    for (int i = 0; i < 5; i++) { System.out.println(); }

    System.out.println( printCentered("     Your cards     ") );
    System.out.println( printCentered("~~+--------------+~~") );
    System.out.println();
    printCardsSet( player.getOrderedCards() );

    for (int i = 0; i < 7; i++) { System.out.println(); }
    System.out.println( printCentered("+~: HELP :~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~+") );
    System.out.println( printCentered("|                                                |") );
    System.out.println( printCentered("|  - To play a card type the letter below it     |") );
    System.out.println( printCentered("|                                                |") );
    System.out.println( printCentered("+~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~+") );
    for (int i = 0; i < 4; i++) { System.out.println(); }
    
    System.out.print( printCentered("> ") );
  }

  public static void printGreeting() {
    System.out.print("\033[H\033[2J");
    
    System.out.println();
    System.out.println(
      "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
    );
    for (int i = 0; i < 10; i++) { System.out.println(); }
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
    for (int i = 0; i < 10; i++) { System.out.println(); }
    System.out.println(
      "Adjust the screen height and width till the two lines of dashes are visible and they dont break (Press enter when done)"
    );
    System.out.print(
      "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
    );
    
    try {
      System.in.read();
    } catch (Exception e) {
    
    }

    System.out.print("\033[H\033[2J");
    System.out.println(
      "+------------------------------------------------------------------------------------------------------------------------------+"
    );
    System.out.println("How many will play?");
    System.out.print("> ");
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

    System.out.println(
      "+------------------------------------------------------------------------------------------------------------------------------+"
    );
    System.out.println("What's your name, player " + (playerNumber + 1) + "?");
    System.out.print("> ");

    return in.nextLine();
  }

  private static String printSpecialRow(String kind, String text) {
    String result = "";

    switch (kind) {
      case "Top":
        result += ("| " + text + printMultiple(" ", 7 - text.length()) + "|");
        break;
      case "Middle":
        result += ("| " + text + printMultiple(" ", 7 - text.length()) + "|");
        break;
      case "Bottom":
        result += ("|" + printMultiple(" ", 7 - text.length()) + text + " |");
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

  public static void printPostWildTurn(Player player, Card card, String wildColor) {
    System.out.print("\033[H\033[2J");
    
    System.out.println();
    System.out.println( printCentered("-_-_-_ It's your turn, " + player.getName() + "! _-_-_-") ); 
    System.out.println( printCentered("      -________________" + printMultiple("_", player.getName().length()) + "_-      ") ); 
    for (int i = 0; i < 2; i++) { System.out.println(); }

    System.out.println( printCentered("     Last played card     ") );
    System.out.println( printCentered("~~+--------------------+~~") );
    System.out.println();
    printWildCard(card, wildColor);

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

  public static void printPostDrawTurn(Player player, Card card, int howManyToEat, String wildColor) {
    System.out.print("\033[H\033[2J");
    
    System.out.println();
    System.out.println( printCentered("-_-_-_ It's your turn, " + player.getName() + "! _-_-_-") ); 
    System.out.println( printCentered("      -________________" + printMultiple("_", player.getName().length()) + "_-      ") ); 
    for (int i = 0; i < 2; i++) { System.out.println(); }

    System.out.println( printCentered("     Last played card     ") );
    System.out.println( printCentered("~~+--------------------+~~") );
    System.out.println();
    printWildCard(card, wildColor);

    for (int i = 0; i < 2; i++) { System.out.println(); }
    System.out.println( printCentered("     Your cards     ") );
    System.out.println( printCentered("~~+--------------+~~") );
    System.out.println();

    printCardsSet( player.getOrderedCards() );

    
    for (int i = 0; i < 2; i++) { System.out.println(); }
    System.out.println( printCentered("+~: HELP :~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~+") );
    System.out.println( printCentered("|                                                |") );
    System.out.println( printCentered("|  You can either play another " + card.getNumber() + " card or        |") );
    System.out.println( printCentered("|  type 'eat' to eat " + howManyToEat + " cards                     |") );
    System.out.println( printCentered("|                                                |") );
    System.out.println( printCentered("+~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~+") );
    for (int i = 0; i < 1; i++) { System.out.println(); }
    
    System.out.print( printCentered("> ") );
  }

  public static String upperCaseFirst(String value) {

    // Convert String to char array.
    char[] array = value.toCharArray();
    // Modify first element in array.
    array[0] = Character.toUpperCase(array[0]);
    // Return string.
    return new String(array);
}
}
