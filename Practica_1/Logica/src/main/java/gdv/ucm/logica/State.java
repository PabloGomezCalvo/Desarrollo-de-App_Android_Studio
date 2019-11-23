/**

    Clase abstracta para los diferentes estados los juego.

*/

package gdv.ucm.logica;

public abstract class State {

    public State(LogicStateManager logicStateManager){
        _logicStateManager = logicStateManager;
        _whiteFlash = true;
        _whiteFlashAlpha = 1.0f;
    }
/**
    Inicializador del estado.
*/
    abstract void init();
/**
    Render de las "Entidades" de cada estado.
*/
    abstract void render();
/**
    Lógica que siguen las entidades que forman parte del estado del juego.
*/
    abstract void update(float deltaTime);
/**
    Color del fondo que va cambiando en cada estado
*/
    protected int _numColor;
/**
    Manager que se encarga de inicializar recursos, hacer cambios de estados y llamar a cada update y render de cada uno de los estados.
*/
    protected LogicStateManager _logicStateManager;
/**
    Vector de las entidades que tiene cada estado.
*/
    protected Entity _entityVector [];
/**
    Variables que controlan la animación de flash al principio de cada estado.
*/
    protected boolean _whiteFlash;
    protected float _whiteFlashAlpha;
}
