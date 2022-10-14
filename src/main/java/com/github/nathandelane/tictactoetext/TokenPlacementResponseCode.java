package com.github.nathandelane.tictactoetext;

import java.util.Objects;

public final class TokenPlacementResponseCode {

  public static final TokenPlacementResponseCode ERROR = new TokenPlacementResponseCode(1);

  public static final TokenPlacementResponseCode SUCCESS = new TokenPlacementResponseCode(0);

  private final int code;

  private TokenPlacementResponseCode(final int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TokenPlacementResponseCode that = (TokenPlacementResponseCode) o;
    return code == that.code;
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }

}
