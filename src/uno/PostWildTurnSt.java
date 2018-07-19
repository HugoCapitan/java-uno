package uno;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * PostWildTurnSt
 */
public class PostWildTurnSt implements TurnSt {
  Uno uno;

  public PostWildTurnSt (Uno uno) {
    this.uno = uno;
  }

  @Override
  public void playCard(Player turnPlayer, String selectionChar) {
    String wildColor = uno.getWildColor();
    Card selectedCard = turnPlayer.getCards().get(selectionChar);
    LinkedList<Card> stack = uno.getStack();
    
    if (selectedCard != null && selectedCard.getColor().equals(wildColor) || selectedCard.getColor().equals("Wild")) {
      stack.addFirst( turnPlayer.pickCard(selectionChar) );
      uno.turnSt = uno.normalTurnSt;

      uno.reactToCard(selectedCard);

      uno.nextTurn(turnPlayer);
    } else {
      uno.turn(turnPlayer);
    }
        
  }
  
  @Override
  public void turn(Player turnPlayer) {
    String selectionChar;
    LinkedList<Card> deck = uno.getDeck();
    LinkedList<Card> stack = uno.getStack();

    if (deck.size() == 0) 
      uno.refillDeck();

    Printer.printPostWildTurn(turnPlayer, stack.getFirst(), uno.getWildColor());

    // Reading user input
    selectionChar = in.next();

    if (selectionChar.equals("pass")) 
      uno.pass(turnPlayer);
    else if (selectionChar.equals("eat"))
      uno.eatCard(turnPlayer);
    else if (selectionChar.length() == 1)
      uno.playCard(turnPlayer, selectionChar);
    else 
      uno.turn(turnPlayer);    
  }
}
