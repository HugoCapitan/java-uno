package uno;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * NormalTurnSt
 */
public class NormalTurnSt implements TurnSt {
  Uno uno;

  public NormalTurnSt(Uno uno) {
    this.uno = uno;
  }

  @Override
  public void playCard(Player turnPlayer, String selectionChar) {
    int turnsCounter = uno.getTurnsCounter();
    Card selectedCard = turnPlayer.getCards().get(selectionChar);
    LinkedList<Card> deck = uno.getDeck();
    LinkedList<Card> stack = uno.getStack();
    ListIterator playersIterator = uno.getPlayersIterator();
    
    if (selectedCard != null) {
      if (turnsCounter == 1 || stack.getFirst().isCompatible(selectedCard)) {
        stack.addFirst( turnPlayer.pickCard(selectionChar) );

        if (selectedCard.getNumber().equals("Flip")) {
          if (uno.retrievePlayerSt instanceof NextPlayerSt) {
            uno.retrievePlayerSt = uno.prevPlayerSt;
            playersIterator.previous();
          } else {
            uno.retrievePlayerSt = uno.nextPlayerSt;
            playersIterator.next();
          }
        }

        if (selectedCard.getNumber().equals("Block")) // Pass the next player
          uno.retrievePlayer();

        uno.nextTurn();
      } else {
        // TODO: Display "this card can't be played" message
        uno.turn(turnPlayer);
      }
    } else {
      // TODO: Display "this card doesn't exists" message
      uno.turn(turnPlayer);
    }
    
  }

  @Override
  public void turn(Player turnPlayer) {
    String selectionChar;
    int turnsCounter = uno.getTurnsCounter();
    LinkedList<Card> deck = uno.getDeck();
    LinkedList<Card> stack = uno.getStack();

    if (deck.size() == 0) 
      uno.refillDeck();

    if (turnsCounter == 1)
      Printer.printFirstTurn(turnPlayer);
    else   
      Printer.printTurn(turnPlayer, stack.getFirst());

    // Reading user input
    selectionChar = in.next();

    if (selectionChar.equals("pass") && turnsCounter > 1) 
      uno.pass();
    else if (selectionChar.equals("eat"))
      uno.eatCard(turnPlayer);
    else if (selectionChar.length() == 1)
      uno.playCard(turnPlayer, selectionChar);
    else 
      uno.turn(turnPlayer);
  }
  
}