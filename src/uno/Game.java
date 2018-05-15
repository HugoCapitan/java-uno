package uno;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Game
 */
public class Game {
  private static Game uniqueInstance;

  private List<Player> players = new ArrayList<>();
  private List<Card> cards     = new ArrayList<>();

  private Game(int nPlayers) {
    Scanner in = new Scanner(System.in);

    for (int i = 0; i <= 9; i++) {
      this.cards.add( new Card(i, "Yellow") );
      this.cards.add( new Card(i, "Red") );
      this.cards.add( new Card(i, "Blue") );
      this.cards.add( new Card(i, "Green") );
    }

    this.shuffleStack();

    for (int i = 0; i < nPlayers; i++) {
      System.out.println("Enter name for player #" + (i + 1));
      String playerName = in.next();
      List<Card> playerCards = new ArrayList<Card>(this.cards.subList(0, 7));
      this.cards.subList(0, 7).clear();
      Player newPlayer = new Player(playerName, playerCards);
      players.add(i, newPlayer);
    } 


    System.out.println("========== Game Ready! ==========");
    System.out.println("||||| These are your cards: |||||");
    for (Player p : this.players) {
      System.out.println("Player " + p.getName() + ":");
      p.printCards();
    }
  }

  public List<Player> getPlayers() { return this.players; }

  public void shuffleStack() {
    int n = this.cards.size();
    Random random = new Random();

    random.nextInt();

    for (int i = 0; i < n; i++) {
      int change = i + random.nextInt(n - i);
      swap(this.cards, i, change);
    }
  }

  private static void swap(List<Card> cards, int i, int change) {
    Card helper = cards.get(i);
    cards.set(i, cards.get(change));
    cards.set(change, helper);
  }
  
  public static Game getNewGame (int nPlayers) {
    uniqueInstance = new Game(nPlayers);

    return uniqueInstance;
  }

}