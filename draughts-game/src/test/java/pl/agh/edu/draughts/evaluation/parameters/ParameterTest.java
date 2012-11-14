package pl.agh.edu.draughts.evaluation.parameters;

import static org.mockito.Mockito.*;
import pl.edu.agh.draughts.game.elements.King;
import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.Piece;
import pl.edu.agh.draughts.game.elements.PieceColor;

public class ParameterTest {

	protected King createKingMock(PieceColor pieceColor) {
		return createPieceMock(King.class, pieceColor);
	}

	protected Pawn createPawnMock(PieceColor pieceColor) {
		return createPieceMock(Pawn.class, pieceColor);
	}

	private <T extends Piece> T createPieceMock(Class<T> clazz,
			PieceColor pieceColor) {
		T pieceMock = mock(clazz);
		when(pieceMock.getPieceColor()).thenReturn(pieceColor);
		return pieceMock;
	}
}
