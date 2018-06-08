package uno;

import java.util.Scanner;

/**
 * TurnSt
 */
public interface TurnSt {
  Scanner in = new Scanner(System.in);

  public void turn(Player turnPlayer);
  public void playCard(Player turnPlayer, String selectionChar);
}