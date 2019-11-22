package gdv.ucm.logica;

public abstract class State {

    public State(LogicStateManager logicStateManager){
        _logicStateManager = logicStateManager;
        _whiteFlash = true;
        _whiteFlashAlpha = 1.0f;
    }

    abstract void init();
    abstract void render();
    abstract void update(float deltaTime);

    protected int _numColor;
    protected LogicStateManager _logicStateManager;
    protected Entity _entityVector [];
    protected boolean _whiteFlash;
    protected float _whiteFlashAlpha;
}
