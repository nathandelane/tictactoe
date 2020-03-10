package com.github.nathandelane.tictactoe;


public enum BoardPositionValue {
	
	EMPTY(" "),
	CROSS("X"),
	KNOT("O");
	
	private final String strValue;
	
	private BoardPositionValue(final String strValue) {
		this.strValue = strValue;
	}
	
	@Override
	public String toString() {
		return strValue;
	}

}
