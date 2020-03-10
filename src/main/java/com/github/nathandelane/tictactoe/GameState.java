package com.github.nathandelane.tictactoe;

import static com.github.nathandelane.tictactoe.Messenger.messageSystem;

import java.util.Arrays;

public class GameState {
	
	public final Board board;
	
	public final BoardPositionValue currentPlayerValue;
	
	public final boolean userQuitGame;
	
	private GameState() {
		this.board = Board.newBoard();
		this.currentPlayerValue = null;
		this.userQuitGame = false;
	}
	
	private GameState(final Board board, final BoardPositionValue currentPlayerValue, final boolean userQuitGame) {
		this.board = board;
		this.currentPlayerValue = currentPlayerValue;
		this.userQuitGame = userQuitGame;
	}
	
	public static GameState initializeGameState() {
		return new GameState();
	}
	
	public static GameState updateGameState(final Board board, final GameState gameState) {
		return new GameState(board, gameState.currentPlayerValue, gameState.userQuitGame);
	}
	
	public static GameState updateGameState(final BoardPositionValue currentPlayerValue, final GameState gameState) {
		return new GameState(gameState.board, currentPlayerValue, gameState.userQuitGame);
	}
	
	public static GameState updateGameState(final boolean userQuitGame, final GameState gameState) {
		return new GameState(gameState.board, gameState.currentPlayerValue, userQuitGame);
	}
	
	public static boolean gameIsOver(final GameState gameState) {
		return GameState.allPositionsAreFilled(gameState.board) || GameState.isTicTacToe(gameState) || gameState.userQuitGame;
	}
	
	public static boolean allPositionsAreFilled(final Board board) {
		final int searchResult = Arrays.binarySearch(board.representation, BoardPosition.EMPTY_BOARD_POSITION);
		
		messageSystem("Search result %s%n", searchResult);
		
		return (searchResult < 0);
	}
	
	private static final int[][] WINS = new int[][] {
		{ 0, 1, 2 },
		{ 3, 4, 5 },
		{ 6, 7, 8 },
		{ 0, 3, 6 },
		{ 1, 4, 7 },
		{ 2, 5, 8 },
		{ 0, 4, 8 },
		{ 2, 4, 6 }
	};
	
	public static boolean isTicTacToe(final GameState gameState) {
		boolean isWin = false;
		
		final BoardPositionValue currentPlayerValue = gameState.currentPlayerValue;
		final BoardPosition[] r = gameState.board.representation;
		
		int winsIndex = 0;
		
		do {
			final int[] nextWin = WINS[winsIndex];
			
			isWin = (
				r[nextWin[0]].value == currentPlayerValue &&
				r[nextWin[1]].value == currentPlayerValue &&
				r[nextWin[2]].value == currentPlayerValue
			);
			
			winsIndex++;
		} while (!isWin && winsIndex < WINS.length);
		
		return isWin;
	}
	
	public static BoardPositionValue getCurrentUser(final BoardPositionValue currentPlayerValue) {
		final BoardPositionValue newPlayerValue;
		
		if (currentPlayerValue == null || currentPlayerValue == BoardPositionValue.KNOT) {
			newPlayerValue = BoardPositionValue.CROSS;
		}
		else {
			newPlayerValue = BoardPositionValue.KNOT;
		}
		
		return newPlayerValue;
	}

}
