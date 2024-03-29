package gdv.ucm.logica;

import java.io.IOException;
import java.util.List;
import gdv.ucm.interfaces.Input;
import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public class PlayState extends State {

    public PlayState(LogicStateManager logicStateManager){
        super(logicStateManager);
        _numColor = _logicStateManager.swapColor();
        _entityVector = new Entity[11];
        _points = 0;
        _totalBalls = 5;
        _gameOver = false;
        _ballsVel = 430;
        _ballsStartVel = 430;
        _ballsStartPosX = (1080/2) - (128/2);
        _ballActive = 3;
        _ballStartVector = 3;
        _ballSeparation = 395;
    }
/**
    
    Inicialización de las Entidades del estado.

*/
    @Override
    public void init() {

        _entityVector[0] = new Entity(
                new Sprite(_logicStateManager.getImage(0), new Rectangle(0, 0, 32, 32)),
                new Rectangle(0, 0, 1080, 1920));

        _entityVector[2] = new EntitySwapper(
                new Sprite(_logicStateManager.getImage(11), new Rectangle(0, 0, 528, 192)),
                new Rectangle((1080 / 2) - (528 / 2), 1200, 528, 192), 0, true);

        _entityVector[3] = new EntitySwapper(
                new Sprite(_logicStateManager.getImage(12), new Rectangle(0, 0, 128, 128)),
                new Rectangle(_ballsStartPosX, 0, 128, 128), 0, true);
        _entityVector[4] = new EntitySwapper(
                new Sprite(_logicStateManager.getImage(12), new Rectangle(0, 0, 128, 128)),
                new Rectangle(-1, 0, 128, 128), 0, true);
        _entityVector[5] = new EntitySwapper(
                new Sprite(_logicStateManager.getImage(12), new Rectangle(0, 0, 128, 128)),
                new Rectangle(-1, 0, 128, 128), 0, true);
        _entityVector[6] = new EntitySwapper(
                new Sprite(_logicStateManager.getImage(12), new Rectangle(0, 0, 128, 128)),
                new Rectangle(-1, 0, 128, 128), 0, true);
        _entityVector[7] = new EntitySwapper(
                new Sprite(_logicStateManager.getImage(12), new Rectangle(0, 0, 128, 128)),
                new Rectangle(-1, 0, 128, 128), 0, true);

        //POINTS AS NUMBERS

        _entityVector[8] = new Entity( //160 h y 125 w
                new Sprite(_logicStateManager.getImage(9), new Rectangle(0, 0, 125, 160)),
                new Rectangle(780, 200, 125, 160));
        _entityVector[9] = new Entity( //160 h y 125 w
                new Sprite(_logicStateManager.getImage(9), new Rectangle(0, 0, 125, 160)),
                new Rectangle(840, 200, 125, 160));
        _entityVector[10] = new Entity( //160 h y 125 w
                new Sprite(_logicStateManager.getImage(9), new Rectangle(0, 0, 125, 160)),
                new Rectangle(900, 200, 125, 160));

        int color = _logicStateManager.getGame().getGraphics().getColorSprite(_logicStateManager.getImage(1),
                32 * _numColor, 0, 32, 32);

        _entityVector[1] = new EntityBackgroundArrows(
                new Sprite(_logicStateManager.getImage(2), new Rectangle(0, 1150, 676, 1920)),
                new Rectangle((1080 / 2) - (676 / 2), 0, 676, 1920), color);


        _animation = new Animation(new Sprite(_logicStateManager.getImage(12),
                new Rectangle(0, 0, 128, 128)), (1080 / 2) - 25, 1150, 1.0f);


        checkNumber();

    }
/**
    
    Dibujado de las entidades del Estado.

*/
    @Override
    public void render() {

        _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[1].getImage(),
                _entityVector[1].getRectOrigin(), _entityVector[1].getPosRectangle(),
                ((EntityBackgroundArrows) _entityVector[1]).getColor());//arrows
        if (!_gameOver) {
            _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[2].getImage(),
                    _entityVector[2].getRectOrigin(), _entityVector[2].getPosRectangle());//player

            //BALLS PAINT
            for (int i = _ballStartVector; i < _totalBalls; i++) {
                if (_entityVector[i].getPosX() != -1)
                    _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[i].getImage(),
                            _entityVector[i].getRectOrigin(), _entityVector[i].getPosRectangle());//ball
            }
        }
        //POINTS PAINT
        for (int i = _totalBalls; i < _entityVector.length; i++)
            if (_entityVector[i].getPosX() != -1)
                _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[i].getImage(),
                        _entityVector[i].getRectOrigin(), _entityVector[i].getPosRectangle());


        if (_animation.isActive())
            for (int i = 0; i < _animation.getTotalBalls(); i++) {
                _logicStateManager.getGame().getGraphics().drawRectToRect(_animation.getSprite().get_img(),
                        _animation.getSprite().get_rectTexture(), _animation.getFinalRect(i),
                        _animation.getAlpha());
            }

        if (_whiteFlash) {
            if (_whiteFlashAlpha <= 0.0f)
                _whiteFlash = false;
            _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[0].getImage(),
                    _entityVector[0].getRectOrigin(), _entityVector[0].getPosRectangle(), _whiteFlashAlpha);
            _whiteFlashAlpha -= 0.025;
        }
    }
