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
    LinkedList<Card> deck = uno.getDeck();
    LinkedList<Card> stack = uno.getStack();
    ListIterator playersIterator = uno.getPlayersIterator();
    
    if (selectedCard != null) {
      if (turnsCounter == 1 || stack.getFirst().isCompatible(selectedCard)) {
        stack.addFirst( turnPlayer.pickCard(selectionChar) );

        switch(selectedCard.getNumber()) {
          case "Flip":
            if (uno.retrievePlayerSt instanceof NextPlayerSt) {
              uno.retrievePlayerSt = uno.prevPlayerSt;
              playersIterator.previous();
            } else {
              uno.retrievePlayerSt = uno.nextPlayerSt;
              playersIterator.next();
            }
            break;
          case "Block":
            uno.retrievePlayer();
            break;
          case "+2":
            uno.turnSt = uno.postDrawTurnSt;
          case "Wild":
            uno.setWildColor(Printer.askForWildColor());
            uno.turnSt = uno.postWildTurnSt;
            break;
          case "+4":

          default:
            break;
        }

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

    Printer.printTurn(turnPlayer, stack.getFirst());

    // Reading user input
    selectionChar = in.next();

    if (selectionChar.equals("eat")) {
      Printer.printCardsSet(uno.eatTwice(turnPlayer));
      uno.turnSt = uno.normalTurnSt;
      try {
        System.in.read();
      } catch (Exception e) {
        
      }
      uno.nextTurn();
    } else if (selectionChar.length() == 1) {
      uno.playCard(turnPlayer, selectionChar);
    } else {
      uno.turn(turnPlayer);    
    }
  }

}