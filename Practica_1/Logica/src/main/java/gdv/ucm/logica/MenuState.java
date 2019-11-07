package gdv.ucm.logica;

import java.io.IOException;
import java.util.List;
import gdv.ucm.interfaces.Input;
import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public class MenuState implements State {

    public MenuState(LogicStateManager logicStateManager, int numColor){
        _logicStateManager = logicStateManager;
        _entityVector = new Entity[5];
        _numColor = numColor;
    }


    @Override
    public void init() {


        try {

            _entityVector[1] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("switchDashLogo.png"),new Rectangle(0,0,508,368)),
                    new Rectangle((1080/2)-(508/2),200,508 ,368));
            _entityVector[2] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("tapToPlay.png"),new Rectangle(0,0,506,72)),
                    new Rectangle((1080/2)-(506/2),800,506 ,72));
            _entityVector[3] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("buttons.png"),new Rectangle(140,0,140,140)),
                    new Rectangle(50,200,140 ,140));
            _entityVector[4] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("buttons.png"),new Rectangle(0,0,140,140)),
                    new Rectangle(890,200,140 ,140));

            int color = _logicStateManager.getGame().getGraphics().getColorSprite(_logicStateManager.getGame().getGraphics().
                            newImage("backgrounds.png"),0 + 32 * _numColor,0,32,32);

            _logicStateManager.setClearColor(color);


            _entityVector[0] = new EntityBackgroundArrows(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("arrowsBackground.png"),new Rectangle(0,1150,676,1920)),
                    new Rectangle((1080/2)-(676/2),0,676 ,1920),color);

        } catch (IOException e) {
            e.printStackTrace();
        }
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

 /*       List<Input.TouchEvent> inputStream = _logicStateManager.getGame().getInput().getTouchEvents();

        while(!inputStream.isEmpty()){
            Input.TouchEvent event = inputStream.get(0);
            inputStream.remove(0);
            if(event._eventType == Input.TouchEvent.EventType.Release && event.y < 250)
                _logicStateManager.spawActiveState(1);
        }
*/
    }

    private int _numColor;
    private LogicStateManager _logicStateManager;
    private Entity _entityVector [];
}
