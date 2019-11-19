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
        TouchEvent _event = new TouchEvent();
        _event.x = _abstractGraphics.changeToGamelCoordenatesX(mouseEvent.getX());
        _event.y = _abstractGraphics.changeToGamelCoordenatesY(mouseEvent.getY());
        _event._eventType = TouchEvent.EventType.Release;
        _event.pointerId = mouseEvent.getButton();

        synchronized (this) {
            _inputStream.add(_event);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        TouchEvent _event = new TouchEvent();
        _event.x = _abstractGraphics.changeToGamelCoordenatesX(mouseEvent.getX());
        _event.y = _abstractGraphics.changeToGamelCoordenatesY(mouseEvent.getY());
        _event._eventType = TouchEvent.EventType.Press;
        _event.pointerId = mouseEvent.getButton();


        synchronized (this) {
            _inputStream.add(_event);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        TouchEvent _event = new TouchEvent();
        _event.x = _abstractGraphics.changeToGamelCoordenatesX(mouseEvent.getX());
        _event.y = _abstractGraphics.changeToGamelCoordenatesY(mouseEvent.getY());
        _event._eventType = TouchEvent.EventType.Release;
        _event.pointerId = mouseEvent.getButton();
        synchronized (this) {
            _inputStream.add(_event);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        TouchEvent _event = new TouchEvent();
        _event.x = _abstractGraphics.changeToGamelCoordenatesX(mouseEvent.getX());
        _event.y = _abstractGraphics.changeToGamelCoordenatesY(mouseEvent.getY());
        _event._eventType = TouchEvent.EventType.EnteredScreen;
        _event.pointerId = mouseEvent.getButton();
        synchronized (this) {
            _inputStream.add(_event);
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        TouchEvent _event = new TouchEvent();
        _event.x = _abstractGraphics.changeToGamelCoordenatesX(mouseEvent.getX());
        _event.y = _abstractGraphics.changeToGamelCoordenatesY(mouseEvent.getY());
        _event._eventType = TouchEvent.EventType.ExitScreen;
        _event.pointerId = mouseEvent.getButton();
        synchronized (this) {
            _inputStream.add(_event);
        }
    }

    AbstractGraphics _abstractGraphics;
    List<TouchEvent> _inputStream;
}
