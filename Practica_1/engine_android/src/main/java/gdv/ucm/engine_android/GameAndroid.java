package gdv.ucm.engine_android;

import gdv.ucm.interfaces.Game;
import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Input;

public class GameAndroid implements Game {
    @Override
    public Graphics getGraphics() {
        return _graphics;
    }

    @Override
    public Input getInput() {
        return _input;
    }

    private GraphicsAndroid _graphics;
    private InputAndroid _input;
}
