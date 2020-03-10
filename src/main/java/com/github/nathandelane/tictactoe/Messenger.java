package com.github.nathandelane.tictactoe;


public class Messenger {
	
	private static final boolean ENABLE_SYSTEM_MESSAGES = false;
	
	private Messenger() { }
	
	public static void messageUser(final String message, final Object ... args) {
		if (message != null && !message.trim().equalsIgnoreCase("")) {
			System.out.format(message, args);
		}
	}
	
	public static void messageSystem(final String message, final Object ... args) {
		if (message != null && !message.trim().equalsIgnoreCase("")) {
			if (ENABLE_SYSTEM_MESSAGES) {
				System.out.format("[SYSTEM] " + message, args);
			}
		}
	}

}
