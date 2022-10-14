package com.github.nathandelane.tictactoetext;

public class TokenPlacementResult {

  public final TokenPlacementResponseCode resultCode;

  public final String message;

  public final byte nextPlayerToken;

  public TokenPlacementResult(final TokenPlacementResponseCode resultCode, final String message, final byte nextPlayerToken) {
    this.resultCode = resultCode;
    this.message = message;
    this.nextPlayerToken = nextPlayerToken;
  }

}
