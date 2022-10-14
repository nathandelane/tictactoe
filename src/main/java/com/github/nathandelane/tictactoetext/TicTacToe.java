package com.github.nathandelane.tictactoetext;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import static com.github.nathandelane.tictactoetext.Board.*;
import static com.github.nathandelane.tictactoetext.TokenPlacementResponseCode.ERROR;

public class TicTacToe {

  private final Map<Byte, String> tokenLabelMap;

  private final Map<Byte, AtomicInteger> wins;

  private TicTacToe() {
    tokenLabelMap = new HashMap<>();
    tokenLabelMap.put(X_TOKEN, "X");
    tokenLabelMap.put(O_TOKEN, "O");

    wins = new HashMap<>();
    wins.put(X_TOKEN, new AtomicInteger(0));
    wins.put(O_TOKEN, new AtomicInteger(0));
  }

  private void run() {
    byte winner = NO_TOKEN;

    while (true) {
      byte currentPlayerToken;

      if (winner == NO_TOKEN) {
        currentPlayerToken = X_TOKEN;

        System.out.println("X goes first.");
      }
      else {
        currentPlayerToken = winner;

        final String tokenLabel = tokenLabelMap.get(currentPlayerToken);

        System.out.format("%n%1$s won! So, %1$s goes first.%n", tokenLabel);
      }

      TokenPlacementResult playResult = null;

      while (BOARD.isWinner() == NO_TOKEN && !BOARD.isFull()) {
        System.out.println(BOARD);

        playResult = PlayerTurn.playFor(currentPlayerToken, tokenLabelMap.get(currentPlayerToken));

        if (playResult.resultCode == ERROR) {
          System.out.println("Please try again...");
        } else {
          currentPlayerToken = playResult.nextPlayerToken;
          playResult = null;
        }
      }

      if (BOARD.isFull()) {
        System.out.println("\nBoard is full! The game is a draw.");
      }
      else {
        winner = BOARD.isWinner();

        if (winner != NO_TOKEN) {
          wins.get(winner).incrementAndGet();

          final String tokenLabel = tokenLabelMap.get(winner);

          System.out.format("%n%s is the winner!%n", tokenLabel);
        }
      }

      System.out.println(BOARD);
      System.out.print("Would you like to play again (Y or N)?> ");

      Scanner in = new Scanner(System.in);

      final String answer = in.next();

      if (answer.equalsIgnoreCase("N")) {
        final int xWins = wins.get(X_TOKEN).get();
        final int oWins = wins.get(O_TOKEN).get();

        System.out.println("Thanks for playing!");
        System.out.format("%nX wins: %s%nO wins: %s%n", xWins, oWins);

        break;
      } else {
        BOARD.reset();

        System.out.println("OK, let's play again...");
      }
    }
  }

  public static void main(final String[] args) {
    System.out.println("Welcome to Tic-Tac-Toe!");

    final TicTacToe ticTacToe = new TicTacToe();
    ticTacToe.run();
  }

}