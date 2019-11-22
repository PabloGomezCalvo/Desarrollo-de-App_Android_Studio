package gdv.ucm.engine_pc;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import gdv.ucm.interfaces.Input;
import gdv.ucm.utilities.AbstractGraphics;


public class InputPC implements Input, MouseListener {

    public InputPC(AbstractGraphics abstractGraphics){
        _abstractGraphics = abstractGraphics;
        _inputStream = new ArrayList<>();
    }

    @Override
    synchronized public List<TouchEvent> getTouchEvents() {
        return _inputStream;
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        TouchEvent _event = createEvent(mouseEvent);
        if(_event != null) {
            synchronized (this) {
                _inputStream.add(_event);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        TouchEvent _event = createEvent(mouseEvent);
        if(_event != null) {
            synchronized (this) {
                _inputStream.add(_event);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        TouchEvent _event = createEvent(mouseEvent);
        if(_event != null) {
            synchronized (this) {
                _inputStream.add(_event);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        TouchEvent _event = createEvent(mouseEvent);
        if(_event != null) {
            synchronized (this) {
                _inputStream.add(_event);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        TouchEvent _event = createEvent(mouseEvent);
        if(_event != null) {
            synchronized (this) {
                _inputStream.add(_event);
            }
        }
    }

    private TouchEvent createEvent(MouseEvent mouseEvent){
        if(_abstractGraphics.changeToGamelCoordenatesY(mouseEvent.getY()) != -1 && _abstractGraphics.changeToGamelCoordenatesY(mouseEvent.getY()) != -1) {
            TouchEvent _event = new TouchEvent();
            _event.x = _abstractGraphics.changeToGamelCoordenatesX(mouseEvent.getX());
            _event.y = _abstractGraphics.changeToGamelCoordenatesY(mouseEvent.getY());
            _event._eventType = TouchEvent.EventType.ExitScreen;
            _event.pointerId = mouseEvent.getButton();

            return _event;
        }
        return null;
    }

    private AbstractGraphics _abstractGraphics;
    private List<TouchEvent> _inputStream;
}
