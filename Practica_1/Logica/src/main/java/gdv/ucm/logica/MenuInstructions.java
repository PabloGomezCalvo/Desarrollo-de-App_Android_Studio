package gdv.ucm.logica;

import java.io.IOException;
import java.util.List;

import gdv.ucm.interfaces.Input;
import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public class MenuInstructions implements State {

    public MenuInstructions(LogicStateManager logicStateManager, int numColor){
        _logicStateManager = logicStateManager;
        _entityVector = new Entity[5];
        _numColor = numColor;
    }

    @Override
    public void init() {
        try {

            _entityVector[1] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("howToPlay.png"),new Rectangle(0,0,486,354)),
                    new Rectangle((1080/2)-(486/2),200,486 ,354));
            _entityVector[2] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("tapToPlay.png"),new Rectangle(0,0,506,72)),
                    new Rectangle((1080/2)-(506/2),1300,506 ,72));
            _entityVector[3] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("instructions.png"),new Rectangle(0,0,538,551)),
                    new Rectangle((1080/2)-(538/2),700,538 ,551));
            _entityVector[4] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("buttons.png"),new Rectangle(140,0,140,140)),
                    new Rectangle(890,200,140 ,140));

            int color = _logicStateManager.getGame().getGraphics().getColorSprite(_logicStateManager.getGame().getGraphics().
                    newImage("backgrounds.png"),0 + 32 * _numColor,0,32,32);

            _entityVector[0] = new EntityBackgroundArrows(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("arrowsBackground.png"),new Rectangle(0,1150,676,1920)),
                    new Rectangle((1080/2)-(676/2),0,676 ,1920),color);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(_entityVector[0]._rectFinal._x +"  " + _entityVector[0]._rectFinal._y + "  " +_entityVector[0]._rectFinal._width + "  " + _entityVector[0]._rectFinal._height);

    }

    @Override
    public void render() {
        _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[0].getImage(),
                _entityVector[0].getRectOrigin(),_entityVector[0].getPosRectangle(),
                ((EntityBackgroundArrows)_entityVector[0]).getColor());
        for(int i = 1; i < _entityVector.length;i++)
            _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[i].getImage(),
                    _entityVector[i].getRectOrigin(),_entityVector[i].getPosRectangle());
    }

    @Override
    public void update(float deltaTime) {
        _entityVector[0].moveSurfaceImage(_entityVector[0].getPosImgX(),
                _entityVector[0].getPosImgY() - 100 * deltaTime); //muevo el fondoDeArrows


        List<Input.TouchEvent> inputStream = _logicStateManager.getGame().getInput().getTouchEvents();

        while(!inputStream.isEmpty()){
            Input.TouchEvent event = inputStream.get(0);
            inputStream.remove(0);
            //TODO: CAMBIAR QUE CENTRO VAYA A ESTADO.GAMEOBJECT Y VAYA A ESTADO.JUEGO

            if(event._eventType == Input.TouchEvent.EventType.Release
                    && event.y <= _entityVector[4]._rectFinal._y + _entityVector[4]._rectFinal._height && event.y >= _entityVector[4]._rectFinal._y
                    && event.x <= _entityVector[4]._rectFinal._x + _entityVector[4]._rectFinal._width && event.x >= _entityVector[4]._rectFinal._x)
                _logicStateManager.spawActiveState(3);
            else if(event._eventType == Input.TouchEvent.EventType.Release
                    && event.y <= _entityVector[0]._rectFinal._y + _entityVector[0]._rectFinal._height && event.y >= _entityVector[0]._rectFinal._y
                    && event.x <= _entityVector[0]._rectFinal._x + _entityVector[0]._rectFinal._width && event.x >= _entityVector[0]._rectFinal._x){
                _logicStateManager.spawActiveState(3);
            }
        }
    }

    private int _numColor;
    private LogicStateManager _logicStateManager;
    private Entity _entityVector [];
}
