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

  public void printCard() {
    System.out.print("+");
    printMultiple('-', width);
    System.out.println("+");

    System.out.print("| " + this.number);
    printMultiple(' ', width - 2);
    System.out.println("|");

    System.out.print("|");
    printMultiple(' ', width);
    System.out.println("|");

    System.out.println("| " + this.color + " |");

    System.out.print("|");
    printMultiple(' ', width);
    System.out.println("|");

    System.out.print("|");
    printMultiple(' ', width - 2);
    System.out.println(this.number + " |");

    System.out.print("+");
    printMultiple('-', width);
    System.out.println("+");
  }

  private static void printMultiple(char Character, int n) {
    if (n > 0) {
      System.out.print(Character);
      printMultiple(Character, n - 1);
    }
  }

}