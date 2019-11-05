package gdv.ucm.logica;

import java.io.IOException;

import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public class MenuState implements State {

    public MenuState(LogicStateManager logicStateManager){
        _logicStateManager = logicStateManager;
        _entityVector = new Entity[5];
        i = 0;
    }


    @Override
    public void init() {

        try {
            _entityVector[0] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("balls.png"),new Rectangle(0,150,130,150)),
                    new Rectangle(0,0,130 ,150));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[0].getImage(),
                _entityVector[0].getRectOrigin(),_entityVector[0].getPosRectangle());
    }

    @Override
    public void update(float deltaTime) {
        i++;
        if(i > 1500)
            _logicStateManager.spawActiveState(1);
        else
            _entityVector[0].moveEntity(_entityVector[0].getPosX() + 50*deltaTime,_entityVector[0].getPosY());

    }

    private int i;
    private LogicStateManager _logicStateManager;
    private Entity _entityVector [];
}
