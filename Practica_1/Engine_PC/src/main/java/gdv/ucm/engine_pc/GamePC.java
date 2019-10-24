package gdv.ucm.engine_pc;

import gdv.ucm.interfaces.Game;
import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Input;

public class GamePC implements Game {

    public GamePC(int width, int height){
        _height = height;
        _width = width;
        _graphics = new GraphicsPC(width,height);
        _input = new InputPC();
    }

    public void init(){

    }

    public void run(){

    }

    @Override
    public Graphics getGraphics() {
        return _graphics;
    }

    @Override
    public Input getInput() {
        return _input;
    }

    private int _width;
    private int _height;
    private Graphics _graphics;
    private InputPC _input;
}
