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

  public String getColor() { return this.color; }
  public String getNumber() { return this.number; }
  public String getNumberS() { return Integer.toString(this.number); }

}