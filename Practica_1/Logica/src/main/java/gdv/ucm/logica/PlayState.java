package gdv.ucm.logica;

import java.io.IOException;
import java.util.List;

import gdv.ucm.interfaces.Input;
import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public class PlayState implements State {

    public PlayState(LogicStateManager logicStateManager, int numColor){
        _logicStateManager = logicStateManager;
        _numColor = numColor;
        _whiteFlash = true;
        _entityVector = new Entity[9];
        _points = 0;
        _totalBalls = 5;
        _gameOver = false;
        _ballsVel = 430;
        _ballsStartVel = 430;
        _ballsStartPosX = (1080/2) - (128/2);
        _ballActive = 4;
        _ballStartVector = 4;
        _ballSeparation = 395;
    }

    @Override
    public void init() {
        try {
            _entityVector[0] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("white.png"),new Rectangle(0,0,32,32)),
                    new Rectangle(0,0,1080 ,1920));

            _entityVector[2] = new EntitySwapper(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("players.png"),new Rectangle(0,0,528,192)),
                    new Rectangle((1080/2)-(528/2),1200,528 ,192),0);
            _entityVector[3] = new EntitySwapper(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("scoreFont.png"),new Rectangle(0,0,528,192)),
                    new Rectangle((1080/2)-(528/2),1200,528 ,192),0);

            _entityVector[4] = new EntitySwapper(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("balls.png"),new Rectangle(0,0,128,128)),
                    new Rectangle(_ballsStartPosX,0,128 ,128),0);
            _entityVector[5] = new EntitySwapper(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("balls.png"),new Rectangle(0,0,128,128)),
                    new Rectangle(-1,0,128 ,128),0);
            _entityVector[6] = new EntitySwapper(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("balls.png"),new Rectangle(0,0,128,128)),
                    new Rectangle(-1,0,128 ,128),0);
            _entityVector[7] = new EntitySwapper(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("balls.png"),new Rectangle(0,0,128,128)),
                    new Rectangle(-1,0,128 ,128),0);
            _entityVector[8] = new EntitySwapper(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("balls.png"),new Rectangle(0,0,128,128)),
                    new Rectangle(-1,0,128 ,128),0);
            int color = _logicStateManager.getGame().getGraphics().getColorSprite(_logicStateManager.getGame().getGraphics().
                    newImage("backgrounds.png"), 32 * _numColor,0,32,32);

            _entityVector[1] = new EntityBackgroundArrows(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("arrowsBackground.png"),new Rectangle(0,1150,676,1920)),
                    new Rectangle((1080/2)-(676/2),0,676 ,1920),color);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[1].getImage(),
                _entityVector[1].getRectOrigin(),_entityVector[1].getPosRectangle(),
                ((EntityBackgroundArrows)_entityVector[1]).getColor());//arrows
        _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[2].getImage(),
                _entityVector[2].getRectOrigin(),_entityVector[2].getPosRectangle());//player

        for(int i = _ballStartVector; i < _ballStartVector + _totalBalls;i++){
            if(_entityVector[i].getPosRectangle()._x != -1)
                _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[i].getImage(),
                        _entityVector[i].getRectOrigin(),_entityVector[i].getPosRectangle());//ball
        }

        if(_whiteFlash){
            _whiteFlash = false;
            _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[0].getImage(),
                    _entityVector[0].getRectOrigin(), _entityVector[0].getPosRectangle());
        }
    }

    @Override
    public void update(float deltaTime) {
        _entityVector[1].moveSurfaceImage(_entityVector[1].getPosImgX(),
                _entityVector[1].getPosImgY() - 384 * deltaTime); //muevo el fondoDeArrows

        checkInput();
        ballsFalling(); // check if we need to throw another ball, if so then do it

        for(int i = _ballStartVector; i < _ballStartVector + _totalBalls;i++) { // MOVE ACTIVES BALLS
            if (_entityVector[i].getPosRectangle()._x != -1)
                _entityVector[i].moveEntity(_entityVector[i].getPosX(), _entityVector[i].getPosY() + _ballsVel * deltaTime);
        }

        for(int i = 3; i < 3 + _totalBalls;i++){//collision with balls
            if(checkCollision(_entityVector[2].getPosRectangle(),_entityVector[i].getPosRectangle())) {
                if (((EntitySwapper) _entityVector[2]).getMode() == ((EntitySwapper) _entityVector[i]).getMode()) {
                    _points++;
                    _ballsVel = _ballsStartVel + (_points/10) * 90;
                } else
                    _gameOver = true;

                _entityVector[i].moveEntity(-1,0);
            }
        }

        if(_gameOver){
            _logicStateManager.spawActiveState(3);
        }

    }

    public int getPoints(){
        return _points;
    }

    private void checkInput(){
        List<Input.TouchEvent> inputStream = _logicStateManager.getGame().getInput().getTouchEvents();
        boolean swapped = false;
        while(!inputStream.isEmpty()){
            Input.TouchEvent event = inputStream.get(0);
            inputStream.remove(0);
            if(event._eventType == Input.TouchEvent.EventType.Release && !swapped
                    && event.y <= _entityVector[1]._rectFinal._y + _entityVector[1]._rectFinal._height && event.y >= _entityVector[1]._rectFinal._y
                    && event.x <= _entityVector[1]._rectFinal._x + _entityVector[1]._rectFinal._width && event.x >= _entityVector[1]._rectFinal._x) {
                ((EntitySwapper) _entityVector[2]).swapper();
                swapped = true;
            }
        }
    }

    private boolean checkCollision(Rectangle one, Rectangle other){

        if(one._x < other._x + other._width &&
                one._x + one._width > other._x &&
                one._y < other._y + other._height &&
                one._height + one._y > other._y)
            return true;
        return false;
    }

    private void ballsFalling(){
        if(_entityVector[_ballActive].getPosY() > _ballSeparation){
            boolean selected = false;
            int i = _ballStartVector;
            while(!selected && i < _ballStartVector + _totalBalls){

                if(_entityVector[i].getPosX() == -1){
                    selected = true;
                    _entityVector[i].moveEntity(_ballsStartPosX,0);
                    ((EntitySwapper) _entityVector[i]).setMode(((EntitySwapper) _entityVector[_ballActive]).getMode());
                    if((int)(Math.random()* (100-1)) >= 70)
                        ((EntitySwapper) _entityVector[i]).swapper();
                    _ballActive = i;
                }

                i++;
            }
        }
    }

    private int _ballStartVector;
    private int _ballSeparation;
    private int _ballActive;
    private int _ballsStartPosX;
    private int _ballsVel;
    private int _ballsStartVel;
    private boolean _gameOver;
    private int _totalBalls;
    private int _points;
    private boolean _whiteFlash;
    private int _numColor;
    private LogicStateManager _logicStateManager;
    private Entity _entityVector [];
}
