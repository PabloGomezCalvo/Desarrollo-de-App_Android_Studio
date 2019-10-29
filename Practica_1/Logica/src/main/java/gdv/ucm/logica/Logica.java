package gdv.ucm.logica;

import java.io.IOException;

import gdv.ucm.interfaces.Game;
import gdv.ucm.interfaces.GameLogic;
import gdv.ucm.interfaces.Image;

public class Logica implements GameLogic {


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
