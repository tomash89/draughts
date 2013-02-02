package pl.edu.agh.draughts.ai;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import pl.edu.agh.draughts.evaluation.parameters.EvaluationFunction;
import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class MinMaxTest {

    static class MockMove extends Move {
        public MockMove(int id) {
            super(new Pawn(PieceColor.WHITE), 0, 0);
            this.id = id;
        }
        public int id;
    }

    static class MockChessboard extends Chessboard {
        public MockChessboard(int id) {
            super();
            this.id = id;
        }

        public int id;

        @Override
        public boolean doMove(Move move) {
            this.id = ((MockMove) move).id;
            return true;
        }
    }

    @Test
    public void test() throws InvalidPieceException {
        // given
        EvaluationFunction evaluationFunction = mock(EvaluationFunction.class);
        Chessboard chessboard = spy(new MockChessboard(0));
        MinMax minMax = new MinMax(PieceColor.WHITE);
        minMax.getMinMaxMove(chessboard, PieceColor.WHITE, evaluationFunction, 1);
        Move[] moves = {new MockMove(1), new MockMove(2), new MockMove(3)};
        when(chessboard.getPossibleMoves(Mockito.any(PieceColor.class))).thenReturn(Arrays.asList(moves));
        when(chessboard.copyOf()).thenReturn(chessboard);
        when(evaluationFunction.calculateValue(Mockito.any(Chessboard.class), Mockito.any(PieceColor.class)))
                .thenAnswer(
                new Answer<Float>() {
            @Override
            public Float answer(org.mockito.invocation.InvocationOnMock invocation) throws Throwable {
                        PieceColor pc = ((PieceColor) invocation.getArguments()[1]);
                        if (pc == PieceColor.WHITE) {
                            int id = ((MockChessboard) invocation.getArguments()[0]).id;
                            System.out.println(id);
                            return new Float(id);
                        } else {
                            return 0.0f;
                        }
            };
        });

        // when
        Move move = minMax.getMinMaxMove(chessboard, PieceColor.WHITE, evaluationFunction, 1);

        // then
        assertEquals(3, ((MockMove) move).id);
    }

}
