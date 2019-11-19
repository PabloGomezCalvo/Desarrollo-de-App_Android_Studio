package gdv.ucm.logica;

import java.io.IOException;
import java.util.List;
import gdv.ucm.interfaces.Input;
import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public class MenuState implements State {

    public MenuState(LogicStateManager logicStateManager, int numColor){
        _logicStateManager = logicStateManager;
        _entityVector = new Entity[6];
        _numColor = numColor;
        _whiteFlash = true;
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
                            newImage("switchDashLogo.png"),new Rectangle(0,0,508,368)),
                    new Rectangle((1080/2)-(508/2),200,508 ,368));
            _entityVector[3] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("tapToPlay.png"),new Rectangle(0,0,506,72)),
                    new Rectangle((1080/2)-(506/2),800,506 ,72));
            _entityVector[4] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("buttons.png"),new Rectangle(140*2,0,140,140)),
                    new Rectangle(50,200,140 ,140));
            _entityVector[5] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("buttons.png"),new Rectangle(0,0,140,140)),
                    new Rectangle(890,200,140 ,140));

            int color = _logicStateManager.getGame().getGraphics().getColorSprite(_logicStateManager.getGame().getGraphics().
                            newImage("backgrounds.png"),32 * _numColor,0,32,32);

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
                ((EntityBackgroundArrows)_entityVector[1]).getColor());
        for(int i = 2; i < _entityVector.length;i++)
            _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[i].getImage(),
                    _entityVector[i].getRectOrigin(),_entityVector[i].getPosRectangle());

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


         List<Input.TouchEvent> inputStream = _logicStateManager.getGame().getInput().getTouchEvents();

        while(!inputStream.isEmpty()){
            Input.TouchEvent event = inputStream.get(0);
            inputStream.remove(0);
            if(event._eventType == Input.TouchEvent.EventType.Release
                    && event.y <= _entityVector[5]._rectFinal._y + _entityVector[5]._rectFinal._height && event.y >= _entityVector[5]._rectFinal._y
                    && event.x <= _entityVector[5]._rectFinal._x + _entityVector[5]._rectFinal._width && event.x >= _entityVector[5]._rectFinal._x)
            _logicStateManager.spawActiveState(1);


            else if(event._eventType == Input.TouchEvent.EventType.Release
                    && event.y <= _entityVector[1]._rectFinal._y + _entityVector[1]._rectFinal._height && event.y >= _entityVector[1]._rectFinal._y
                    && event.x <= _entityVector[1]._rectFinal._x + _entityVector[1]._rectFinal._width && event.x >= _entityVector[1]._rectFinal._x)
                _logicStateManager.spawActiveState(2);
            //TODO: TAP TO PLAY -> ESTADO.PLAY
        }
    }

    private boolean _whiteFlash;
    private int _numColor;
    private LogicStateManager _logicStateManager;
    private Entity _entityVector [];
}
