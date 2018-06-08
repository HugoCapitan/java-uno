package uno;

/**
 * Card
 */
public class Card {
  private int width = 8;
  private String number;
  private String color;

  public Card (String number, String color) {
    this.number = number;
    this.color = color;
  }

  public boolean isCompatible(Card comparator) {
    return (
      comparator.getColor().equals("Wild") ||
      comparator.getColor().equals(this.color) || 
      comparator.getNumber().equals(this.number)
    );
  }

  public String getColor() { return this.color; }
  public String getNumber() { return this.number; }

}