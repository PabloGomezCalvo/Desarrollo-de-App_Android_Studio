package gdv.ucm.logica;

import java.io.IOException;
import java.util.List;
import gdv.ucm.interfaces.Input;
import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public class MenuState extends State {

    public MenuState(LogicStateManager logicStateManager, int numColor){
        super(logicStateManager);
        _entityVector = new Entity[6];
        _numColor = numColor;
    }


    @Override
    public void init() {

        _entityVector[0] = new Entity(
                new Sprite(_logicStateManager.getImage(0), new Rectangle(0, 0, 32, 32)),
                new Rectangle(0, 0, 1080, 1920));
        _entityVector[2] = new Entity(
                new Sprite(_logicStateManager.getImage(10), new Rectangle(0, 0, 508, 368)),
                new Rectangle((1080 / 2) - (508 / 2), 200, 508, 368));
        _entityVector[3] = new Entity(
                new Sprite(_logicStateManager.getImage(4), new Rectangle(0, 0, 506, 72)),
                new Rectangle((1080 / 2) - (506 / 2), 800, 506, 72));
        _entityVector[4] = new EntitySwapper(
                new Sprite(_logicStateManager.getImage(6), new Rectangle(140 * 2, 0, 140, 140)),
                new Rectangle(50, 200, 140, 140), 0, false);
        _entityVector[5] = new Entity(
                new Sprite(_logicStateManager.getImage(6), new Rectangle(0, 0, 140, 140)),
                new Rectangle(890, 200, 140, 140));

        int color = _logicStateManager.getGame().getGraphics().getColorSprite(_logicStateManager.getImage(1),
                32 * _numColor, 0, 32, 32);

        _entityVector[1] = new EntityBackgroundArrows(
                new Sprite(_logicStateManager.getImage(2), new Rectangle(0, 1150, 676, 1920)),
                new Rectangle((1080 / 2) - (676 / 2), 0, 676, 1920), color);
    }

    @Override
    public void render() {

        _logicStateManager.getGame().getGraphics().drawRectToRect(_entityVector[1].getImage(),
                _entityVector[1].getRectOrigin(), _entityVector[1].getPosRectangle(),
                ((EntityBackgroundArrows) _entityVector[1]).getColor());
        for (int i = 2; i < _entityVector.length; i++)
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

        _entityVector[1].moveSurfaceImage(_entityVector[1].getPosImgX(),
                _entityVector[1].getPosImgY() - 384 * deltaTime); //muevo el fondoDeArrows

        if(!_whiteFlash) {
            List<Input.TouchEvent> inputStream = _logicStateManager.getGame().getInput().getTouchEvents();
            boolean selected = false;
            while (!inputStream.isEmpty()) {
                Input.TouchEvent event = inputStream.get(0);
                inputStream.remove(0);
                System.out.println(event._eventType);

                if (event._eventType == Input.TouchEvent.EventType.Release &&
                        event.y <= _entityVector[5]._rectFinal._y + _entityVector[5]._rectFinal._height && event.y >= _entityVector[5]._rectFinal._y
                        && event.x <= _entityVector[5]._rectFinal._x + _entityVector[5]._rectFinal._width && event.x >= _entityVector[5]._rectFinal._x)
                    _logicStateManager.spawActiveState(1);
                else if (event._eventType == Input.TouchEvent.EventType.Release &&
                        event.y <= _entityVector[1]._rectFinal._y + _entityVector[1]._rectFinal._height && event.y >= _entityVector[1]._rectFinal._y
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
}
