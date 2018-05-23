package uno;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

/**
 * Game
 */
public class Game {
  private static Game uniqueInstance;
  private static Scanner in = new Scanner(System.in);

  private int turnsCounter = 0;
  private ListIterator playersIterator;
  private List<Player> players = new ArrayList<>();
  private List<Card> deck      = new ArrayList<>();
  private List<Card> stack     = new ArrayList<>();

  private Game() {
    int nPlayers;
    // Creating and shuffling deck
    for (int i = 0; i <= 9; i++) {
      this.deck.add( new Card(i, "Yellow") );
      this.deck.add( new Card(i, "Red") );
      this.deck.add( new Card(i, "Blue") );
      this.deck.add( new Card(i, "Green") );
    }
    this.suffleDeck();

    // Get players and start first turn
    Printer.printGreeting();
    nPlayers = in.nextInt();

    for (int i = 0; i < nPlayers; i++) { this.addPlayer(); }
    this.playersIterator = players.listIterator();
  
    this.nextTurn();
  }

  public void addPlayer() {
    Player newPlayer;
    int playerNumber = this.players.size();
    List<Card> playerCards = new ArrayList<Card>(this.deck.subList(0, 7));

    System.out.println("Enter name for player #" + playerNumber);
    newPlayer = new Player(in.next(), playerCards);
    players.add(playerNumber, newPlayer);

    this.deck.subList(0, 7).clear();
  }

  public List<Player> getPlayers() { return this.players; }

  private Player getNextPlayer() { 
    if (!this.playersIterator.hasNext()) 
      this.playersIterator = this.players.listIterator();

    return (Player) this.playersIterator.next();
  }

  public void nextTurn() {
    ++this.turnsCounter;
    boolean selectedValid = true;
    Card selectedCard;
    Player turnPlayer = this.getNextPlayer();

    Printer.printTurn(turnPlayer);
  }

  public void suffleDeck() {
    int n = this.deck.size();
    Random random = new Random();

    random.nextInt();

    for (int i = 0; i < n; i++) {
      int change = i + random.nextInt(n - i);
      swap(this.deck, i, change);
    }
  }




  private static void swap(List<Card> deck, int i, int change) {
    Card helper = deck.get(i);
    deck.set(i, deck.get(change));
    deck.set(change, helper);
  }
  
  public static Game getNewGame () {
    uniqueInstance = new Game();

    return uniqueInstance;
  }

}