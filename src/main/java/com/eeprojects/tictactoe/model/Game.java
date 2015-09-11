package com.eeprojects.tictactoe.model;

import java.util.Scanner;

import com.eeprojects.tictactoe.search.MinMaxSearch;

public class Game {

	private static final int MAX_LOCATION = 9;
	private static final int MIN_LOCATION = 1;
	private boolean isPlayerOnesTurn;
	private String playerOnePiece;
	private String playerTwoPiece;
	private boolean isGameOver;
	private Board board;
	private Engine engine;

	public Game() {
		isPlayerOnesTurn = true;
		board = new Board();
		engine = new Engine(new MinMaxSearch());
	}

	public void play() {
		// for displaying board location selections
		Board boardLocation = new Board();
		int count = MIN_LOCATION;
		for (int row = Board.MIN_ROW; row < Board.MAX_ROW; row++) {
			for (int col = Board.MIN_COLUMN; col < Board.MAX_COLUMN; col++, count++) {
				boardLocation.setPiece(count + "", row, col);
			}
		}
		System.out.println("The following numbers represent the location of each piece:");
		boardLocation.print();
		Scanner in = new Scanner(System.in);

		String currentPiece = null;
		String userInput = null;
		while (!isGameOver) {
			if (isPlayerOnesTurn) {
				System.out.print("Hello Player one. ");
				if (playerOnePiece == null) {
					System.out.print("Please enter your piece: ");
					userInput = in.nextLine().toUpperCase();
					while (!isValidPiece(userInput)) {
						System.out.print("Invalid input, please try again: ");
						userInput = in.nextLine().toUpperCase();
					}
					playerOnePiece = userInput;
					if (playerOnePiece.equals(Board.X_PIECE)) {
						playerTwoPiece = Board.O_PIECE;
					} else {
						playerTwoPiece = Board.X_PIECE;
					}
				}
			}

			if (isPlayerOnesTurn) {
				currentPiece = playerOnePiece;
			} else {
				currentPiece = playerTwoPiece;
			}

			Move move = null;
			if (isPlayerOnesTurn) {
				System.out.print("Please enter the piece location: ");
				move = getUserInputMove(in);

				while (!board.isEmpty(move.getRow(), move.getCol())) {
					System.out.print("That location is already used, please try again: ");
					move = getUserInputMove(in);
				}
			} else {
				System.out.println("Computer thinking...");
				move = engine.getMove(board, currentPiece);
				System.out.println("Computer selected a move!");
			}

			board.setPiece(currentPiece, move.getRow(), move.getCol());

			System.out.println();
			board.print();
			if (board.isWin()) {
				System.out.print("Congratulations! ");
				if (isPlayerOnesTurn) {
					System.out.println("Player one wins!");
				} else {
					System.out.println("Player two wins!");
				}
				isGameOver = true;
			} else if (board.isDraw()) {
				System.out.println("It's a draw!");
				isGameOver = true;
			} else {
				isPlayerOnesTurn = !isPlayerOnesTurn;
			}
		}
	}

	private Move getUserInputMove(Scanner in) {
		String userInput = null;
		userInput = in.nextLine();
		Move move = null;
		while (!isValidSingleNumberMove(userInput)) {
			System.out.print("Invalid input, please try again: ");
			userInput = in.nextLine().toUpperCase();
		}
		int location = Integer.parseInt(userInput);
		move = getMove(location);
		return move;
	}

	private Move getMove(int location) {
		Integer selectedRow = null;
		Integer selectedCol = null;
		int checkLocation = 1;
		for (int row = Board.MIN_ROW; row < Board.MAX_ROW; row++) {
			for (int col = Board.MIN_COLUMN; col < Board.MAX_COLUMN; col++, checkLocation++) {
				if (checkLocation == location) {
					selectedRow = row;
					selectedCol = col;
					break;
				}
			}
			if (selectedRow != null && selectedCol != null) {
				break;
			}
		}
		return new Move(selectedRow, selectedCol);
	}

	public boolean isValidPiece(String piece) {
		return piece.equals(Board.O_PIECE) || piece.equals(Board.X_PIECE);
	}

	public boolean isValidSingleNumberMove(String location) {
		try {
			Integer loc = Integer.parseInt(location);
			return loc <= MAX_LOCATION && loc >= MIN_LOCATION;
		} catch (NumberFormatException e) {
		}
		return false;
	}

}
