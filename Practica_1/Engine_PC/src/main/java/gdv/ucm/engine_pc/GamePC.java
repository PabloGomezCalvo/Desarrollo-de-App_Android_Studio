package gdv.ucm.engine_pc;

import javax.swing.JFrame;

import gdv.ucm.interfaces.Game;
import gdv.ucm.interfaces.GameLogic;
import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Input;

public class GamePC implements Game {

    public GamePC(int width, int height, GameLogic gameLogic){
        _gameLogic = gameLogic;
        _graphics = new GraphicsPC(width,height);
        _input = new InputPC();

        _window = new JFrame("Game SKRA");
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

        while(true){

            do{
                do{
                    java.awt.Graphics g = strategy.getDrawGraphics();
                    _graphics.setNewGraphics(g);
                    try{
                        _gameLogic.update();
                        _graphics.clear(255);
                        _gameLogic.render();
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
    private GameLogic _gameLogic;
    private JFrame _window;
}
