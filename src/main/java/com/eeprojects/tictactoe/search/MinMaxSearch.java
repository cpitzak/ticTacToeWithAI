package com.eeprojects.tictactoe.search;

import java.util.ArrayList;
import java.util.List;

import com.eeprojects.tictactoe.interfaces.SearchStrategy;
import com.eeprojects.tictactoe.model.Board;
import com.eeprojects.tictactoe.model.Move;
import com.eeprojects.tictactoe.model.Node;
import com.eeprojects.tictactoe.model.Tree;
import com.eeprojects.tictactoe.search.utils.SearchUtils;

public class MinMaxSearch implements SearchStrategy {

	private Tree<Board> tree;
	private String playerPiece;
	private String opponentPiece;

	public Move getMove(Board board, String playPiece) {
		this.playerPiece = playPiece;
		this.opponentPiece = SearchUtils.changePiece(playPiece);
		Node<Board> root = new Node<Board>(null, Board.newInstance(board));
		// play all positions with the piece
		List<Node<Board>> children = SearchUtils.getChildren(root, playerPiece);
		if (!children.isEmpty()) {
			root.getChildren().addAll(children);
		}
		tree = new Tree<Board>(root);
		int maxDepth = board.calculateMaxDepth();
		int bestValue = minimax(tree.getRoot(), maxDepth, true, playerPiece);
		return tiebreaker(tree.getRoot().getChildren(), bestValue, board);
	}

	private Move tiebreaker(List<Node<Board>> children, int bestValue, Board originalBoard) {
		List<Board> boards = new ArrayList<Board>();
		for (Node<Board> child : children) {
			if (child.getWeight() == bestValue) {
				boards.add(child.getValue());
			}
		}

		for (Board board : boards) {
			if (board.isWin() && board.getWinningPiece().equals(playerPiece)) {
				return SearchUtils.getNewMoveFromBoard(originalBoard, board);
			}
		}
		return SearchUtils.getNewMoveFromBoard(originalBoard, boards.get(0));
	}

	private int minimax(Node<Board> node, int depth, boolean maximizingPlayer, String playPiece) {
		if (depth != 0 && node.getValue().isWin()) {
			int heuristicValue = getHeuristicValue(node);
			return heuristicValue;
		}
		if (depth == 0 || node.getChildren().isEmpty()) {
			int heuristicValue = getHeuristicValue(node);
			return heuristicValue;
		}
		if (maximizingPlayer) {
			int bestValue = Integer.MIN_VALUE;
			// change the piece
			if (!node.getChildren().isEmpty()) {
				playPiece = SearchUtils.changePiece(playPiece);
			}
			for (Node<Board> child : node.getChildren()) {
				// play all positions with the piece
				List<Node<Board>> children = SearchUtils.getChildren(child, playPiece);
				if (!children.isEmpty() && child.getChildren().isEmpty()) {
					child.getChildren().addAll(children);
				}
				int value = minimax(child, depth - 1, false, playPiece);
				bestValue = Math.max(bestValue, value);
				child.setWeight(bestValue);
			}
			node.setWeight(bestValue);
			return bestValue;
		} else {
			int bestValue = Integer.MAX_VALUE;
			// change the piece
			if (!node.getChildren().isEmpty()) {
				playPiece = SearchUtils.changePiece(playPiece);
			}
			for (Node<Board> child : node.getChildren()) {
				// play all positions with the piece
				List<Node<Board>> children = SearchUtils.getChildren(child, playPiece);
				if (!children.isEmpty() && child.getChildren().isEmpty()) {
					child.getChildren().addAll(children);
				}
				int value = minimax(child, depth - 1, true, playPiece);
				bestValue = Math.min(bestValue, value);
				child.setWeight(bestValue);
			}
			node.setWeight(bestValue);
			return bestValue;
		}
	}

	private Integer getHeuristicValue(Node<Board> node) {
		String winningPiece = node.getValue().getWinningPiece();
		if (node.getValue().isDraw()) { // draw
			return 0;
		} else if (winningPiece.equals(playerPiece)) { // player wins
			return 1;
		} else if (winningPiece.equals(opponentPiece)) { // player lost
			return -1;
		}
		throw new IllegalStateException("Hueristic was calculated when there was no end to the game");
	}

}
