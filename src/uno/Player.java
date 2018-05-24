package uno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Player
 */
public class Player {
  private static String qwerty = "qwertyuiopasdfghjklzxcvbnm";

  private String name;
  private Map<String, Card> cards = new HashMap<String, Card>();

  // public Player(String name) {
  public Player(String name, List<Card> cards) {
    this.name = name;
    this.addCards(cards);
  }

  public void addCards(List<Card> cards) {
    for (int i = 0; i < cards.size(); i++) {
      Card cardToAdd = cards.get(i);
      this.cards.put(qwerty.substring(i, i + 1), cardToAdd);
    }
  }

  public Map<String, Card> getCards() { return this.cards; }

  public String getName() { return this.name; }

  public List<Card> getOrderedCards() {
    List<Card> ordered = new ArrayList<>();

    for (int i = 0; i < this.cards.size(); i++) {
      ordered.add(i, this.cards.get( qwerty.substring(i, i + 1) ));
    }

    return ordered;
  }

}
