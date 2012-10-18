package pl.edu.agh.draughts.game.elements;

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.text.Position;

public class Move {

	private Piece piece;

	private Queue<Position> steps = new LinkedList<Position>();

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public void addNextStep(Position position) {
		steps.add(position);
	}
}
