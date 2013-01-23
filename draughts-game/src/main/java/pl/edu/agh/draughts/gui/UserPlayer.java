package pl.edu.agh.draughts.gui;

import pl.edu.agh.draughts.game.elements.Player;

public class UserPlayer implements Player {

    @Override
    public boolean isUserControllable() {
        return true;
    }

}
