package pl.edu.agh.draughts.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pl.edu.agh.draughts.ai.RandomPlayer;
import pl.edu.agh.draughts.ai.SmartPlayer;
import pl.edu.agh.draughts.game.elements.Player;

public class NewGameCreator extends JFrame {
    private static final long serialVersionUID = 1L;
    public static String USER_PL = "User";
    public static String RANDOM_PL = "Random player";
    public static String SMART_PL = "Smart player";
    public static String[] players = { USER_PL, RANDOM_PL, SMART_PL };
    public JComboBox whitePlayer;
    public JComboBox blackPlayer;

    public NewGameCreator(String title) {
        super(title);
    }

    public static void createNewGame() {
        NewGameCreator newGameCreator = new NewGameCreator("New game");
        newGameCreator.setLayout(new BoxLayout(newGameCreator.getContentPane(), BoxLayout.Y_AXIS));


        JPanel whitePanel = new JPanel();
        whitePanel.add(new JLabel("White player: "));
        JComboBox whitePlayer = new JComboBox(players);
        whitePanel.add(whitePlayer);
        newGameCreator.whitePlayer = whitePlayer;
        newGameCreator.add(whitePanel);

        JPanel blackPanel = new JPanel();
        blackPanel.add(new JLabel("Black player: "));
        JComboBox blackPlayer = new JComboBox(players);
        newGameCreator.blackPlayer = blackPlayer;
        blackPanel.add(blackPlayer);
        newGameCreator.add(blackPanel);

        JPanel okPanel = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {

            private NewGameCreator newGameCreator;

            ActionListener init(NewGameCreator newGameCreator) {
                this.newGameCreator = newGameCreator;
                return this;
            }
            
            private Player createPlayer(String name) {
                if(name == USER_PL) {
                    return new UserPlayer();
                } else if(name == RANDOM_PL) {
                    return new RandomPlayer(100);
                } else if(name == SMART_PL) {
                    return new SmartPlayer(3, "src/main/resources/winner.txt");
                }
                return null;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        DraughtsMain.startNewGame(createPlayer((String) newGameCreator.whitePlayer.getSelectedItem()),
                                createPlayer((String) newGameCreator.blackPlayer.getSelectedItem()));
                    };
                });
                

                newGameCreator.setVisible(false);
                newGameCreator.dispose();
            }
        }.init(newGameCreator));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {

            private NewGameCreator newGameCreator;
            
            ActionListener init(NewGameCreator newGameCreator) {
                this.newGameCreator = newGameCreator;
                return this;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                newGameCreator.setVisible(false);
                newGameCreator.dispose();
                
            }
        }.init(newGameCreator));
        okPanel.add(okButton);
        okPanel.add(cancelButton);
        newGameCreator.add(okPanel);

        newGameCreator.pack();
        newGameCreator.setVisible(true);
    }
}
