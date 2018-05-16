package uno;

import java.util.ArrayList;
import java.util.List;

/**
 * Player
 */
public class Player {

  private String name;
  private List<Card> cards;

  // public Player(String name) {
  public Player(String name, List<Card> cards) {
    this.name = name;
    this.cards = cards;
  }

  public String getName() { return this.name; }

  public List<Card> getCardS() { return this.cards; }

}
