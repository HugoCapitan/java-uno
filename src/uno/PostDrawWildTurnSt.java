package uno;

import java.util.LinkedList;

/**
 * PostDrawWildTurnSt
 */
public class PostDrawWildTurnSt implements TurnSt {
  Uno uno;

  public PostDrawWildTurnSt(Uno uno) {
    this.uno = uno;
  }

  @Override
  public void playCard(Player turnPlayer, String selectionChar) {
    int turnsCounter = uno.getTurnsCounter();
    Card selectedCard = turnPlayer.getCards().get(selectionChar);
    LinkedList<Card> stack = uno.getStack();
    
    if (selectedCard != null && selectedCard.getNumber().equals("+4")) {
      stack.addFirst( turnPlayer.pickCard(selectionChar) );

      uno.reactToCard(selectedCard);

      uno.nextTurn(turnPlayer);

    } else {
      // TODO: Display "this card doesn't exists" message
      uno.turn(turnPlayer);
    }
           
  }
  
  @Override
  public void turn(Player turnPlayer) {
    if (uno.getSumToDraw() == 0) {
      uno.turnSt = uno.postWildTurnSt;
      uno.nextTurn(turnPlayer);
    }

    String selectionChar;
    LinkedList<Card> deck = uno.getDeck();
    LinkedList<Card> stack = uno.getStack();

    if (deck.size() == 0) 
      uno.refillDeck();

    Printer.printPostDrawTurn(turnPlayer, stack.getFirst(), uno.getSumToDraw(), uno.getWildColor());

    // Reading user input
    selectionChar = in.next();

    if (selectionChar.equals("eat"))
      uno.eatSum(turnPlayer); // TODO: eatsumdraw
    else if (selectionChar.length() == 1)
      uno.playCard(turnPlayer, selectionChar);
    else 
      uno.turn(turnPlayer);    
  }
}
