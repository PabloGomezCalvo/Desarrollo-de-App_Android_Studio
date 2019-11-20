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
        _game.getGraphics().clear(_clearColor[_cNum]);
        _activeState.render();
    }

    public void spawActiveState(int i){

        //ESTADO.MENU
        if(i == 0) {
            _activeState = new MenuState(this,_cNum);
            _activeState.init();
        }

        //ESTADO.INSTRUCCIONES
        else if(i == 1){
            _activeState = new MenuInstructions(this,_cNum);
            _activeState.init();
        }
        //ESTADO.PLAY
        else if(i == 2) {
            _activeState = new PlayState(this, _cNum);
            _activeState.init();
        }
        //ESTADO.GAMEOVER
        else if(i == 3) {
            _activeState = new GameOverState(this, _cNum,(((PlayState)_activeState).getPoints()));
            _activeState.init();
        }
        else
            _activeState = new MenuState(this, 6);
        _activeState.init();
    }



    private int _totalColors = 9;
    private int _cNum;
    private int[] _clearColor = { 0x41a85f, 0x00a885, 0x3d8eb9, 0x2969b0, 0x553982, 0x28324e, 0xf37934, 0xd14b41, 0x75706b};
    State _activeState;
    private Game _game;
}
