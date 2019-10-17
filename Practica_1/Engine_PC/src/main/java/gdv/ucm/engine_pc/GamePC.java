package gdv.ucm.engine_pc;

import gdv.ucm.interfaces.Game;
import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Input;

public class GamePC implements Game {
    @Override
    public Graphics getGraphics() {
        return _graphics;
    }

    @Override
    public Input getInput() {
        return _input;
    }

    private Graphics _graphics;
    private InputPC _input;
}
