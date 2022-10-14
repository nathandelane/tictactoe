package com.github.nathandelane.tictactoetext;

import java.util.Arrays;
import java.util.List;

import static com.github.nathandelane.tictactoetext.TokenPlacementResponseCode.ERROR;
import static com.github.nathandelane.tictactoetext.TokenPlacementResponseCode.SUCCESS;

public class Board {

  public static final Board BOARD = new Board();

  public static final byte NO_TOKEN = 0x00; // 00

  public static final byte X_TOKEN = 0x01; // 01

  public static final byte O_TOKEN = 0x02; // 10

  private static final int BOARD_WIDTH = 3;

  private static final List<Integer> X_OCCUPATION_MAP = Arrays.asList(1, 4, 16, 64, 256, 1_024, 4_096, 16_384, 65_536);

  private static final List<Integer> O_OCCUPATION_MAP = Arrays.asList(2, 8, 32, 128, 512, 2_048, 8_192, 32_768, 131_072);

  private static final List<Integer> X_WINS = Arrays.asList(21, 1_344, 86_016, 4_161, 16_644, 66_576, 65_793, 4_368);

  private static final List<Integer> O_WINS = Arrays.asList(42, 2_688, 172_032, 8_322, 33_288, 133_152, 131_586, 8_736);

  private int boardMap; // 32-bit number

  private boolean[] spacesOccupied = new boolean[] {
    false, false, false,
    false, false, false,
    false, false, false
  };

  private Board() {
    boardMap = 0;
  }

  public TokenPlacementResult placeToken(final byte token, final int row, final int column) {
    if (token != X_TOKEN && token != O_TOKEN) return new TokenPlacementResult(ERROR,"Token must be either 0x01 or 0x02.", token);
    if (row < 0 || row > 2) return new TokenPlacementResult(ERROR, "Row must be between 0 and 2, inclusively.", token);
    if (column < 0 || column > 2) return new TokenPlacementResult(ERROR, "Column must be between 0 and 2, inclusively.", token);
    if (isLocationOccupied(row, column)) return new TokenPlacementResult(ERROR, "Location is already occupied.", token);

    final int index = getIndexForLocation(row, column);
    final int tokenPlacementValue;

    if (token == X_TOKEN) {
      tokenPlacementValue = X_OCCUPATION_MAP.get(index);
    }
    else {
      tokenPlacementValue = O_OCCUPATION_MAP.get(index);
    }

    boardMap = (boardMap | tokenPlacementValue);
    spacesOccupied[index] = true;

    if (token == X_TOKEN) {
      return new TokenPlacementResult(SUCCESS, "Success", O_TOKEN);
    }
    else {
      return new TokenPlacementResult(SUCCESS, "Success", X_TOKEN);
    }
  }

  public boolean isFull() {
    boolean foundUnoccupiedSpace = false;

    for (final boolean nextSpaceOccupied : spacesOccupied) {
      if (!nextSpaceOccupied) {
        foundUnoccupiedSpace = true;

        break;
      }
    }

    return !foundUnoccupiedSpace;
  }

  public void reset() {
    boardMap = 0;
  }

  public byte isWinner() {
    final byte noWinner = 0x00;

    byte whoIsWinner = noWinner;

    for (final int nextMask : X_WINS) {
      final int testResult = (boardMap ^ nextMask);

      if (testResult == (boardMap - nextMask)) {
        whoIsWinner = X_TOKEN;
        break;
      }
    }
    for (final int nextMask : O_WINS) {
      final int testResult = (boardMap ^ nextMask);

      if (testResult == (boardMap - nextMask)) {
        whoIsWinner = O_TOKEN;
        break;
      }
    }

    return whoIsWinner;
  }

  public boolean isLocationOccupied(final int row, final int column) {
    final int index = getIndexForLocation(row, column);
    final int iXOccupied = X_OCCUPATION_MAP.get(index);
    final int iOOccupied = O_OCCUPATION_MAP.get(index);
    final int xOccupiedTestResult = (boardMap ^ iXOccupied);
    final int oOccupiedTestResult =  (boardMap ^ iOOccupied);

    return xOccupiedTestResult == (boardMap - iXOccupied) || oOccupiedTestResult == (boardMap - iOOccupied);
  }

  public int getIndexForLocation(final int row, final int column) {
    return (column + (BOARD_WIDTH * row));
  }

  @Override
  public String toString() {
    final String filledBoard = Integer.toBinaryString(boardMap);
    final StringBuilder sb = new StringBuilder();

    if (filledBoard.length() < 18) {
      for (int i = 0; i < (18 - filledBoard.length()); i++) {
        sb.append("0");
      }
    }

    sb.append(filledBoard);

    final String[] placementOnBoard = new String[9];
    final String stringBoard = sb.toString();

    for (int i = 0; i < 9; i++) {
      final int index = (18 - ((i + 1) * 2 ));
      final String binarySubString = stringBoard.substring(index, (index + 2));

      if (binarySubString.equals("01")) {
        placementOnBoard[i] = "X";
      }
      else if (binarySubString.equals("10")) {
        placementOnBoard[i] = "O";
      }
      else {
        placementOnBoard[i] = " ";
      }
    }

    final StringBuilder formatted = new StringBuilder("\n")
      .append("   0   1   2\n")
      .append("0  ").append(placementOnBoard[0]).append(" | ").append(placementOnBoard[1]).append(" | ").append(placementOnBoard[2]).append("\n")
      .append("  ---+---+---\n")
      .append("1  ").append(placementOnBoard[3]).append(" | ").append(placementOnBoard[4]).append(" | ").append(placementOnBoard[5]).append("\n")
      .append("  ---+---+---\n")
      .append("2  ").append(placementOnBoard[6]).append(" | ").append(placementOnBoard[7]).append(" | ").append(placementOnBoard[8]).append("\n");

    return formatted.append("\n").toString();
  }

}
