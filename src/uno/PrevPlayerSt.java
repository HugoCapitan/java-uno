package uno;

import java.util.ListIterator;

/**
 * PrevPlayerSt
 */
public class PrevPlayerSt implements RetrievePlayerSt {
  Uno uno;

  public PrevPlayerSt(Uno uno) {
    this.uno = uno;
  }

  @Override
  public Player retrievePlayer() {
    ListIterator iterator = uno.getPlayersIterator();

    if (iterator == null || !iterator.hasPrevious()) {
      iterator = uno.getPlayers().listIterator( uno.getPlayersNum() );
      uno.setPlayersIterator(iterator);
    }

    return (Player) iterator.previous();
  }
  
}