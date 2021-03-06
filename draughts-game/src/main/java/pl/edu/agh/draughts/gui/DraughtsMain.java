package pl.edu.agh.draughts.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import pl.edu.agh.draughts.ai.AIPlayer;
import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Player;

public class DraughtsMain {
    
    private static DraughtsEngine draughtsEngine = new DraughtsEngine();
    private static ChessboardPanel chessboard;
    private static JFrame frame;
    private static JLabel statusLabel;

    private static void tryToSetSystemLookAndFeel() {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    private static JFrame createNewFrame() {
        frame = new JFrame("Draughts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JLayeredPane());
        return frame;
    }
    
    private static void addChessboard(JFrame frame) {
        chessboard = new ChessboardPanel(Chessboard.CHESSBOARD_SIZE, 50);
        frame.getContentPane().add(chessboard);
    }
    
    private static void addInformationPanel(JFrame frame) {
        JPanel infoPanel = new JPanel();
        frame.getContentPane().add(infoPanel, BorderLayout.EAST);
        infoPanel.setPreferredSize(new Dimension(200, frame.getHeight()));
    }
    
    private static void addMenuBar(JFrame frame) {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("New");
        newGame.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                NewGameCreator.createNewGame();
            }
        });
        JMenuItem end = new JMenuItem("End");
        end.addActionListener(new ActionListener() {
            
           private JFrame frame;
           
           public ActionListener init(JFrame frame) {
               this.frame = frame;
               return this;
           }
           
           @Override
            public void actionPerformed(ActionEvent e) {
               int choice = JOptionPane.showConfirmDialog(frame, "Do you really want to exit?",
                       "Exit", JOptionPane.YES_NO_OPTION);
               if(choice == JOptionPane.YES_OPTION) {
                   System.exit(0);
               }
                
            } 
        }.init(frame));
        gameMenu.add(newGame);
        gameMenu.add(new JSeparator());
        gameMenu.add(end);
        jMenuBar.add(gameMenu);
        frame.setJMenuBar(jMenuBar);
    }
    
    private static void addStatusBar(JFrame frame) {
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        frame.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        statusLabel = new JLabel("");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);
    }
    
    private static void showFrame(JFrame frame) {
        frame.pack();
        frame.setVisible(true);
    }
    
    private static void createAndAddMouseListenersAndControler(boolean isWhiteControllable, boolean isBlackControllable) {
        ChessboardMouseListener chessboardMouseListener = new ChessboardMouseListener(isWhiteControllable,
                isBlackControllable);
        chessboard.addMouseListener(chessboardMouseListener);
        chessboard.addMouseMotionListener(chessboardMouseListener);
        chessboardMouseListener.setChessboardPanel(chessboard);
        
        ChessboardControler chessboardControler = new ChessboardControler();
        chessboardMouseListener.setChessboardControler(chessboardControler);
        chessboardControler.setChessboardPanel(chessboard);
        chessboardControler.setDraughtsEngine(draughtsEngine);
    }
    
    private static void createAndShowGUI() {
        tryToSetSystemLookAndFeel();
        JFrame frame = createNewFrame();
        addChessboard(frame);
        //addInformationPanel(frame);
        addMenuBar(frame);
        addStatusBar(frame);
        showFrame(frame);
    }
    
    public static void setStatusBarText(String message) {
        statusLabel.setText(message);
    }
    
    public static void startNewGame(Player white, Player black) {
        chessboard.setVisible(false);
        chessboard.clearPieces();
        if (white instanceof AIPlayer) {
            draughtsEngine.setWhitePlayer((AIPlayer) white);
        } else {
            draughtsEngine.setWhitePlayer(null);
        }
        if (black instanceof AIPlayer) {
            draughtsEngine.setBlackPlayer((AIPlayer) black);
        } else {
            draughtsEngine.setBlackPlayer(null);
        }
        draughtsEngine.initializeGame();
        chessboard.addPieces(draughtsEngine.getChessboard().getChessboardTable());
        chessboard.setVisible(true);
        createAndAddMouseListenersAndControler(white.isUserControllable(), black.isUserControllable());
        showFrame(frame);
        draughtsEngine.tryToMoveAutomatically();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });

    }

}
