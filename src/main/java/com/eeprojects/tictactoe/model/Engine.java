package com.eeprojects.tictactoe.model;

import com.eeprojects.tictactoe.interfaces.SearchStrategy;

public class Engine implements SearchStrategy {

	private SearchStrategy search;

	public Engine(SearchStrategy search) {
		this.search = search;
	}

	public Move getMove(Board board, String playPiece) {
		return search.getMove(board, playPiece);
	}

}
