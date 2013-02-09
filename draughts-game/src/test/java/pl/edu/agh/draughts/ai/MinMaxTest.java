package pl.edu.agh.draughts.ai;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

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
        private final int id;
        
        public MockMove(int id) {
            super(new Pawn(PieceColor.WHITE), 0, 0);
            this.id = id;
        }
        
        public int getId() {
            return this.id;
        }
        
        @Override
        public Move copyOf() {
            return new MockMove(id);
        }
        
        @Override
        public void doMove(Chessboard chessboard) {
            ((MockChessboard)chessboard).setId(id);
        }
    }

    static class MockChessboard extends Chessboard {
        
        private Move[] moves;
        private int id;
        
        public MockChessboard(int id, Move[] moves) {
            super();
            this.id = id;
            this.moves = moves;
        }

        @Override
        public List<Move> getPossibleMoves(PieceColor pieceColor) {
            return Arrays.asList(moves);
        }
        
        @Override
        public Chessboard copyOf() {
            return new MockChessboard(id, moves);
        }
        
        public int getId() {
            return this.id;
        }
        
        public void setId(int id) {
            this.id = id;
        }
    }

    @Test
    public void test() throws InvalidPieceException {
        // given
        EvaluationFunction evaluationFunction = mock(EvaluationFunction.class);
        Move[] moves = {new MockMove(1), new MockMove(2), new MockMove(3)};
        Chessboard chessboard = new MockChessboard(0, moves);
        MinMax minMax = new MinMax(PieceColor.WHITE);
        when(evaluationFunction.calculateValue(Mockito.any(Chessboard.class), Mockito.any(PieceColor.class)))
                .thenAnswer(
                new Answer<Float>() {
            @Override
            public Float answer(org.mockito.invocation.InvocationOnMock invocation) throws Throwable {
                        PieceColor pc = ((PieceColor) invocation.getArguments()[1]);
                        if (pc == PieceColor.WHITE) {
                            int id = ((MockChessboard) invocation.getArguments()[0]).getId();
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
