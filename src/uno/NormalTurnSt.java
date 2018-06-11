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
    LinkedList<Card> stack = uno.getStack();
    
    if (selectedCard != null && (turnsCounter == 1 || stack.getFirst().isCompatible(selectedCard))) {
      stack.addFirst( turnPlayer.pickCard(selectionChar) );

      uno.reactToCard(selectedCard);

      uno.nextTurn();
    } else {
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