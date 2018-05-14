package uno;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Game
 */
public class Game {
  private static Game uniqueInstance;

  private ArrayList<Player> players = new ArrayList<>();
  private ArrayList<Card> cards     = new ArrayList<>();

  private Game(int nPlayers) {
    for (int i = 0; i <= 9; i++) {
      this.cards.add( new Card(i, "Yellow") );
      this.cards.add( new Card(i, "Red") );
      this.cards.add( new Card(i, "Blue") );
      this.cards.add( new Card(i, "Green") );
    }

    Scanner in = new Scanner(System.in);

    for (int i = 0; i < nPlayers; i++) {
      System.out.println("Enter name for player #" + (i + 1));
      String name = in.next();
      players.add(i, new Player(name));
    } 
  }

  public static Game getNewGame (int nPlayers) {
    uniqueInstance = new Game(nPlayers);

    return uniqueInstance;
  }

  public void printCardsSet(ArrayList<Card> set) {
  } 
  
}