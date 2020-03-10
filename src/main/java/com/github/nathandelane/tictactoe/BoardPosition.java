package com.github.nathandelane.tictactoe;


public class BoardPosition implements Comparable<BoardPosition> {
	
	public static final BoardPosition EMPTY_BOARD_POSITION = newBoardPosition();

	public final BoardPositionValue value;
	
	private BoardPosition(final BoardPositionValue value) {
		this.value = value;
	}
	
	public static BoardPosition newBoardPosition() {
		return new BoardPosition(BoardPositionValue.EMPTY);
	}
	
	public static BoardPosition newBoardPosition(final BoardPositionValue value) {
		return new BoardPosition(value);
	}
	
	@Override
	public boolean equals(Object other) {
		return other != null && other instanceof BoardPosition && ((BoardPosition) other).value == this.value;
	}
	
	@Override
	public int hashCode() {
		return value.hashCode();
	}
	
	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public int compareTo(final BoardPosition other) {
		final int result;
		
		if (other.value == this.value) {
			result = 0;
		}
		else {
			result = -1;
		}
		
		return result;
	}
	
}
