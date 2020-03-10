package com.github.nathandelane.tictactoe;


public class PositionUpdater {
	
	public final BoardPositionValue value;
	
	public final int elementIndex;

	public PositionUpdater(BoardPositionValue value, int elementIndex) {
		this.value = value;
		this.elementIndex = elementIndex;
	}
	
}
