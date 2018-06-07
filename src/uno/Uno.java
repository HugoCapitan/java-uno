package uno;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

/**
 * Uno
 */
public class Uno {
  RetrievePlayerSt nextPlayerSt;
  RetrievePlayerSt prevPlayerSt;
  RetrievePlayerSt retrievePlayerSt; // Actual RetrieveState

  private static Uno uno;
  private static Scanner in = new Scanner(System.in);

  private int playersNum;
  private int turnsCounter;
  private LinkedList<Card> deck = new LinkedList<>();
  private LinkedList<Card> stack = new LinkedList<>();
  private List<Player> players = new ArrayList<>();
  private ListIterator playersIterator = null;

  private Uno() {
    // Initialize behavior states.
    this.nextPlayerSt = new NextPlayerSt(this);
    this.prevPlayerSt = new PrevPlayerSt(this);
    this.retrievePlayerSt = nextPlayerSt;

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
  
  public List<Player> getPlayers() { 
    return this.players; 
  }

  public ListIterator getPlayersIterator() {
    return this.playersIterator;
  }

  public int getPlayersNum() {
    return this.playersNum;
  }

  public void initDeck() {
    // First: Colored Cards From 0 to 9
    for (int i = 0; i <= 9; i++) {
      String number = Integer.toString(i);
      this.deck.add( new Card(number, "Yellow") );
      this.deck.add( new Card(number, "Red") );
      this.deck.add( new Card(number, "Blue") );
      this.deck.add( new Card(number, "Green") );
    }

    // Second: Colored Cards From 1 to 9 and Wilds
    for (int i = 1; i <= 9; i++) {
      String number = Integer.toString(i);
      this.deck.add( new Card(number, "Yellow") );
      this.deck.add( new Card(number, "Red") );
      this.deck.add( new Card(number, "Blue") );
      this.deck.add( new Card(number, "Green") );

      if (i <= 4)
        this.deck.add( new Card("Wild", "Wild") );
      else if (i > 4)
        this.deck.add( new Card("+4", "Wild") );
    }

    // Third: Block, Reverse, +2; Two of each color
    for (int i = 0; i < 4; i++) {
      String color = "";
      if (i == 0) color = "Yellow";
      if (i == 1) color = "Red";
      if (i == 2) color = "Blue";
      if (i == 3) color = "Green";

      this.deck.add( new Card("Block", color) );
      this.deck.add( new Card("Flip", color) );
      this.deck.add( new Card("+2", color) );
    }


  }

  public void nextTurn() {
    ++this.turnsCounter;
    this.turn(this.retrievePlayer());
  }

  private void pass() {
    this.nextTurn();
  }

  private void playCard(Player turnPlayer, String selectionChar) {
    Card selectedCard = turnPlayer.getCards().get(selectionChar);
    
    if (selectedCard != null) {
      if (this.turnsCounter == 1 || this.stack.getFirst().isCompatible(selectedCard)) {
        this.stack.addFirst( turnPlayer.pickCard(selectionChar) );

        if (selectedCard.getNumber().equals("Flip")) {
          if (this.retrievePlayerSt instanceof NextPlayerSt) {
            this.retrievePlayerSt = this.prevPlayerSt;
            this.playersIterator.previous();
          } else {
            this.retrievePlayerSt = this.nextPlayerSt;
            this.playersIterator.next();
          }
        }

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

  public Player retrievePlayer() {
    return this.retrievePlayerSt.retrievePlayer();
  }

  public void setPlayersIterator(ListIterator playersIterator) {
    this.playersIterator = playersIterator;
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
  
  public static Uno getNewGame () {
    uno = new Uno();

    return uno;
  }
 
}