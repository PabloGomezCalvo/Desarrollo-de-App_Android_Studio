package gdv.ucm.engine_android;

import android.content.res.AssetManager;

import gdv.ucm.interfaces.Game;
import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Input;

public class GameAndroid implements Game {

    public GameAndroid(int width, int height, AssetManager assetManager){
        _width = width;
        _height = height;
        _graphics = new GraphicsAndroid(width,height, assetManager);
        _input = new InputAndroid();
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
    private GraphicsAndroid _graphics;
    private InputAndroid _input;
}