/**
    
    Lógica de cada Entidad del estado.

*/
    @Override
    public void update(float deltaTime) {

        _entityVector[1].moveSurfaceImage(_entityVector[1].getPosImgX(),
                _entityVector[1].getPosImgY() - 384 * deltaTime); //muevo el fondoDeArrows

        if(!_whiteFlash) {

            ballsFalling(); // check if we need to throw another ball, if so then do it

            for (int i = _ballStartVector; i < _ballStartVector + _totalBalls; i++) { // MOVE ACTIVES BALLS
                if (_entityVector[i].getPosRectangle()._x != -1)
                    _entityVector[i].moveEntity(_entityVector[i].getPosX(), _entityVector[i].getPosY() + _ballsVel * deltaTime);
            }
            if (!_gameOver) {
                checkInput();
                for (int i = _ballStartVector; i < _ballStartVector + _totalBalls; i++) {//collision with balls
                    if (checkCollision(_entityVector[2].getPosRectangle(), _entityVector[i].getPosRectangle())) {
                        if (((EntitySwapper) _entityVector[2]).getMode() == ((EntitySwapper) _entityVector[i]).getMode()) {
                            _points++;
                            checkNumber();
                            _animation.PutColor(((EntitySwapper) _entityVector[i]).getMode());
                            _animation.resetAnimation();
                            _ballsVel = _ballsStartVel + (_points / 10) * 90;
                        } else {
                            _animation.PutColor(((EntitySwapper) _entityVector[2]).getMode());
                            _animation.resetAnimation();
                            _gameOver = true;
                        }
                        _entityVector[i].moveEntity(-1, 0);
                    }
                }
            }
            if (_animation.isActive()) {
                _animation.simulate();
            } else if (!_animation.isActive() && _gameOver)
                _logicStateManager.spawActiveState(3);

        }
    }

    public int getPoints(){
        return _points;
    }
/**
    Comprueba los eventos generados y en el caso de que se pulse en la zona destinada al "jugador", hace el swap de color del jugador.
*/
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
/**
    Comprueba la colisión entre 2 entidades.
    @param one rectángulo de la entidad 1
    @param other rectángulo de la entidad 2

*/
    private boolean checkCollision(Rectangle one, Rectangle other){

        if(one._x < other._x + other._width &&
                one._x + one._width > other._x &&
                one._y < other._y + other._height &&
                one._height + one._y > other._y)
            return true;
        return false;
    }
/**
    En el caso de que la separación entre bolas me permita "generar" otra bola,
    cogemos la siguiente bola que debería caer, la activa y la pone en movimiento.

*/
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
/**
    
    Coloca los números de tal manera para que estén centrados en el caso de sean de 1,2 o 3 cifras.

*/
    private void checkNumber(){
        if(_points > 99){
            _entityVector[8].moveEntity(780,_entityVector[8].getPosY());
            int a = _points % 10;
            int b = (_points/10)%10;
            int c = (_points/100)%10;
            putNumberFont(8,c);
            putNumberFont(9,b);
            putNumberFont(10,a);
        }
        else if(_points > 9) {
            _entityVector[8].moveEntity(-1, _entityVector[8].getPosY());
            _entityVector[9].moveEntity(840, _entityVector[9].getPosY());
            int a = _points % 10;
            int b = ((_points - a) / 10) % 10;
            putNumberFont(9, b);
            putNumberFont(10, a);
        }
        else {
            _entityVector[8].moveEntity(-1, _entityVector[8].getPosY());
            _entityVector[9].moveEntity(-1, _entityVector[9].getPosY());
            putNumberFont(10, _points);

        }

    }
/**
    
    Valor del rectángulo de textura de cada uno de los números.

*/
    private void putNumberFont(int index, int valor){
        switch (valor){
            case 0:
                _entityVector[index].getRectOrigin()._x = 7 * 125;
                _entityVector[index].getRectOrigin()._y = 3 * 160;
                break;
            case 1:
                _entityVector[index].getRectOrigin()._x = 8 * 125;
                _entityVector[index].getRectOrigin()._y = 3 * 160;
                break;
            case 2:
                _entityVector[index].getRectOrigin()._x = 9 * 125;
                _entityVector[index].getRectOrigin()._y = 3 * 160;
                break;
            case 3:
                _entityVector[index].getRectOrigin()._x = 10 * 125;
                _entityVector[index].getRectOrigin()._y = 3 * 160;
                break;
            case 4:
                _entityVector[index].getRectOrigin()._x = 11 * 125;
                _entityVector[index].getRectOrigin()._y = 3 * 160;
                break;
            case 5:
                _entityVector[index].getRectOrigin()._x = 12 * 125;
                _entityVector[index].getRectOrigin()._y = 3 * 160;
                break;
            case 6:
                _entityVector[index].getRectOrigin()._x = 13 * 125;
                _entityVector[index].getRectOrigin()._y = 3 * 160;
                break;
            case 7:
                _entityVector[index].getRectOrigin()._x = 14 * 125;
                _entityVector[index].getRectOrigin()._y = 3 * 160;
                break;
            case 8:
                _entityVector[index].getRectOrigin()._x = 0;
                _entityVector[index].getRectOrigin()._y = 4 * 160;
                break;
            case 9:
                _entityVector[index].getRectOrigin()._x = 125;
                _entityVector[index].getRectOrigin()._y = 4 * 160;
                break;
            default:
                break;
        }
    }

    private Animation _animation;
    private int _ballStartVector;
    private int _ballSeparation;
    private int _ballActive;
    private int _ballsStartPosX;
    private int _ballsVel;
    private int _ballsStartVel;
    private boolean _gameOver;
    private int _totalBalls;
    private int _points;
}
