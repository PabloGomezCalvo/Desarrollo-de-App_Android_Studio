package gdv.ucm.engine_pc;

import javax.swing.JFrame;

import gdv.ucm.interfaces.Game;
import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Input;
import gdv.ucm.interfaces.StateManager;

public class GamePC implements Game {

    public GamePC(int width, int height, StateManager stateManager){
        _stateManager = stateManager;
        _graphics = new GraphicsPC(width,height);
        _input = new InputPC(_graphics);
        _window = new JFrame("Switch Dash Game");
        _window.addMouseListener(_input);
        _window.setSize(width,  height);
    }

    public void init(){

        _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _window.setIgnoreRepaint(true);
        _window.setVisible(true);


        int tries = 100;
        do{
            try{
                _window.createBufferStrategy(2);
                break;
            }
            catch(Exception e){
            }
        }while(tries-- > 0);

        if(tries == 0){
            System.err.println("Couldnt initialize the window");
            return;
        }
    }

    public void run(){
        java.awt.image.BufferStrategy strategy;
        strategy = _window.getBufferStrategy();
        long lastFrameTime = System.nanoTime();
        while(true){
            _graphics.changeResolutionRatio(_window.getWidth(),_window.getHeight());
            _graphics.setResolution(_window.getWidth(),_window.getHeight());
            long currentTime = System.nanoTime();
            _stateManager.update((float)((currentTime-lastFrameTime)/1.0E9));
            lastFrameTime = currentTime;
            do{
                do{
                    java.awt.Graphics g = strategy.getDrawGraphics();
                    _graphics.setNewGraphics(g);
                    try{
                        _stateManager.render();
                    }catch (Exception e){

                    }
                    finally{
                        g.dispose();
                    }
                }while(strategy.contentsRestored());
                strategy.show();

            }while(strategy.contentsLost());
        }
    }

    @Override
    public Graphics getGraphics() {
        return _graphics;
    }

    @Override
    public Input getInput() {
        return _input;
    }

    private GraphicsPC _graphics;
    private InputPC _input;
    private StateManager _stateManager;
    private JFrame _window;
}
