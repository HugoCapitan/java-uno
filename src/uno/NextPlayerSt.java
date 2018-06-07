package uno;

import java.util.ListIterator;

/**
 * NextPlayerSt
 */
public class NextPlayerSt implements RetrievePlayerSt {
  Uno uno;

  public NextPlayerSt(Uno uno) {
    this.uno = uno;
  }
  
  @Override
  public Player retrievePlayer() {
    ListIterator iterator = uno.getPlayersIterator();

    if (iterator == null || !iterator.hasNext()) {
      iterator = uno.getPlayers().listIterator();
      uno.setPlayersIterator(iterator);
    }

    return (Player) iterator.next();
  }
  
}