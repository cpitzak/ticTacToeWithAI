package com.eeprojects.tictactoe.model;

import java.util.Arrays;

public class Board {

	//
	// 1, 2, 3
	// 4, 5, 6
	// 7, 8, 9
	//

	public static final int MIN_ROW = 0;
	public static final int MIN_COLUMN = 0;
	public static final int MAX_COLUMN = 3;
	public static final int MAX_ROW = 3;

	public static final String X_PIECE = "X";
	public static final String O_PIECE = "O";

	private String[][] board = new String[MAX_ROW][MAX_COLUMN];

	public static Board newInstance(Board board) {
		Board newBoard = new Board();
		for (int row = MIN_ROW; row < MAX_ROW; row++) {
			for (int col = MIN_COLUMN; col < MAX_COLUMN; col++) {
				String piece = board.getPiece(row, col);
				newBoard.setPiece(piece, row, col);
			}
		}
		return newBoard;
	}

	public void setPiece(String piece, int row, int col) {
		board[row][col] = piece;
	}

	public String getPiece(int row, int col) {
		return board[row][col];
	}

	public boolean isEmpty(int row, int col) {
		return board[row][col] == null;
	}

	public void print() {
		// 1 | 2 | 3
		// -----------
		// 4 | 5 | 6
		// -----------
		// 7 | 8 | 9

		for (int row = MIN_ROW; row < board.length; row++) {
			for (int col = MIN_COLUMN; col < board[0].length; col++) {
				System.out.print(" ");
				String piece = board[row][col];
				if ((col + 1) == board[0].length) {
					if (piece == null) {
						System.out.print(" ");
					} else {
						System.out.print(board[row][col]);
					}
				} else {
					if (piece == null) {
						System.out.print("  |");
					} else {
						System.out.print(board[row][col] + " |");
					}
				}
			}
			if ((row + 1) == board.length) {
				System.out.println();
			} else {
				System.out.println();
				System.out.println("-----------");
			}
		}
	}

	public boolean isWin() {
		return getWinningPiece() != null;
	}

	public String getWinningPiece() {
		String pieceOne = null;
		String pieceTwo = null;
		String pieceThree = null;

		// horizontal
		for (int row = MIN_ROW; row < MAX_ROW; row++) {
			pieceOne = board[row][MIN_COLUMN];
			pieceTwo = board[row][MIN_COLUMN + 1];
			pieceThree = board[row][MIN_COLUMN + 2];

			if (isThreeInARow(pieceOne, pieceTwo, pieceThree)) {
				return pieceOne;
			}
		}

		// verticle
		for (int col = MIN_COLUMN; col < MAX_COLUMN; col++) {
			pieceOne = board[MIN_ROW][col];
			pieceTwo = board[MIN_ROW + 1][col];
			pieceThree = board[MIN_ROW + 2][col];

			if (isThreeInARow(pieceOne, pieceTwo, pieceThree)) {
				return pieceOne;
			}
		}

		// diagonal
		pieceOne = board[MIN_ROW][MIN_COLUMN];
		pieceTwo = board[MIN_ROW + 1][MIN_COLUMN + 1];
		pieceThree = board[MIN_ROW + 2][MIN_COLUMN + 2];

		if (isThreeInARow(pieceOne, pieceTwo, pieceThree)) {
			return pieceOne;
		}

		// diagonal
		pieceOne = board[MIN_ROW][MIN_COLUMN + 2];
		pieceTwo = board[MIN_ROW + 1][MIN_COLUMN + 1];
		pieceThree = board[MIN_ROW + 2][MIN_COLUMN];

		if (isThreeInARow(pieceOne, pieceTwo, pieceThree)) {
			return pieceOne;
		}

		return null;
	}

	private boolean isThreeInARow(String pieceOne, String pieceTwo, String pieceThree) {
		boolean result = false;
		if (pieceOne != null & pieceTwo != null & pieceThree != null) {
			result = pieceOne.equals(pieceTwo) && pieceTwo.equals(pieceThree);
		}
		return result;
	}

	public boolean isDraw() {
		return !isAvailableSpot() && !isWin();
	}

	public boolean isAvailableSpot() {
		boolean isAvailableSpot = false;
		for (int row = MIN_ROW; row < MAX_ROW; row++) {
			for (int col = MIN_COLUMN; col < MAX_COLUMN; col++) {
				if (board[row][col] == null) {
					isAvailableSpot = true;
					break;
				}
			}
			if (isAvailableSpot) {
				break;
			}
		}
		return isAvailableSpot;
	}

	public int calculateMaxDepth() {
		int depth = 0;
		for (int row = MIN_ROW; row < MAX_ROW; row++) {
			for (int col = MIN_COLUMN; col < MAX_COLUMN; col++) {
				if (getPiece(row, col) == null) {
					depth++;
				}
			}
		}
		return depth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(board);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return true;
	}
}
