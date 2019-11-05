package gdv.ucm.logica;

import gdv.ucm.interfaces.Game;
import gdv.ucm.interfaces.StateManager;

public class LogicStateManager implements StateManager {

    public Game getGame(){
        return _game;
    }


    public void init(Game game){
        _game = game;
        _activeState = new MenuState(this);
        _activeState.init();
    }

    @Override
    public void update(float deltaTime) {
        _activeState.update(deltaTime);
    }

    @Override
    public void render() {
        _activeState.render();
    }

    public void spawActiveState(int i){

        if(i != 0) {
            _activeState = new MenuState(this);
            _activeState.init();
        }
    }

    State _activeState;
    private Game _game;
}
