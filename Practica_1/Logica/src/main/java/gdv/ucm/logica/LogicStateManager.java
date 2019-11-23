/**
    Clase Manager de la lógica que tiene como proposito la inicialización de los recursos, cambiar entre estados y llamar al render y update de las entidades de los estados.
*/

package gdv.ucm.logica;


import java.io.IOException;

import gdv.ucm.interfaces.Game;
import gdv.ucm.interfaces.Image;
import gdv.ucm.interfaces.StateManager;

public class LogicStateManager implements StateManager {
/**
    @return referencia al juego.
*/
    public Game getGame(){
        return _game;
    }

/**
    Inicialización de los recursos, color de fondo y carga del primer estado.
*/
    public void init(Game game){
        _images = new Image[13];
        _game = game;
        _cNum = (int)(Math.random()* (_totalColors-1));

        try {
            _images[0] = _game.getGraphics().newImage("white.png");
            _images[1] = _game.getGraphics().newImage("backgrounds.png");
            _images[2] = _game.getGraphics().newImage("arrowsBackground.png");
            _images[3] = _game.getGraphics().newImage("howToPlay.png");
            _images[4] = _game.getGraphics().newImage("tapToPlay.png");
            _images[5] = _game.getGraphics().newImage("instructions.png");
            _images[6] = _game.getGraphics().newImage("buttons.png");
            _images[7] = _game.getGraphics().newImage("gameOver.png");
            _images[8] = _game.getGraphics().newImage("playAgain.png");
            _images[9] = _game.getGraphics().newImage("scoreFont.png");
            _images[10] = _game.getGraphics().newImage("switchDashLogo.png");
            _images[11] = _game.getGraphics().newImage("players.png");
            _images[12] = _game.getGraphics().newImage("balls.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        _activeState = new MenuState(this,_cNum);
        _activeState.init();
    }
/**
    Update del estado activo.
*/
    @Override
    public void update(float deltaTime) {
        _activeState.update(deltaTime);
    }
/**
    Render y clear del estado activo.
*/
    @Override
    public void render() {
        _game.getGraphics().clear(_clearColor[_cNum]);
        _activeState.render();
    }
/**
    Hace el cambio entre estados.
    @param i Estado al que quiere cambiarse

*/
    public void spawActiveState(int i){

        //ESTADO.MENU
        if(i == 0)
            _activeState = new MenuState(this,_cNum);
        //ESTADO.INSTRUCCIONES
        else if(i == 1)
            _activeState = new MenuInstructions(this,_cNum);
        //ESTADO.PLAY
        else if(i == 2)
            _activeState = new PlayState(this);
        //ESTADO.GAMEOVER
        else if(i == 3)
            _activeState = new GameOverState(this, _cNum,(((PlayState)_activeState).getPoints()));
        else
            _activeState = new MenuState(this, 6);

        _activeState.init();
    }
/**
    @param index imagen de la que se quiere hacer get.
    @return imagen del vector de imagenes cargadas.

*/
    public Image getImage(int index){
        return _images[index];
    }

/**
    @return color aleatorio para el fondo.

*/
    public int swapColor(){
        _cNum = (int)(Math.random()* (_totalColors-1));
        return _cNum;
    }

    private Image[] _images;
    private int _totalColors = 9;
    private int _cNum;
    private int[] _clearColor = { 0x41a85f, 0x00a885, 0x3d8eb9, 0x2969b0, 0x553982, 0x28324e, 0xf37934, 0xd14b41, 0x75706b};
    private State _activeState;
    private Game _game;
}
