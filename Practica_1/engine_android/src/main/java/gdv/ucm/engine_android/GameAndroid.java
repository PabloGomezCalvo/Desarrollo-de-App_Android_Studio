package gdv.ucm.engine_android;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import gdv.ucm.interfaces.Game;
import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Input;
import gdv.ucm.interfaces.StateManager;
import gdv.ucm.utilities.Transform;

public class GameAndroid implements Game, Runnable {

    public GameAndroid(AssetManager assetManager, Context context, StateManager stateManager){
        _surfaceView = new SurfaceView(context);
        _graphics = new GraphicsAndroid(_surfaceView.getWidth(),_surfaceView.getHeight(), assetManager);
        _transform = new Transform(_graphics, _surfaceView.getWidth(), _surfaceView.getHeight());
        _input = new InputAndroid();
        _stateManager = stateManager;
    }


    public void pause(){
        _running = false;
        do{
            try {
                _thread.join();
                _thread = null;
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while(true);
    }

    public void resume(){
        if(!_running) {
            _running = true;
            _thread = new Thread(this);
            _thread.start();
        }
    }

    public void run(){
        SurfaceHolder holder = _surfaceView.getHolder();


        long lastFrameTime = System.nanoTime();
        while(_running){
            _graphics.setResolution(_surfaceView.getWidth(),_surfaceView.getHeight());
            _transform.changeResolutionRatio(_surfaceView.getWidth(),_surfaceView.getHeight());

            long currentTime = System.nanoTime();
            _stateManager.update((float)((currentTime-lastFrameTime)/1.0E9));
            lastFrameTime = currentTime;
            while(!holder.getSurface().isValid())
                ;
            Canvas canvas = holder.lockCanvas();
            _graphics.setNewCanvas(canvas);
            _stateManager.render();
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public SurfaceView get_surfaceView(){
        return _surfaceView;
    }

    @Override
    public Graphics getGraphics() {
        return _transform;
    }

    @Override
    public Input getInput() {
        return _input;
    }


    private Transform _transform;
    private Thread _thread;
    private StateManager _stateManager;
    private boolean _running = false;
    private GraphicsAndroid _graphics;
    private InputAndroid _input;
    private SurfaceView _surfaceView;
}
