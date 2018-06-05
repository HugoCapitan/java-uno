package uno;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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

  private int playersNum;
  private int turnsCounter;
  private LinkedList<Card> deck = new LinkedList<>();
  private LinkedList<Card> stack = new LinkedList<>();
  private List<Player> players = new ArrayList<>();
  private ListIterator playersIterator = null;

  private Game() {
    // Creating and shuffling deck
    this.initDeck(); 
    this.suffleDeck();

    // Create n players and start first turn
    Printer.printGreeting();
    this.playersNum = in.nextInt();

    for (int i = 0; i < this.playersNum; i++) { 
      this.addPlayer(); 
    }

    this.nextTurn();
  }

  public void addPlayer() {
    Player newPlayer;
    int playerNumber = this.players.size();
    String playerName;
    List<Card> playerCards;
    
    playerName = Printer.printNamePetition(playerNumber);
    playerCards = new ArrayList<Card>(this.deck.subList(0, 7));

    newPlayer = new Player(playerName, playerCards);
    this.deck.subList(0, 7).clear(); // Remove player cards from deck.    
    
    this.players.add(playerNumber, newPlayer);
  }

  private void eatCard(Player turnPlayer) {
    turnPlayer.addCard(this.deck.removeLast());
    this.turn(turnPlayer);
  }
  
  private Player getNextPlayer() {
    // Initi iterator if doesn't exists yet or reset it when position is last.
    if (this.playersIterator == null || !this.playersIterator.hasNext()) 
      this.playersIterator = this.players.listIterator();

    return (Player) this.playersIterator.next();
  }

  public List<Player> getPlayers() { 
    return this.players; 
  }

  public void initDeck() {
    for (int i = 0; i <= 9; i++) {
      this.deck.add( new Card(i, "Yellow") );
      this.deck.add( new Card(i, "Red") );
      this.deck.add( new Card(i, "Blue") );
      this.deck.add( new Card(i, "Green") );
    }
  }

  public void nextTurn() {
    ++this.turnsCounter;
    this.turn(this.getNextPlayer());
  }

  private void pass() {
    this.nextTurn();
  }

  private void playCard(Player turnPlayer, String selectionChar) {
    Card selectedCard = turnPlayer.getCards().get(selectionChar);
    
    if (selectedCard != null) {
      if (this.turnsCounter == 1 || this.stack.getFirst().isCompatible(selectedCard)) {
        this.stack.addFirst( turnPlayer.pickCard(selectionChar) );
        this.nextTurn();
      } else {
        // TODO: Display "this card can't be played" message
        this.turn(turnPlayer);
      }
    } else {
      // TODO: Display "this card doesn't exists" message
      this.turn(turnPlayer);
    }
  }

  public void refillDeck() {
    Card firstCard = this.stack.removeFirst();
    this.deck.addAll(this.stack);
    this.suffleDeck();

    this.stack = new LinkedList<>();
    this.stack.addFirst(firstCard);
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
  
  private void turn(Player turnPlayer) {
    String selectionChar;

    if (this.deck.size() == 0) 
      this.refillDeck();

    if (this.turnsCounter == 1)
      Printer.printFirstTurn(turnPlayer);
    else   
      Printer.printTurn(turnPlayer, this.stack.getFirst());

    // Reading user input
    selectionChar = in.next();

    if (selectionChar.equals("pass") && this.turnsCounter > 1) 
      this.pass();
    else if (selectionChar.equals("eat"))
      this.eatCard(turnPlayer);
    else if (selectionChar.length() == 1)
      this.playCard(turnPlayer, selectionChar);
    else 
      this.turn(turnPlayer);
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