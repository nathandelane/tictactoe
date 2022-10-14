package com.github.nathandelane.tictactoetext;

import static com.github.nathandelane.tictactoetext.Board.BOARD;
import static com.github.nathandelane.tictactoetext.TokenPlacementResponseCode.ERROR;

import java.util.Scanner;

public final class PlayerTurn {

  private PlayerTurn() { }

  public static TokenPlacementResult playFor(final byte token, final String playerLabel) {
    System.out.format("Player %s's turn - please enter: row column> ", playerLabel);

    Scanner in = new Scanner(System.in);

    final int row = in.nextInt();
    final int column = in.nextInt();

    final TokenPlacementResult result = BOARD.placeToken(token, row, column);

    if (result.resultCode == ERROR) {
      System.out.format("Result: %s%n", result.message);
    }

    return result;
  }

}
