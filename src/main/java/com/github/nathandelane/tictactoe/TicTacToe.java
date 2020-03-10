package com.github.nathandelane.tictactoe;

import static com.github.nathandelane.tictactoe.GameState.gameIsOver;
import static com.github.nathandelane.tictactoe.Messenger.messageUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TicTacToe {
	
	private TicTacToe() {
		messageUser("Welcome to Tic Tac Toe!");
	}
	
	public void initializeGame() {
		try (final BufferedReader consoleInputReader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"))) {
			runGame(consoleInputReader);
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	public void runGame(final BufferedReader consoleInputReader) throws IOException {
		GameState gameState = GameState.initializeGameState();
		
		boolean userInputIsValid = true;
		
		while (!gameIsOver(gameState)) {
			if (userInputIsValid && (gameState.board.message == null || gameState.board.message.trim().equalsIgnoreCase(""))) {
				gameState = GameState.updateGameState(GameState.getCurrentUser(gameState.currentPlayerValue), gameState);
			}
			else {
				messageUser(gameState.board.message);
			}
			
			messageUser("%n%nCurrent user: %s%n--------------------%n%sPlease enter coordinates (Letter Number): ", gameState.currentPlayerValue, gameState.board);
			
			final String userInput = consoleInputReader.readLine();

			userInputIsValid = UserInput.isValid(userInput);
			
			if (userInputIsValid) {
				gameState = processUserInput(userInput, gameState);
			}
			else {
				messageUser("Did not understand input: %s", userInput);
			}
		}
		
		processLastGameState(gameState);
	}
	
	public static GameState processUserInput(final String userInput, final GameState gameState) {
		final GameState newGameState;
		
		int elementIndex = -1;
		
		if (UserInput.isQuit(userInput)) {
			messageUser("Quitting...");
			
			newGameState = GameState.updateGameState(true, gameState);
		}
		else if (UserInput.isPlacementCommand(userInput) && ((elementIndex = elementIndexIsInRange(UserInput.translateInputToElementIndex(userInput))) > -1)) {
			final PositionUpdater positionUpdater = new PositionUpdater(gameState.currentPlayerValue, elementIndex);
			
			newGameState = GameState.updateGameState(Board.attemptPositionUpdate(gameState.board, positionUpdater), gameState);
		}
		else {
			newGameState = gameState;
		}
		
		return newGameState;
	}
	
	public static int elementIndexIsInRange(final int elementIndex) {
		return (elementIndex >= 0 && elementIndex < 9) ? elementIndex : -1;
	}
	
	public static void processLastGameState(final GameState gameState) {
		if (GameState.isTicTacToe(gameState)) { 
			messageUser("Winner, Winner! %s wins!", gameState.currentPlayerValue);
		}
		else if (GameState.allPositionsAreFilled(gameState.board)) {
			messageUser("All positions have been filled with no winner. The game is a draw!");
		}
		else if (!gameState.userQuitGame) {
			messageUser("Unknown game state.");
		}
	}
	
	public static void main(final String[] args) {
		new TicTacToe().initializeGame();
	}
	
}
