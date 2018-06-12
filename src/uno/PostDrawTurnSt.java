package uno;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * PostDrawTurnSt
 */
public class PostDrawTurnSt implements TurnSt{
  Uno uno;

  public PostDrawTurnSt(Uno uno) {
    this.uno = uno;
  }

  @Override
  public void playCard(Player turnPlayer, String selectionChar) {
    int turnsCounter = uno.getTurnsCounter();
    Card selectedCard = turnPlayer.getCards().get(selectionChar);
    LinkedList<Card> stack = uno.getStack();
    
    if (selectedCard != null && selectedCard.getNumber().equals("+2")) {
      stack.addFirst( turnPlayer.pickCard(selectionChar) );

      uno.reactToCard(selectedCard);

      uno.nextTurn();

    } else {
      // TODO: Display "this card doesn't exists" message
      uno.turn(turnPlayer);
    }
        
  }

  @Override
  public void turn(Player turnPlayer) {
    if (uno.getSumToDraw() == 0) {
      uno.turnSt = uno.normalTurnSt;
      uno.nextTurn();
    }

    String selectionChar;
    LinkedList<Card> deck = uno.getDeck();
    LinkedList<Card> stack = uno.getStack();

    if (deck.size() == 0) 
      uno.refillDeck();

    Printer.printPostDrawTurn(turnPlayer, stack.getFirst(), uno.getSumToDraw(), stack.getFirst().getColor());

    // Reading user input
    selectionChar = in.next();

    if (selectionChar.equals("eat"))
      uno.eatSum(turnPlayer);
    else if (selectionChar.length() == 1)
      uno.playCard(turnPlayer, selectionChar);
    else 
      uno.turn(turnPlayer);    
  }

}