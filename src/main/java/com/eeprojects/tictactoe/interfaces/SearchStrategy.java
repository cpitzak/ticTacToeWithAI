package com.eeprojects.tictactoe.interfaces;

import com.eeprojects.tictactoe.model.Board;
import com.eeprojects.tictactoe.model.Move;

public interface SearchStrategy {

	Move getMove(Board board, String playPiece);

}
