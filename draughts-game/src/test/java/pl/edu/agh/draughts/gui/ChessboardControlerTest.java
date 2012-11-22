package pl.edu.agh.draughts.gui;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import pl.edu.agh.draughts.game.elements.ChessboardPosition;

public class ChessboardControlerTest {
    
    @Test
    public void shouldReturnLine() {
        //given
        ChessboardPosition a = new ChessboardPosition(1, 1);
        ChessboardPosition b = new ChessboardPosition(4, 4);
        
        //when
        List<ChessboardPosition> result =  ChessboardControler.makePostionsLine(a, b);
        
        //then
        Assert.assertTrue(result.size()==3);
    }
    
    @Test
    public void shouldReturnNull() {
      //given
        ChessboardPosition a = new ChessboardPosition(1, 1);
        ChessboardPosition b = new ChessboardPosition(3, 4);
        
        //when
        List<ChessboardPosition> result =  ChessboardControler.makePostionsLine(a, b);
        
        //then
        Assert.assertNull(result);
    }

}
