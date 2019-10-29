package gdv.ucm.logica;

import java.io.IOException;

import gdv.ucm.interfaces.Game;
import gdv.ucm.interfaces.Image;
import gdv.ucm.interfaces.StateManager;

public class LogicStateManager implements StateManager {


    public void init(Game game){
        _game = game;
        try {
            _balls = game.getGraphics().newImage("balls.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(long deltaTime) {

        x++;
    }

    @Override
    public void render() {
        _game.getGraphics().drawImage(_balls,x,0);
    }

    private int x;
    private Image _balls;
    private Game _game;
}
