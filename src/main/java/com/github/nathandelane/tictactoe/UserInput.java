package com.github.nathandelane.tictactoe;

import java.util.regex.Pattern;

public class UserInput {
	
	private static final Pattern VALID_USER_INPUT_PATTERN = Pattern.compile("\\s*([A-Ca-c]{1}\\s*[0-2]{1}|Q)\\s*");
	
	private static final Pattern PLACEMENT_COMMAND_PATTERN = Pattern.compile("\\s*([A-C]{1})\\s*([0-2]{1})\\s*");
	
	private UserInput() { }
	
	public static boolean isValid(final String userInput) {
		return VALID_USER_INPUT_PATTERN.asPredicate().test(userInput.trim().toUpperCase());
	}
	
	public static boolean isQuit(final String userInput) {
		return userInput.trim().equalsIgnoreCase("Q");
	}
	
	public static boolean isPlacementCommand(final String userInput) {
		return PLACEMENT_COMMAND_PATTERN.asPredicate().test(userInput.trim().toUpperCase());
	}
	
	public static int translateInputToElementIndex(final String userInput) {
		final int elementIndex;
		
		final String[] tokens = new String[2];
		
		for (final char nextValue : userInput.toCharArray()) {
			if (Character.isAlphabetic(nextValue)) {
				tokens[0] = "" + Character.toUpperCase(nextValue);
			}
			else if (Character.isDigit(nextValue)) {
				tokens[1] = "" + nextValue;
			}
		}
		
		int xValue = -1;
		
		if (tokens[0].equals("A")) {
			xValue = 0;
		}
		else if (tokens[0].equals("B")) {
			xValue = 1;
		}
		else if (tokens[0].equals("C")) {
			xValue = 2;
		}
		
		final int yValue = Integer.parseInt(tokens[1]);
		
		if (xValue < 0 || yValue < 0 || yValue > 2) {
			elementIndex = -1;
		}
		else {
			elementIndex = (yValue * 3 + xValue);
		}
		
		return elementIndex;
	}

}
