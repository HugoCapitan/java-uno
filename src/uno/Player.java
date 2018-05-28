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

  public void addCard(Card card) {
    int i = this.cards.size();
    this.cards.put(qwerty.substring(i, i + 1), card);
  }

  public void addCards(List<Card> cards) {
    int startingIndex = this.cards.size();

    for (int i = startingIndex; i < startingIndex + cards.size(); i++) {
      Card cardToAdd = cards.get(i);
      this.cards.put(qwerty.substring(i, i + 1), cardToAdd);
    }
  }

  public Map<String, Card> getCards() { 
    return this.cards; 
  }  

  public String getName() { 
    return this.name; 
  }
  
  public List<Card> getOrderedCards() {
    List<Card> ordered = new ArrayList<>();

    for (int i = 0; i < this.cards.size(); i++) {
      ordered.add(i, this.cards.get( qwerty.substring(i, i + 1) ));
    }

    return ordered;
  }

  public Card pickCard(String selection) {
    Card selectedCard = this.cards.get(selection);
    String lastKey = qwerty.substring(
      this.cards.size() - 1, this.cards.size()
    );

    if (lastKey.equals(selection)) 
      this.cards.remove(selection);
    else if (selectedCard != null) 
      // Replacing selected card with last in the map
      this.cards.put(selection, this.cards.remove(lastKey));
    

    return selectedCard;
  }

}
