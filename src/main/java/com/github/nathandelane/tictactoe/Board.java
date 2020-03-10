package com.github.nathandelane.tictactoe;

import static com.github.nathandelane.tictactoe.BoardPosition.EMPTY_BOARD_POSITION;

public class Board {
	
	public final BoardPosition[] representation;
	
	public final String message;
	
	private Board() {
		representation = new BoardPosition[] {
			EMPTY_BOARD_POSITION, EMPTY_BOARD_POSITION, EMPTY_BOARD_POSITION,
			EMPTY_BOARD_POSITION, EMPTY_BOARD_POSITION, EMPTY_BOARD_POSITION,
			EMPTY_BOARD_POSITION, EMPTY_BOARD_POSITION, EMPTY_BOARD_POSITION
		};
		message = "";
	}
	
	private Board(final Board originalBoard, final String message) {
		this.representation = originalBoard.representation;
		this.message = message;
	}
	
	private Board(final Board originalBoard, final PositionUpdater positionUpdater) {
		this(originalBoard, "");
		
		representation[positionUpdater.elementIndex] = BoardPosition.newBoardPosition(positionUpdater.value);
	}
	
	public static Board newBoard() {
		return new Board();
	}
	
	public static Board attemptPositionUpdate(final Board originalBoard, final PositionUpdater positionUpdater) {
		if (!positionIsAlreadyTaken(originalBoard, positionUpdater)) {
			return new Board(originalBoard, positionUpdater);
		}
		else {
			return new Board(originalBoard, "That position is already taken on the board. Please try another position.");
		}
	}
	
	public static boolean positionIsAlreadyTaken(final Board originalBoard, final PositionUpdater positionUpdater) {
		return (originalBoard.representation[positionUpdater.elementIndex].value != BoardPositionValue.EMPTY);
	}
	
	@Override
	public String toString() {
		return String.format("     [ A ][ B ][ C ]%n[ 0 ][ %s ][ %s ][ %s ]%n[ 1 ][ %s ][ %s ][ %s ]%n[ 2 ][ %s ][ %s ][ %s ]%n%n",
			representation[0], representation[1], representation[2],
			representation[3], representation[4], representation[5],
			representation[6], representation[7], representation[8]);
	}

}
