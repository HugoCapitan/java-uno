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
    
  }
  
  @Override
  public void turn(Player turnPlayer) {
    String selectionChar;
    LinkedList<Card> deck = uno.getDeck();
    LinkedList<Card> stack = uno.getStack();

    if (deck.size() == 0) 
      uno.refillDeck();

    Printer.printTurn(turnPlayer, stack.getFirst());

    // Reading user input
    selectionChar = in.next();

    if (selectionChar.equals("eat"))
      uno.eatCard(turnPlayer); // TODO: eatsumdraw
    else if (selectionChar.length() == 1)
      uno.playCard(turnPlayer, selectionChar);
    else 
      uno.turn(turnPlayer);    
  }
}