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
  private List<Card> stack     = new ArrayList<>();

  private Game(int nPlayers) {
    Scanner in = new Scanner(System.in);

    for (int i = 0; i <= 9; i++) {
      this.stack.add( new Card(i, "Yellow") );
      this.stack.add( new Card(i, "Red") );
      this.stack.add( new Card(i, "Blue") );
      this.stack.add( new Card(i, "Green") );
    }

    this.shuffleStack();

    Printer.printGreeting();

    for (int i = 0; i < nPlayers; i++) { this.addPlayer(); }

  }

  public void addPlayer() {
    Player newPlayer;
    int playerNumber = this.players.size() + 1;
    List<Card> playerCards = new ArrayList<Card>(this.stack.subList(0, 7));

    System.out.println("Enter name for player #" + playerNumber);
    newPlayer = new Player(in.next(), playerCards);
    players.add(playerNumber, newPlayer);

    this.stack.subList(0, 7).clear();
  }

  public List<Player> getPlayers() { return this.players; }

  public void shuffleStack() {
    int n = this.stack.size();
    Random random = new Random();

    random.nextInt();

    for (int i = 0; i < n; i++) {
      int change = i + random.nextInt(n - i);
      swap(this.stack, i, change);
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