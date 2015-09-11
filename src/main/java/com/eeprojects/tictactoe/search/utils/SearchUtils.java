package com.eeprojects.tictactoe.search.utils;

import java.util.ArrayList;
import java.util.List;

import com.eeprojects.tictactoe.model.Board;
import com.eeprojects.tictactoe.model.Move;
import com.eeprojects.tictactoe.model.Node;

public class SearchUtils {

	public static List<Node<Board>> getChildren(Node<Board> node, String playPiece) {
		List<Node<Board>> children = new ArrayList<Node<Board>>();
		for (int row = Board.MIN_ROW; row < Board.MAX_ROW; row++) {
			for (int col = Board.MIN_COLUMN; col < Board.MAX_COLUMN; col++) {
				if (node.getValue().getPiece(row, col) == null) {
					Board newBoard = Board.newInstance(node.getValue());
					newBoard.setPiece(playPiece, row, col);
					Node<Board> newNode = new Node<Board>(node, newBoard);
					children.add(newNode);
				}
			}
		}
		return children;
	}

	public static String changePiece(String currentPiece) {
		return currentPiece.equals(Board.X_PIECE) ? Board.O_PIECE : Board.X_PIECE;
	}

	public static Move getNewMoveFromBoard(Board originalBoard, Board newMoveBoard) {
		for (int row = Board.MIN_ROW; row < Board.MAX_ROW; row++) {
			for (int col = Board.MIN_COLUMN; col < Board.MAX_COLUMN; col++) {
				String originalPiece = originalBoard.getPiece(row, col);
				String newPiece = newMoveBoard.getPiece(row, col);
				if (originalPiece == null && newPiece != null) {
					return new Move(row, col);
				}
			}
		}
		return null;
	}

}
