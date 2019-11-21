package gdv.ucm.logica;

import java.io.IOException;
import java.util.List;

import gdv.ucm.interfaces.Input;
import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public class GameOverState implements State {

    public GameOverState(LogicStateManager logicStateManager, int numColor, int points){
        _logicStateManager = logicStateManager;
        _entityVector = new Entity[15];
        _numColor = numColor;
        _whiteFlash = true;
        _points = points;
        _whiteFlashAlpha = 1.0f;
    }

    @Override
    public void init() {
        try {
            _entityVector[0] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("white.png"),new Rectangle(0,0,32,32)),
                    new Rectangle(0,0,1080 ,1920));
            _entityVector[2] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("gameOver.png"),new Rectangle(0,0,252,208)),
                    new Rectangle((1080/2)-(252/2),350,252 ,208));
            _entityVector[3] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("playAgain.png"),new Rectangle(0,0,532,72)),
                    new Rectangle((1080/2)-(532/2),1400,532 ,72));
            _entityVector[4] = new EntitySwapper(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("buttons.png"),new Rectangle(140*2,0,140,140)),
                    new Rectangle(50,200,140 ,140),0,false);
            _entityVector[5] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("buttons.png"),new Rectangle(0,0,140,140)),
                    new Rectangle(890,200,140 ,140));

            //POINTS
            _entityVector[6] = new Entity( //160 h y 125 w
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("scoreFont.png"),new Rectangle(0,160,125,160)),
                    new Rectangle((1080/2) - (100*3),1000,125 ,160));
            _entityVector[7] = new Entity( //160 h y 125 w
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("scoreFont.png"),new Rectangle(125*14,0,125,160)),
                    new Rectangle((1080/2)- (100*2),1000,125 ,160));
            _entityVector[8] = new Entity( //160 h y 125 w
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("scoreFont.png"),new Rectangle(125*8,0,125,160)),
                    new Rectangle((1080/2)- (100*1),1000,125 ,160));
            _entityVector[9] = new Entity( //160 h y 125 w
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("scoreFont.png"),new Rectangle(125*13,0,125,160)),
                    new Rectangle((1080/2),1000,125 ,160));
            _entityVector[10] = new Entity( //160 h y 125 w
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("scoreFont.png"),new Rectangle(125*4,160,125,160)),
                    new Rectangle((1080/2)+ 100,1000,125 ,160));
            _entityVector[11] = new Entity( //160 h y 125 w
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("scoreFont.png"),new Rectangle(125*3,160,125,160)),
                    new Rectangle((1080/2)+ (100*2),1000,125 ,160));

            //POINTS AS NUMBERS
            _entityVector[12] = new Entity( //160 h y 125 w
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("scoreFont.png"),new Rectangle(0,0,125,160)),
                    new Rectangle((1080/2) - 150,800,125 ,160));
            _entityVector[13] = new Entity( //160 h y 125 w
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("scoreFont.png"),new Rectangle(0,0,125,160)),
                    new Rectangle((1080/2) - 50,800,125 ,160));
            _entityVector[14] = new Entity( //160 h y 125 w
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("scoreFont.png"),new Rectangle(0,0,125,160)),
                    new Rectangle((1080/2) + 50,800,125 ,160));


            int color = _logicStateManager.getGame().getGraphics().getColorSprite(_logicStateManager.getGame().getGraphics().
                    newImage("backgrounds.png"),32 * _numColor,0,32,32);

            _entityVector[1] = new EntityBackgroundArrows(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("arrowsBackground.png"),new Rectangle(0,1150,676,1920)),
                    new Rectangle((1080/2)-(676/2),0,676 ,1920),color);

        } catch (IOException e) {
            e.printStackTrace();
        }

        checkNumber();

    }

    @Override
    public void render() {

        _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[1].getImage(),
                _entityVector[1].getRectOrigin(), _entityVector[1].getPosRectangle(),
                ((EntityBackgroundArrows) _entityVector[1]).getColor());
        for (int i = 2; i < _entityVector.length; i++)
            if (_entityVector[i].getPosX() != -1)
                _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[i].getImage(),
                        _entityVector[i].getRectOrigin(), _entityVector[i].getPosRectangle());

        if (_whiteFlash) {
            if (_whiteFlashAlpha <= 0.0f)
                _whiteFlash = false;
            _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[0].getImage(),
                    _entityVector[0].getRectOrigin(), _entityVector[0].getPosRectangle(), _whiteFlashAlpha);
            _whiteFlashAlpha -= 0.025;
        }
    }

    @Override
    public void update(float deltaTime) {
        _entityVector[1].moveSurfaceImage(_entityVector[0].getPosImgX(),
                _entityVector[1].getPosImgY() - 384 * deltaTime); //muevo el fondoDeArrows

        if(!_whiteFlash) {

            List<Input.TouchEvent> inputStream = _logicStateManager.getGame().getInput().getTouchEvents();

            boolean selected = false;

            while (!inputStream.isEmpty()) {
                Input.TouchEvent event = inputStream.get(0);
                inputStream.remove(0);
                if (event._eventType == Input.TouchEvent.EventType.Release
                        && event.y <= _entityVector[5]._rectFinal._y + _entityVector[5]._rectFinal._height && event.y >= _entityVector[5]._rectFinal._y
                        && event.x <= _entityVector[5]._rectFinal._x + _entityVector[5]._rectFinal._width && event.x >= _entityVector[5]._rectFinal._x)
                    _logicStateManager.spawActiveState(1);

                    //TODO: TAP TO PLAY -> ESTADO.PLAY EN VEZ DE AL ESTADO.MENU

                else if (event._eventType == Input.TouchEvent.EventType.Release
                        && event.y <= _entityVector[1]._rectFinal._y + _entityVector[1]._rectFinal._height && event.y >= _entityVector[1]._rectFinal._y
                        && event.x <= _entityVector[1]._rectFinal._x + _entityVector[1]._rectFinal._width && event.x >= _entityVector[1]._rectFinal._x)
                    _logicStateManager.spawActiveState(2);

                else if (event._eventType == Input.TouchEvent.EventType.Release && !selected
                        && event.y <= _entityVector[4]._rectFinal._y + _entityVector[4]._rectFinal._height && event.y >= _entityVector[4]._rectFinal._y
                        && event.x <= _entityVector[4]._rectFinal._x + _entityVector[4]._rectFinal._width && event.x >= _entityVector[4]._rectFinal._x) {
                    ((EntitySwapper) _entityVector[4]).swapper();
                    selected = true;
                }
            }
        }
    }

    private void checkNumber(){
        if(_points > 99){
            int a = _points % 10;
            int b = (_points/10)%10;
            int c = (_points/100)%10;
            putNumberFont(12,c);
            putNumberFont(13,b);
            putNumberFont(14,a);
        }
        else if(_points > 9) {
            _entityVector[12].moveEntity(-1, _entityVector[12].getPosY());
            _entityVector[13].moveEntity((1080 / 2) - 100, _entityVector[12].getPosY());
            _entityVector[14].moveEntity((1080 / 2), _entityVector[12].getPosY());
            int a = _points % 10;
            int b = ((_points - a) / 10) % 10;
            putNumberFont(13, b);
            putNumberFont(14, a);
        }
        else {
            _entityVector[12].moveEntity(-1, _entityVector[12].getPosY());
            _entityVector[13].moveEntity(-1, _entityVector[12].getPosY());
            _entityVector[14].moveEntity((1080 / 2) - 50, _entityVector[12].getPosY());
            putNumberFont(14, _points);
        }


    }

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

    private int _points;
    private boolean _whiteFlash;
    private float _whiteFlashAlpha;
    private int _numColor;
    private LogicStateManager _logicStateManager;
    private Entity _entityVector [];

    // private Entity _caracteres []
}
