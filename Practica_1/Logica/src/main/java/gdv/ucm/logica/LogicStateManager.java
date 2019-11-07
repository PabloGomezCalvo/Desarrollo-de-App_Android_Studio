package gdv.ucm.logica;

import gdv.ucm.interfaces.Game;
import gdv.ucm.interfaces.StateManager;

public class LogicStateManager implements StateManager {

    public Game getGame(){
        return _game;
    }


    public void init(Game game){
        _cNum = (int)(Math.random()* (_totalColors-1));
        _game = game;
        _activeState = new MenuState(this,_cNum);
        _activeState.init();
    }

    @Override
    public void update(float deltaTime) {
        _activeState.update(deltaTime);
    }

    @Override
    public void render() {

        _game.getGraphics().clear(_clearColor);
        _activeState.render();
    }

    public void spawActiveState(int i){

        if(i == 0) {
            _activeState = new MenuState(this,_cNum);
            _activeState.init();
        }
        else
            _activeState = new MenuState(this,6);
            _activeState.init();
    }

    public void setClearColor(int c){
        _clearColor = c;
    }

    private int _totalColors = 9;
    private int _cNum;
    private int _clearColor;
    State _activeState;
    private Game _game;
}
