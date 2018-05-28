package uno;

/**
 * Card
 */
public class Card {
  private int width = 8;
  private int number;
  private String color;

  public Card (int number, String color) {
    this.number = number;
    this.color = color;
  }

  public boolean isCompatible(Card comparator) {
    return (
      comparator.getColor().equals(this.color) ||
      comparator.getNumber() == this.number
    );
  }

  public String getColor() { return this.color; }
  public int getNumber() { return this.number; }
  public String getNumberS() { return Integer.toString(this.number); }

}