package gdv.ucm.logica;

import java.io.IOException;
import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public class MenuState implements State {

    public MenuState(LogicStateManager logicStateManager){
        _logicStateManager = logicStateManager;
        _entityVector = new Entity[8];
    }


    @Override
    public void init() {

        try {
            _entityVector[0] = new EntityBackgroundArrows(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("arrowsBackground.png"),new Rectangle(0,1150,676,1920)),
                    new Rectangle((1080/2)-(676/2),0,676 ,1920));
            _entityVector[1] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("white.png"),new Rectangle(0,0,32,32)),
                    new Rectangle(870,0,(1080/2)-(676/2) ,1920));
            _entityVector[2] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("white.png"),new Rectangle(0,0,32,32)),
                    new Rectangle(0,0,(1080/2)-(676/2),1920));
            _entityVector[3] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("switchDashLogo.png"),new Rectangle(0,0,508,368)),
                    new Rectangle((1080/2)-(508/2),200,508 ,368));
            _entityVector[4] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("tapToPlay.png"),new Rectangle(0,0,506,72)),
                    new Rectangle((1080/2)-(506/2),800,506 ,72));
            _entityVector[5] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("buttons.png"),new Rectangle(140,0,140,140)),
                    new Rectangle(50,200,140 ,140));
            _entityVector[6] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("buttons.png"),new Rectangle(0,0,140,140)),
                    new Rectangle(890,200,140 ,140));
            _entityVector[7] = new Entity(
                    new Sprite(_logicStateManager.getGame().getGraphics().
                            newImage("backgrounds.png"),new Rectangle(0,0,32,32)),
                    new Rectangle(0,0,32 ,32));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render() {



        for(int i = 0; i < _entityVector.length;i++)
            _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[i].getImage(),
                    _entityVector[i].getRectOrigin(),_entityVector[i].getPosRectangle());

    }

    @Override
    public void update(float deltaTime) {
        System.out.println(_entityVector[0].getRectOrigin()._y);
        _entityVector[0].moveSurfaceImage(_entityVector[0].getPosImgX(),
                _entityVector[0].getPosImgY() - 100 * deltaTime);
    }

    private LogicStateManager _logicStateManager;
    private Entity _entityVector [];
}
