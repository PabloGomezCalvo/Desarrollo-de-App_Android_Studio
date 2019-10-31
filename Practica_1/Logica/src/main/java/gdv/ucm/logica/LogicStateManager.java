package gdv.ucm.logica;

import java.io.IOException;

import gdv.ucm.interfaces.Game;
import gdv.ucm.interfaces.Image;
import gdv.ucm.interfaces.StateManager;
import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public class LogicStateManager implements StateManager {


    public void init(Game game){
        _game = game;
        try {
            _balls = game.getGraphics().newImage("balls.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        _sprite = new Sprite(_balls,new Rectangle(0,125,125,150));

    }

    @Override
    public void update(float deltaTime) {
        /*alpha += 255 * deltaTime;
        if(alpha > 255)
            alpha = 0;*/
        //x += 50 * deltaTime;
        //System.out.println(alpha+ "\n");
    }

    @Override
    public void render() {
        _game.getGraphics().drawRectToRect(_balls,_sprite.get_rectTexture(),
                new Rectangle(100,100,200 ,200));
    }

    Sprite _sprite;
    private float x = 100;
    private float alpha = 0;
    private Image _balls;
    private Game _game;
}
